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

public class GhostEntity extends VexEntity {


    public GhostEntity(EntityType<? extends GhostEntity> entityType, World world) {
        super(entityType, world);
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
}
