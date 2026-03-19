package net.sleepykairo.piratemod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.custom.MusketBallEntity;
import net.sleepykairo.piratemod.item.ModItems;

public class ArcherfishItem extends MusketItem{
    public ArcherfishItem(Settings settings) {
        super(settings);
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
        if ((double)(f = this.getPullProgress(i = this.getMaxUseTime(stack) - remainingUseTicks)) < 1) {
            return;
        }
        boolean bl3 = isCreativeAndHasAmmo = isCreativeMode && itemStack.isOf(ModItems.MUSKET_BALL);
        if (!world.isClient) {
            for (int shotProjectiles = 0; shotProjectiles < 3; shotProjectiles++)
            {
                MusketBallEntity projectileEntity = new MusketBallEntity(user, world);
                projectileEntity.setDamage(16f);

                projectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 2.5f, 2.5f);
                stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                world.spawnEntity(projectileEntity);
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 0.0f);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.PLAYERS, 0.75f, 0.5f);
            if (world instanceof ServerWorld serverWorld) {
                Vec3d pos = user.getRotationVector().normalize().multiply(1.5);
                serverWorld.spawnParticles(
                        ParticleTypes.BUBBLE_POP,
                        user.getX() + pos.getX(), user.getEyeY() + pos.getY(), user.getZ() + pos.getZ(),
                        20,
                        0.2, 0.2,0.2,
                        0.25
                );
                serverWorld.spawnParticles(
                        ParticleTypes.FISHING,
                        user.getX() + pos.getX(), user.getEyeY() + pos.getY(), user.getZ() + pos.getZ(),
                        10,
                        0.2, 0.2,0.2,
                        0.5
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

    @Override
    public float getPullProgress(int useTicks) {
        float f = (float)useTicks / 30.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }
}
