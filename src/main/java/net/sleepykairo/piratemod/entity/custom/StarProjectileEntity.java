package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StarProjectileEntity extends ThrownItemEntity {
    public static final float START_HOMING_RANGE = 5.0F;
    public static final float HOMING_SPEED = 1.0F;

    private boolean homing = false;
    private boolean splitNormal = false;
    private boolean splitHoming = false;
    private LivingEntity target = null;

    public StarProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public StarProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world,
                                boolean homing, boolean splitNormal, boolean splitHoming) {
        super(entityType, world);
        this.homing = homing;
        this.splitNormal = splitNormal;
        this.splitHoming = splitHoming;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    public void tick() {
        super.tick();
        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    this.getX(), this.getEyeY(), this.getZ(),
                    3,
                    0, 0, 0,
                    0.5
            );
        }

        if (this.homing) {
            if (getTarget() != null) {
                Vec3d targetPos = this.target.getPos();
                Vec3d posDifference = targetPos.subtract(this.getPos());

                addVelocity(posDifference.normalize().multiply(HOMING_SPEED));
            } else {

            }
        }
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }
    public LivingEntity getTarget() {
        return this.target;
    }

//    public void setSplitNormal(boolean bl) {
//        splitNormal = bl;
//    }
//    public boolean getSplitNormal() {
//        return splitNormal;
//    }
//
//    public void setSplitHoming(boolean bl) {
//        splitHoming = bl;
//    }
//    public boolean getSplitHoming() {
//        return splitHoming;
//    }
}
