package net.sleepykairo.piratemod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityGroup;
import net.minecraft.item.ItemStack;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.item.ModItems;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getAttackDamage", at = @At("RETURN"), cancellable = true)
    private static void getAttackDamage_HeadInject_DiamondSwordfish(ItemStack stack, EntityGroup group, CallbackInfoReturnable<Float> cir,
                                                                    @Local(ordinal = 0) MutableFloat mutableFloat) {
        PirateMod.LOGGER.info("Before added buffs: {}", mutableFloat);
        if (stack.isOf(ModItems.DIAMOND_SWORDFISH)) {
            int sharpLevel = EnchantmentHelper.getLevel(Enchantments.SHARPNESS, stack);
            if (sharpLevel > 0) {
                mutableFloat.add((sharpLevel * 0.25f) + 0.25f);
            }
            PirateMod.LOGGER.info("After added buffs: {}", mutableFloat);
        }
        cir.setReturnValue(mutableFloat.floatValue());
    }
}
