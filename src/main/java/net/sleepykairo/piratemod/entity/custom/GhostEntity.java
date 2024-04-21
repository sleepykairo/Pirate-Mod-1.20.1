package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;

import java.lang.annotation.Target;

public class GhostEntity extends HostileEntity {


    public GhostEntity(EntityType<? extends GhostEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.2f));
        //this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.2f));

        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createGhostAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 250)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_BELL_USE;
    }

    @Override
    public void tickMovement() {
        if (this.getTarget() != null) {
            if (getTargetY() > this.getY() + 3) {
                this.setVelocity(this.getVelocity().x, 0.1, this.getVelocity().z);
            } else if (getTargetY() < this.getY() && !isOnGround()) {
                this.setVelocity(this.getVelocity().x, -0.25, this.getVelocity().z);
            }
        }
        super.tickMovement();
    }

    @Override
    public void tick() {

        super.tick();
    }

    private double getTargetY() {
        LivingEntity target = this.getTarget();
        if (target != null) {
            return target.getPos().y;
        }
        return 0;
    }
}
