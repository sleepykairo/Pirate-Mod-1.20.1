package net.sleepykairo.piratemod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.blocks.ModBlocks;
import net.sleepykairo.piratemod.blocks.entity.ModBlockEntities;
import net.sleepykairo.piratemod.effect.ModEffects;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.entity.custom.BlokeEntity;
import net.sleepykairo.piratemod.entity.custom.CannonballProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.DartfishProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.GhostEntity;
import net.sleepykairo.piratemod.item.ModItemGroups;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.potion.ModPotions;
import net.sleepykairo.piratemod.screen.ModScreenHandlers;
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

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		//GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.BlOKE, BlokeEntity.createBlokeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GHOST, GhostEntity.createGhostAttributes());

		DispenserBlock.registerBehavior(ModItems.DARTFISH, new ProjectileDispenserBehavior(){

			@Override
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				DartfishProjectileEntity persistentProjectileEntity = new DartfishProjectileEntity(world, position.getX(), position.getY(), position.getZ());
				persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
				persistentProjectileEntity.setVelocity(0.0f, 10.5f, 1.0f);
				return persistentProjectileEntity;
				//return Util.make(new DartfishProjectileEntity(world, position.getX(), position.getY(), position.getZ()), entity -> entity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED);
			}
		});

		DispenserBlock.registerBehavior(ModItems.CANNONBALL, new ProjectileDispenserBehavior(){

			@Override
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				return Util.make(new CannonballProjectileEntity(world, position.getX(), position.getY(), position.getZ()), entity -> entity.setItem(stack));
			}
		});
	}
}