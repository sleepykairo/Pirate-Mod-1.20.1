package net.sleepykairo.piratemod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.effect.ModEffects;

public class ModPotions {
    public static Potion FROST_WALKER_POTION;

    public static Potion registerPotion(String name) {
        return Registry.register(Registries.POTION, new Identifier(PirateMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.FROST_WALKER_EFFECT, 200, 0)));
    }

    public static void registerPotions() {
        FROST_WALKER_POTION = registerPotion("frost_walker_potion");
    }
}
