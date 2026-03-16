package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;

public class FlameProjectileEntity extends ThrownItemEntity {
    public FlameProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FlameProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.FLAME_PROJECTILE, livingEntity, world);
    }

    public FlameProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.FLAME_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    public void tick() {
        super.tick();

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            if (this.random.nextFloat() < 0.5) {
                serverWorld.spawnParticles(
                        ParticleTypes.LAVA,
                        this.getX(), this.getEyeY(), this.getZ(),
                        1,
                        0, 0, 0,
                        0
                );
            }
            if (this.random.nextFloat() < 0.8) {
                serverWorld.spawnParticles(
                        ParticleTypes.FLAME,
                        this.getX(), this.getEyeY(), this.getZ(),
                        1,
                        0, 0, 0,
                        0
                );
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if(!this.getWorld().isClient()){
            this.getWorld().sendEntityStatus(this, (byte)3);

            float power = 0f;
            this.getWorld().createExplosion(this.getOwner(),
                    this.getX(), this.getY(), this.getZ(), power, false, World.ExplosionSourceType.TNT);

            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.LAVA,
                    this.getX(), this.getEyeY(), this.getZ(),
                    5,
                    0, 0, 0,
                    0.25
            );
            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getEyeY(), this.getZ(),
                    5,
                    0, 0, 0,
                    0.25
            );
        }
        this.discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if(!this.getWorld().isClient() && entity != this.getOwner() && !entity.isTeammate(this.getOwner())){
            this.getWorld().sendEntityStatus(this, (byte)3);

            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.LAVA,
                    this.getX(), this.getBodyY(0), this.getZ(),
                    5,
                    0, 0, 0,
                    0.25
            );
            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getBodyY(0), this.getZ(),
                    5,
                    0, 0, 0,
                    0.25
            );

            entity.playSound(SoundEvents.ITEM_FIRECHARGE_USE,
                    0.3f, (this.getWorld().getRandom().nextFloat()) * 0.5f + 1f);

            entity.damage(this.getDamageSources().inFire(), 10);
            entity.setOnFireFor(5);
        }
        this.discard();
    }
}
