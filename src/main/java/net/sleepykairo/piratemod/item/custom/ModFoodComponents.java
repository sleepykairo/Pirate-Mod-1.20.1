package net.sleepykairo.piratemod.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent GOLDFISH = new FoodComponent.Builder()
            .hunger(8).saturationModifier(2f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1f).alwaysEdible().build();
    public static final FoodComponent RED_SUNFISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.75f).build();
    public static final FoodComponent GREEN_SUNFISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).build();
    public static final FoodComponent EMERALD_FISH = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.3f)
                .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false, false), 1f)
                    .alwaysEdible().build();
}