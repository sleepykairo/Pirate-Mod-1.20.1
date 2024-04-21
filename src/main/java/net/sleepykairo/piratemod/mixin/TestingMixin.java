package net.sleepykairo.piratemod.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(PhantomEntity.class)
//@Mixin(targets = "net.minecraft.entity.mob.GhastEntity")

@Mixin(targets = "net.minecraft.entity.mob.GhastEntity$ShootFireballGoal")
public abstract class TestingMixin {
/*    @Shadow public int cooldown;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "net.minecraft.entity.mob.GhastEntity$ShootFireballGoal;spawnEntity(Lnet.minecraft.entity.projectile.FireballEntity;)V"))
    public void tick(CallbackInfo ci) {
        this.cooldown = 0;
        PirateMod.LOGGER.info("gahastign");
    }*/
}