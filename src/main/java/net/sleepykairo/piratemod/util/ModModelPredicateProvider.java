package net.sleepykairo.piratemod.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.item.ModItems;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerFishingRod(ModItems.GOLDEN_FISHING_ROD);
    }

    private static void registerFishingRod(Item fishingRod) {
        ModelPredicateProviderRegistry.register(fishingRod, new Identifier("cast"), (stack, world, entity, seed) -> {
            boolean bl2;
            if (entity == null) {
                return 0.0f;
            }
            boolean bl = entity.getMainHandStack() == stack;
            boolean bl3 = bl2 = entity.getOffHandStack() == stack;
            if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                bl2 = false;
            }
            return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
        });
    }
}
