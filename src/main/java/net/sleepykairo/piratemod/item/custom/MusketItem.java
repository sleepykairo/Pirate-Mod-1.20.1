package net.sleepykairo.piratemod.item.custom;

import com.google.common.collect.Lists;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.custom.MusketBallEntity;
import net.sleepykairo.piratemod.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MusketItem
        extends RangedWeaponItem
        implements Vanishable {
    public static final int TICKS_PER_SECOND = 20;
    public static final int RANGE = 15;
    private boolean hasPlayedSound = false;

    public MusketItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getHeldProjectiles() {
        return stack -> stack.isOf(ModItems.MUSKET_BALL);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        boolean isCreativeAndHasAmmo;
        int i;
        float f;
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)user;
        boolean isCreativeMode = playerEntity.getAbilities().creativeMode;
        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty() && !isCreativeMode) {
            return;
        }
        if (itemStack.isEmpty()) {
            itemStack = new ItemStack(ModItems.MUSKET_BALL);
        }
        if ((double)(f = MusketItem.getPullProgress(i = this.getMaxUseTime(stack) - remainingUseTicks)) < 1) {
            return;
        }
        boolean bl3 = isCreativeAndHasAmmo = isCreativeMode && itemStack.isOf(ModItems.MUSKET_BALL);
        if (!world.isClient) {
            int k;
            int j;
            MusketBallEntity projectileEntity = new MusketBallEntity(user, world);
            projectileEntity.setDamage(21f);

            projectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 5f, 0.0f);
            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
            world.spawnEntity(projectileEntity);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 2.0f, 0.0f);
            if (world instanceof ServerWorld serverWorld) {
                Vec3d pos = user.getRotationVector().normalize().multiply((double) 2 / 3);
                serverWorld.spawnParticles(
                        ParticleTypes.SMOKE,
                        projectileEntity.getX() + pos.getX(), projectileEntity.getEyeY() + pos.getY(), projectileEntity.getZ() + pos.getZ(),
                        20,
                        0.0, 0.0,0.0,
                        0.5
                );

                serverWorld.spawnParticles(
                        ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                        projectileEntity.getX() + pos.getX(), projectileEntity.getEyeY() + pos.getY(), projectileEntity.getZ() + pos.getZ(),
                        5,
                        0.0, 0.0,0.0,
                        0.05
                );
            }
        }
        if (!isCreativeAndHasAmmo && !playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
            if (itemStack.isEmpty()) {
                playerEntity.getInventory().removeOne(itemStack);
            }
        }
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 40.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean bl;
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl2 = bl = !user.getProjectileType(itemStack).isEmpty();
        if (user.getAbilities().creativeMode || bl) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ModItems.MUSKET_BALL);
    }

    @Override
    public int getRange() {
        return 15;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent = SoundEvents.ITEM_CROSSBOW_LOADING_START;
            SoundEvent soundEvent2 = SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
            float f = MusketItem.getPullProgress(i = this.getMaxUseTime(stack) - remainingUseTicks);
            if (f < 0.2f) {
                hasPlayedSound = false;
            }
//            if (f >= 0.2f) {
//                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.PLAYERS, 0.5f, 1.0f);
//            }
            if (f >= 1f && soundEvent2 != null && !hasPlayedSound) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 2.0f, 2.0f);
                hasPlayedSound = true;
            }
        }
    }
}

