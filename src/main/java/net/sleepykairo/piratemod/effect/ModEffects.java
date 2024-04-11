package net.sleepykairo.piratemod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

public class ModEffects {
    ///public static final StatusEffect FROST_WALKER_EFFECT = new FrostWalkerEffect();
    public static StatusEffect FROST_WALKER_EFFECT;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(PirateMod.MOD_ID, name),
                new FrostWalkerEffect(StatusEffectCategory.BENEFICIAL, 0x6EABD1));
    }

    public static void registerEffects() {
        FROST_WALKER_EFFECT = registerStatusEffect("frost_walker_effect");
    }
}
