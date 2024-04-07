package net.sleepykairo.piratemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

public class ModItemGroups {
    public static final ItemGroup PIRATE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PirateMod.MOD_ID, "pirate"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.pirate"))
                    .icon(() -> new ItemStack(ModItems.CANNONBALL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CANNONBALL);

                        entries.add(ModItems.GOLDFISH);
                        entries.add(ModItems.RED_SUNFISH);
                        entries.add(ModItems.GREEN_SUNFISH);
                        entries.add(ModItems.EMERALD_FISH);

                        entries.add(ModItems.SAWFISH);
                        entries.add(ModItems.OBSIDIAN_SAWFISH);

                        entries.add(ModItems.SWORDFISH);
                        entries.add(ModItems.MOLTEN_SWORDFISH);
                        entries.add(ModItems.SPECTRAL_SWORDFISH);
                        entries.add(ModItems.DIAMOND_SWORDFISH);
                        entries.add(ModItems.JUNGLE_SWORDFISH);
                        entries.add(ModItems.NIGHTFIN_SWORDFISH);

                    }).build());

    public static void registerItemGroups() {
        PirateMod.LOGGER.info("Registering Item Groups for " + PirateMod.MOD_ID);
    }
}
