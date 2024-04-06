package net.sleepykairo.piratemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.sleepykairo.piratemod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.CANNONBALL, Models.GENERATED);

        itemModelGenerator.register(ModItems.SAWFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OBSIDIAN_SAWFISH, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MOLTEN_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPECTRAL_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JUNGLE_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SWORDFISH, Models.HANDHELD);
    }
}
