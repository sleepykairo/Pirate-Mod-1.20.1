package net.sleepykairo.piratemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.item.custom.CannonballItem;
import net.sleepykairo.piratemod.item.custom.DiamondSwordfishItem;
import net.sleepykairo.piratemod.item.custom.JungleSwordfishItem;
import net.sleepykairo.piratemod.item.custom.MoltenSwordfishItem;

public class ModItems {
    //public static final Item CANNONBALL = registerItem("cannonball", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PirateMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PirateMod.LOGGER.info("Registering Mod Items for " + PirateMod.MOD_ID);

    }

    public static final Item CANNONBALL = registerItem("cannonball", new CannonballItem(new FabricItemSettings().maxCount(1)));

    public static final Item SAWFISH = registerItem(
            "sawfish", new AxeItem(ModToolMaterial.SAWFISH,8.0f,-3f, new FabricItemSettings()));
    public static final Item OBSIDIAN_SAWFISH = registerItem(
            "obsidian_sawfish", new AxeItem(ModToolMaterial.OBSIDIAN_SAWFISH,8.5f,-3f, new FabricItemSettings()));

    public static final Item MOLTEN_SWORDFISH = registerItem(
            "molten_swordfish", new MoltenSwordfishItem(ModToolMaterial.SWORDFISH,7,-2.4f, new FabricItemSettings()));
    public static final Item SPECTRAL_SWORDFISH = registerItem(
            "spectral_swordfish", new SwordItem(ModToolMaterial.SWORDFISH_FULL,7,-2.2f, new FabricItemSettings()));
    public static final Item DIAMOND_SWORDFISH = registerItem(
            "diamond_swordfish", new DiamondSwordfishItem(ModToolMaterial.SWORDFISH,7,-2.5f, new FabricItemSettings()));
    public static final Item JUNGLE_SWORDFISH = registerItem(
            "jungle_swordfish", new JungleSwordfishItem(ModToolMaterial.SWORDFISH,5,-2.5f, new FabricItemSettings()));
}
