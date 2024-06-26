package net.sleepykairo.piratemod.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.blocks.custom.AlchemyTableBlock;

public class ModBlocks {

    public static final Block ALCHEMY_TABLE = registerBlock("alchemy_table",
            new AlchemyTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block BOX_OF_GOLDFISH = registerBlock("box_of_goldfish",
            new AlchemyTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(PirateMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(PirateMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        PirateMod.LOGGER.info("Registering ModBlocks for " + PirateMod.MOD_ID);
    }
}
