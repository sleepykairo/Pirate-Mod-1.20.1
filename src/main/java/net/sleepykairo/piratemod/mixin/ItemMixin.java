package net.sleepykairo.piratemod.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
//    @Inject(method = "getRarity", at = @At("RETURN"), cancellable = true)
//    private void lol(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
//        cir.setReturnValue(Rarity);
//    }
}
