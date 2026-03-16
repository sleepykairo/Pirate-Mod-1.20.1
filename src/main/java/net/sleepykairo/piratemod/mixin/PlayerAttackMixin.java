package net.sleepykairo.piratemod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.piratemod.util.interfaces.PlayerAttackAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerAttackMixin implements PlayerAttackAccess {
    public float previousAttackCooldown = 1;
    public float attackDamage;

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Inject(method = "attack", at = @At("HEAD"))
    public void inject(Entity target, CallbackInfo ci) {
        setPreviousAttackCooldown(this.getAttackCooldownProgress(0.5f));
    }

    @Inject(method = "attack", at = @At("TAIL"))
    public void inject2(Entity target, CallbackInfo ci, @Local(ordinal = 0) float f) {
        setAttackDamage(f);
    }

    public void setPreviousAttackCooldown(float prevAttackCooldown) {
        previousAttackCooldown = prevAttackCooldown;
    }

    public float getPreviousAttackCooldown() {
        return previousAttackCooldown;
    }

    public void setAttackDamage(float attackDmg) {
        attackDamage = attackDmg;
    }

    public float getAttackDamage() {
        return attackDamage;
    }
}