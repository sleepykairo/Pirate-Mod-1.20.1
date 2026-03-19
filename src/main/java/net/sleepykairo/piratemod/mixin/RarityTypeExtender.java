package net.sleepykairo.piratemod.mixin;

import com.llamalad7.mixinextras.lib.apache.commons.ArrayUtils;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Rarity.class)
public class RarityTypeExtender {
//    @Shadow
//    @Final
//    private static Rarity[] $VALUES;
//
//    @Invoker(value="<init>") // You may need to be more specific if there's more than one constructor
//    private static Rarity create(String name, int ordinal, String ) {
//        throw new IllegalStateException("Unreachable");
//    }
//
//    static {
//        var entry = create("JSONTHINGS_THINGS", $VALUES.length, "things"); // subsequent additions would be length+1, +2, ...
//
//        //noinspection ShadowFinalModification
//        $VALUES = ArrayUtils.add($VALUES, entry); // use addAll if you have more than one entry to be added, and make sure they are precisely in the order you defined the ordinals, so length before length+1, before length+2, ...
//    }
}