package net.sleepykairo.piratemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.item.custom.*;

public class ModItems {
    //public static final Item CANNONBALL = registerItem("cannonball", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PirateMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PirateMod.LOGGER.info("Registering Mod Items for " + PirateMod.MOD_ID);

    }

    public static final Item CANNONBALL = registerItem("cannonball", new CannonballItem(new FabricItemSettings().maxCount(1)));
    public static final Item DARTFISH = registerItem("dartfish", new DartfishItem(new FabricItemSettings().maxCount(16)));
    public static final Item POISON_DARTFISH = registerItem("poison_dartfish", new PoisonDartfishItem(new FabricItemSettings().maxCount(16)));

    public static final Item PLANT_MATTER = registerItem("plant_matter", new Item(new FabricItemSettings()));
    public static final Item CATALYST = registerItem("catalyst", new Item(new FabricItemSettings()));

    public static final Item FROST_INGOT = registerItem("frost_ingot", new Item(new FabricItemSettings()));
    public static final Item FIRE_INGOT = registerItem("fire_ingot", new Item(new FabricItemSettings()));
    public static final Item PLANT_INGOT = registerItem("plant_ingot", new Item(new FabricItemSettings()));
    public static final Item STORM_INGOT = registerItem("storm_ingot", new Item(new FabricItemSettings()));
    public static final Item OCEAN_INGOT = registerItem("ocean_ingot", new Item(new FabricItemSettings()));
    public static final Item DARK_INGOT = registerItem("dark_ingot", new Item(new FabricItemSettings()));
    public static final Item LIGHT_INGOT = registerItem("light_ingot", new Item(new FabricItemSettings()));

    public static final Item GOLDFISH = registerItem("goldfish", new Item(new FabricItemSettings().food(ModFoodComponents.GOLDFISH)));
    public static final Item RED_SUNFISH = registerItem("red_sunfish", new Item(new FabricItemSettings().food(ModFoodComponents.RED_SUNFISH)));
    public static final Item GREEN_SUNFISH = registerItem("green_sunfish", new Item(new FabricItemSettings().food(ModFoodComponents.GREEN_SUNFISH)));
    public static final Item EMERALD_FISH = registerItem("emerald_fish", new Item(new FabricItemSettings().food(ModFoodComponents.EMERALD_FISH)));


    public static final Item SAWFISH = registerItem(
            "sawfish", new AxeItem(ModToolMaterial.SAWFISH,8.0f,-3f, new FabricItemSettings()));
    public static final Item OBSIDIAN_SAWFISH = registerItem(
            "obsidian_sawfish", new AxeItem(ModToolMaterial.OBSIDIAN_SAWFISH,8.5f,-3f, new FabricItemSettings()));

    public static final Item GARDENERS_HOE = registerItem(
            "gardeners_hoe", new GardenersHoeItem(ModToolMaterial.GARDENERS_HOE,1,0f, new FabricItemSettings()));

    public static final Item SWORDFISH = registerItem(
            "swordfish", new SwordItem(ModToolMaterial.SWORDFISH_FULL,8,-2.4f, new FabricItemSettings()));
    public static final Item MOLTEN_SWORDFISH = registerItem(
            "molten_swordfish", new MoltenSwordfishItem(ModToolMaterial.SWORDFISH,7,-2.4f, new FabricItemSettings()));
    public static final Item SPECTRAL_SWORDFISH = registerItem(
            "spectral_swordfish", new SwordItem(ModToolMaterial.SWORDFISH,6,-2.2f, new FabricItemSettings()));
    public static final Item DIAMOND_SWORDFISH = registerItem(
            "diamond_swordfish", new DiamondSwordfishItem(ModToolMaterial.SWORDFISH,7,-2.5f, new FabricItemSettings()));
    public static final Item JUNGLE_SWORDFISH = registerItem(
            "jungle_swordfish", new JungleSwordfishItem(ModToolMaterial.SWORDFISH,5,-2.5f, new FabricItemSettings()));
    public static final Item NIGHTFIN_SWORDFISH = registerItem(
            "nightfin_swordfish", new NightfinSwordfishItem(ModToolMaterial.SWORDFISH_FULL,3,-2.7f, new FabricItemSettings()));

    public static final Item GOLDEN_FISHING_ROD = registerItem("golden_fishing_rod", new GoldenFishingRodItem(new FabricItemSettings()));
}
