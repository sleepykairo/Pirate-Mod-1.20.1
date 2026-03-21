package net.sleepykairo.piratemod.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;

public class StormManager {
    public static final float STORM_START_DIST = 640.0F; //how far from 0 you are before the storm starts
    public static final float STORM_END_DIST = 640.0F; //how far you are from the start before the storm is max

    public static final float LIGHTNING_SPAWN_RANGE = 128.0F;
    public static final float LIGHTNING_SPAWN_CHANCE_MULTIPLIER = 4.0F;
    public static final float LIGHTNING_SPAWN_CHANCE_OFFSET = 0.25F;
    public static final float FIREBALL_SPAWN_RANGE = 128.0F;
    public static final float FIREBALL_SPAWN_CHANCE_MULTIPLIER = 2.0F;
    public static final float FIREBALL_SPAWN_CHANCE_OFFSET = 0.25F;
    public static final float BIG_FIREBALL_SPAWN_RANGE = 128.0F;
    public static final float BIG_FIREBALL_SPAWN_CHANCE_MULTIPLIER = 8.0F;
    public static final float BIG_FIREBALL_SPAWN_CHANCE_OFFSET = 0.25F;

    public static final float FIREBALL_MAX_HORIZONTAL_SPEED = 0.5F;

    public static void tickStorm(PlayerEntity player, Vec3d pos) {
        PirateMod.LOGGER.info("{}, {}, {}", player.getPos(), getDistanceFromZero(pos), getStormAmount(pos));

        float stormAmount = getStormAmount(pos);
        if (stormAmount < 0) return;

        World world = player.getWorld();

        if (player.getRandom().nextFloat() * LIGHTNING_SPAWN_CHANCE_MULTIPLIER < getStormAmount(pos) + LIGHTNING_SPAWN_CHANCE_OFFSET) {
            spawnLightning(world, pos);
        }
        if (player.getRandom().nextFloat() * FIREBALL_SPAWN_CHANCE_MULTIPLIER < getStormAmount(pos) + FIREBALL_SPAWN_CHANCE_OFFSET) {
            spawnFireball(world, pos);
        }
        if (player.getRandom().nextFloat() * BIG_FIREBALL_SPAWN_CHANCE_MULTIPLIER < getStormAmount(pos) + BIG_FIREBALL_SPAWN_CHANCE_OFFSET) {
            spawnBigFireball(world, pos);
        }
    }

    public static void spawnLightning(World world, Vec3d playerPos) {
        Random random = world.getRandom();
        float range = LIGHTNING_SPAWN_RANGE;
        LightningEntity entity = EntityType.LIGHTNING_BOLT.create(world);

        if (entity != null) {
            double posX = playerPos.getX() + (random.nextFloat() * range) - range / 2;
            double posZ = playerPos.getZ() + (random.nextFloat() * range) - range / 2;

            entity.refreshPositionAfterTeleport(
                    posX,
                    playerPos.getY(),
                    posZ);

            world.spawnEntity(entity);
        }
    }
    public static void spawnFireball(World world, Vec3d playerPos) {
        Random random = world.getRandom();
        float range = FIREBALL_SPAWN_RANGE;
        SmallFireballEntity entity = EntityType.SMALL_FIREBALL.create(world);

        if (entity != null) {
            double posX = playerPos.getX() + (random.nextFloat() * range) - range / 2;
            double posZ = playerPos.getZ() + (random.nextFloat() * range) - range / 2;

            entity.refreshPositionAfterTeleport(
                    posX,
                    playerPos.getY(),
                    posZ);

            entity.setVelocity(
                    (random.nextFloat() * FIREBALL_MAX_HORIZONTAL_SPEED) - FIREBALL_MAX_HORIZONTAL_SPEED / 2,
                    -2.5,
                    (random.nextFloat() * FIREBALL_MAX_HORIZONTAL_SPEED) - FIREBALL_MAX_HORIZONTAL_SPEED / 2);

            world.spawnEntity(entity);
        }
    }
    public static void spawnBigFireball(World world, Vec3d playerPos) {
        Random random = world.getRandom();
        float range = BIG_FIREBALL_SPAWN_RANGE;
        if (isNorth(playerPos)) {
            ArrowEntity entity = EntityType.ARROW.create(world);
            if (entity != null) {
                double posX = playerPos.getX() + (random.nextFloat() * range) - range / 2;
                double posZ = playerPos.getZ() + (random.nextFloat() * range) - range / 2;

                entity.refreshPositionAfterTeleport(
                        posX,
                        playerPos.getY(),
                        posZ);

                entity.setVelocity(0, -10, 0);

                world.spawnEntity(entity);
            }
        } else {
            FireballEntity entity = EntityType.FIREBALL.create(world);
            if (entity != null) {
                double posX = playerPos.getX() + (random.nextFloat() * range) - range / 2;
                double posZ = playerPos.getZ() + (random.nextFloat() * range) - range / 2;

                entity.refreshPositionAfterTeleport(
                        posX,
                        playerPos.getY(),
                        posZ);

                entity.setVelocity(
                        (random.nextFloat() * FIREBALL_MAX_HORIZONTAL_SPEED) - FIREBALL_MAX_HORIZONTAL_SPEED / 2,
                        -2.5,
                        (random.nextFloat() * FIREBALL_MAX_HORIZONTAL_SPEED) - FIREBALL_MAX_HORIZONTAL_SPEED / 2);

                world.spawnEntity(entity);
            }
        }
    }

    public static float getStormAmount(Vec3d pos) {
        return (getDistanceFromZero(pos) - STORM_START_DIST) / STORM_END_DIST;
    }

    public static float getDistanceFromZero(Vec3d pos) {
        Vec3d posNoY = new Vec3d(pos.getX(), 0, pos.getZ());
        return (float) (posNoY.length());
    }

    public static boolean isNorth(Vec3d pos) {
        return pos.getZ() < 0;
    }
}
