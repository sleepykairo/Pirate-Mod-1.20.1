package net.sleepykairo.piratemod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.blocks.ModBlocks;
import net.sleepykairo.piratemod.effect.FrostWalkerEffect;
import net.sleepykairo.piratemod.effect.ModEffects;
import net.sleepykairo.piratemod.item.ModItemGroups;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.potion.ModPotions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PirateMod implements ModInitializer {
	public static final String MOD_ID = "piratemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();

		ModEffects.registerEffects();
		ModPotions.registerPotions();
	}
}