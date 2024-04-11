package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class DartfishProjectileEntity extends ThrownItemEntity {

    public float power = 2f;
    public DartfishProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public DartfishProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.DARTFISH_PROJECTILE, owner, world); // null will be changed later
    }

    public DartfishProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.DARTFISH_PROJECTILE, x, y, z, world); // null will be changed later
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.DARTFISH;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.getWorld().isClient()) {
            Entity entity = entityHitResult.getEntity();
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()),5f);
            ParticleEffect particleEffect = ParticleTypes.EXPLOSION;
            this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        ParticleEffect particleEffect = ParticleTypes.EXPLOSION;
        this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), power, World.ExplosionSourceType.MOB);
        this.discard();
    }
}
