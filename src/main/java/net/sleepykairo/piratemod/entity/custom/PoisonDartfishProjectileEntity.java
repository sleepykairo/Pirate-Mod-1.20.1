package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;

public class PoisonDartfishProjectileEntity extends PersistentProjectileEntity {
    public PoisonDartfishProjectileEntity(EntityType<? extends PoisonDartfishProjectileEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public PoisonDartfishProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.POISON_DARTFISH_PROJECTILE, x, y, z, world);
    }

    public PoisonDartfishProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.POISON_DARTFISH_PROJECTILE, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        ItemStack itemStack = new ItemStack(ModItems.POISON_DARTFISH);
        return itemStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.getWorld().isClient()) {
            Entity entity = entityHitResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                entity.damage(this.getDamageSources().thrown(this, this.getOwner()),5f);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, 0));
            }
            this.discard();
        }
    }
}
