package net.sleepykairo.piratemod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class BlokeEntity extends HostileEntity {


    public BlokeEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(2, new WanderAroundGoal(this, 5));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 10));

        this.targetSelector.add(1, new ActiveTargetGoal<ChickenEntity>((MobEntity)this, ChickenEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createBlokeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 10)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_BELL_USE;
    }
}
