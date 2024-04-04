package net.sleepykairo.piratemod;

import net.fabricmc.api.ModInitializer;

import net.sleepykairo.piratemod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PirateMod implements ModInitializer {
	public static final String MOD_ID = "piratemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
	}
}