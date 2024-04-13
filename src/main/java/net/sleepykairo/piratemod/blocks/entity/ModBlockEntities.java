package net.sleepykairo.piratemod.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.blocks.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<AlchemyTableBlockEntity> ALCHEMY_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PirateMod.MOD_ID, "alchemy_table_be"),
                    FabricBlockEntityTypeBuilder.create(AlchemyTableBlockEntity::new,
                            ModBlocks.ALCHEMY_TABLE).build(null));

    public static void registerBlockEntities() {
        PirateMod.LOGGER.info("Registering Block Entities for " + PirateMod.MOD_ID);
    }
}
