package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class DartfishProjectileEntity extends PersistentProjectileEntity {
    public DartfishProjectileEntity(EntityType<? extends DartfishProjectileEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public DartfishProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.DARTFISH_PROJECTILE, x, y, z, world);
    }

    public DartfishProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.DARTFISH_PROJECTILE, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        ItemStack itemStack = new ItemStack(ModItems.DARTFISH);
        return itemStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.getWorld().isClient()) {
            Entity entity = entityHitResult.getEntity();
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()),5f);
            this.discard();
        }
    }
}
