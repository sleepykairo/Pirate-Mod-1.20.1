package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.entity.custom.FlameProjectileEntity;

public class BoneSwordItem extends SwordItem {
    public BoneSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public static final int MAX_FIRE_TIME = 150;
    private static final String FIRE_TIME_KEY = "FireTime";

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (BoneSwordItem.getActive(stack)) {
            target.setOnFireFor(5);

            World world = attacker.getWorld();
            if (!world.isClient) {
                int fireAspectLevel = EnchantmentHelper.getFireAspect(attacker);
                for (int i = 0; i < fireAspectLevel + 3; i++) {
                    FlameProjectileEntity flameProjectileEntity = new FlameProjectileEntity(attacker, world);

                    Random random = attacker.getRandom();
                    flameProjectileEntity.setVelocity(attacker,
                            -random.nextBetween(15, 45), random.nextBetween(0, 360) - 180, 0.0f, (float) 1 / 3, 0.0f);

                    flameProjectileEntity.setPos(target.getX(), target.getBodyY(1), target.getZ());
                    world.spawnEntity(flameProjectileEntity);
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (BoneSwordItem.getFireTime(itemStack) > -1) {
            return TypedActionResult.fail(itemStack);
        }

        user.playSound(SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS,
                0.65f, (user.getRandom().nextFloat()) * 0.5f + 0.5f);

        if (!world.isClient) {
            ((ServerWorld)user.getWorld()).spawnParticles(
                    ParticleTypes.LAVA,
                    user.getX(), user.getBodyY(0.5), user.getZ(),
                    10,
                    0, 0, 0,
                    1
            );
        }

        user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, MAX_FIRE_TIME, 0, true, false));

        BoneSwordItem.setFireTime(itemStack, MAX_FIRE_TIME);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        boolean active = BoneSwordItem.getActive(stack);

        if (BoneSwordItem.getFireTime(stack) != -1) {
            if (active) {
                int fireTime = BoneSwordItem.getFireTime(stack);
                BoneSwordItem.setFireTime(stack,fireTime - 1);
            } else {
                BoneSwordItem.setFireTime(stack, -1);
                if (entity instanceof PlayerEntity player) {
                    player.getItemCooldownManager().set(this, 150);
                    player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS,
                            0.65f, (player.getRandom().nextFloat()) * 0.5f + 1.5f);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    public static int getFireTime(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null ? nbtCompound.getInt(FIRE_TIME_KEY) : -1;
    }

    public static void setFireTime(ItemStack stack, int i) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt(FIRE_TIME_KEY, i);
    }

    public static boolean getActive(ItemStack stack) {
        return BoneSwordItem.getFireTime(stack) > 0;
    }
}