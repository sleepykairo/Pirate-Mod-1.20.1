package net.sleepykairo.piratemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SEA_HELMET, ModItems.SEA_CHESTPLATE, ModItems.SEA_LEGGINGS, ModItems.SEA_BOOTS);

        getOrCreateTagBuilder(ModTags.Items.HELD_LIKE_CROSSBOW_UNLOADED)
                .add(ModItems.FLINTLOCK);

        getOrCreateTagBuilder(ModTags.Items.IGNORE_ENCHANTED_NAME_FORMATTING)
                .add(ModItems.NIGHTFIN_SWORDFISH);
    }
}