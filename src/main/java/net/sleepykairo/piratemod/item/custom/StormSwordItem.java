package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.effect.ModEffects;
import net.sleepykairo.piratemod.util.interfaces.PlayerAttackAccess;

public class StormSwordItem extends SwordItem {
    public StormSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private static final String CHARGED_KEY = "Charged";

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player && StormSwordItem.isCharged(stack)) {
            target.addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED_EFFECT,
                    attacker.getWorld().isThundering() ? 80 : 40,
                    attacker.getWorld().isThundering() ? 1 : 0,
                    true, false));

            StormSwordItem.setCharged(stack, false);
            player.getItemCooldownManager().set(this, 150);
            if (attacker.getWorld() instanceof ServerWorld serverWorld) {
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(attacker.getWorld());
                if (lightningEntity != null) {
                    lightningEntity.refreshPositionAfterTeleport(
                            target.getX(),
                            target.getY(),
                            target.getZ());
                    lightningEntity.setCosmetic(true);
                    attacker.getWorld().spawnEntity(lightningEntity);
                }

                serverWorld.spawnParticles(
                        ParticleTypes.ELECTRIC_SPARK,
                        target.getX(), target.getBodyY(0.5), target.getZ(),
                        100,
                        0.25, 0.25, 0.25,
                        1
                        );
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (StormSwordItem.isCharged(itemStack)) {
            return TypedActionResult.fail(itemStack);
        }

        StormSwordItem.setCharged(itemStack, true);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean(CHARGED_KEY);
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean(CHARGED_KEY, charged);
    }
}
