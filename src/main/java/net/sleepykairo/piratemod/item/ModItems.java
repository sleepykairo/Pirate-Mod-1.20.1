package net.sleepykairo.piratemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.item.custom.CannonballItem;

public class ModItems {
    //public static final Item CANNONBALL = registerItem("cannonball", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PirateMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PirateMod.LOGGER.info("Registering Mod Items for " + PirateMod.MOD_ID);

    }

    public static final Item CANNONBALL = registerItem("cannonball", new CannonballItem(new FabricItemSettings().maxCount(1)));
}
