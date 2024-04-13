package net.sleepykairo.piratemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.sleepykairo.piratemod.blocks.ModBlocks;
import net.sleepykairo.piratemod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALCHEMY_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.CANNONBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARTFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.POISON_DARTFISH, Models.GENERATED);

        itemModelGenerator.register(ModItems.PLANT_MATTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATALYST, Models.GENERATED);

        itemModelGenerator.register(ModItems.FROST_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLANT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STORM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.OCEAN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHT_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.GOLDFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_SUNFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_SUNFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMERALD_FISH, Models.GENERATED);

        itemModelGenerator.register(ModItems.SAWFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OBSIDIAN_SAWFISH, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GARDENERS_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MOLTEN_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPECTRAL_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JUNGLE_SWORDFISH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NIGHTFIN_SWORDFISH, Models.HANDHELD);
    }
}
