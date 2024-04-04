package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;

public class CannonballProjectileEntity extends ThrownItemEntity {
    public CannonballProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public CannonballProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.CANNONBALL_PROJECTILE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CANNONBALL;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        if(!this.getWorld().isClient()){
            this.getWorld().sendEntityStatus(this, (byte)3);

            float power = 2f;
            float offset = 1.5f;

            this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), power, World.ExplosionSourceType.BLOCK);
            //this.getWorld().createExplosion(this, null, null, getX()-offset, this.getBodyY(0.0625), getZ(), power, false, World.ExplosionSourceType.BLOCK, false);
            //this.getWorld().createExplosion(this, null, null, getX()+offset, this.getBodyY(0.0625), getZ(), power, false, World.ExplosionSourceType.BLOCK, false);
            //this.getWorld().createExplosion(this, null, null, getX(), this.getBodyY(0.0625)-offset, getZ(), power, false, World.ExplosionSourceType.BLOCK, false);
            //this.getWorld().createExplosion(this, null, null, getX(), this.getBodyY(0.0625)+offset, getZ(), power, false, World.ExplosionSourceType.BLOCK, false);
            //this.getWorld().createExplosion(this, null, null, getX(), this.getBodyY(0.0625), getZ()-offset, power, false, World.ExplosionSourceType.BLOCK, false);
            //this.getWorld().createExplosion(this, null, null, getX(), this.getBodyY(0.0625), getZ()+offset, power, false, World.ExplosionSourceType.BLOCK, false);
        }

        this.discard();
    }
}
