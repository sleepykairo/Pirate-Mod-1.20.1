package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.util.ModTags;

public class MusketBallEntity extends ThrownItemEntity {
    private float damage = 10;

    public MusketBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public MusketBallEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.MUSKET_BALL_ENTITY, livingEntity, world);
    }

    public MusketBallEntity(World world, double x, double y, double z) {
        super(ModEntities.MUSKET_BALL_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MUSKET_BALL;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), damage);

        Entity entity2 = this.getOwner();
        LivingEntity livingEntity = (LivingEntity)entity;

        if (entity2 != null && livingEntity != entity2 && livingEntity instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
            ((ServerPlayerEntity)entity2).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, GameStateChangeS2CPacket.DEMO_OPEN_SCREEN));
        }
    }

    @Override
    protected void onBlockCollision(BlockState state) {
//        super.onBlockCollision(state);
        this.discard();
    }

    @Override
    public void tick() {
        super.tick();
        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.SMOKE,
                    getX() + getX(), getEyeY() + getY(), getZ() + getZ(),
                    2,
                    0, 0,0,
                    0.1
            );
        }
    }

    public void setDamage(float f) {
        damage = f;
    }

//    @Override
//    protected void onCollision(HitResult hitResult) {
//        super.onCollision(hitResult);
//        if (hitResult.getType() == HitResult.Type.ENTITY) return;
//
//        if (getWorld() instanceof ServerWorld serverWorld)
//        {
//            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
//            BlockState state = serverWorld.getBlockState(blockHitResult.getBlockPos());
//
//            if (state.isIn(ModTags.Blocks.MUSKET_BALL_BREAKABLE))
//            {
//                serverWorld.breakBlock(blockHitResult.getBlockPos(), false, this.getOwner());
//            }
//            else {
//                this.discard();
//            }
//        }
//    }
}
