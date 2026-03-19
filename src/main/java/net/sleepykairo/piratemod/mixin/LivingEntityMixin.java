package net.sleepykairo.piratemod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.item.ModArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract int getArmor();

    @ModifyVariable(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isOnGround()Z"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getBaseMovementSpeedMultiplier()F"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMovementSpeed()F")
            ), ordinal = 2)
//    private void lol(Vec3d movementInput, CallbackInfo ci, @Local(ordinal = 2) float depthStriderAmount){
    private float travel_depthStriderMixin(float depthStriderAmount){
//        PirateMod.LOGGER.info("Before: {}", depthStriderAmount);

        LivingEntity livingEntity = (LivingEntity) (Object) this;
        Iterable<ItemStack> armorStacks = livingEntity.getArmorItems();

        int seaArmorCount = 0;

        for (ItemStack stack : armorStacks) {
            if (stack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ModArmorMaterials.SEA) {
                    seaArmorCount++;
                }
            }
        }

        depthStriderAmount += seaArmorCount * 0.75f;

//        PirateMod.LOGGER.info("After: {}", depthStriderAmount);
        return depthStriderAmount;
    }
}
