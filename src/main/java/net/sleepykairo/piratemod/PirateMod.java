package net.sleepykairo.piratemod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.blocks.ModBlocks;
import net.sleepykairo.piratemod.blocks.entity.ModBlockEntities;
import net.sleepykairo.piratemod.effect.ModEffects;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.entity.custom.*;
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
		FabricDefaultAttributeRegistry.register(ModEntities.CHARRED_GHAST, CharredGhastEntity.createCharredGhastAttributes());

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
				if (world instanceof ServerWorld serverWorld) {
					serverWorld.spawnParticles(
							ParticleTypes.EXPLOSION,
							position.getX(), position.getY(), position.getZ(),
							2,
							0.5, 0.5,0.5,
							0.75
					);
					serverWorld.spawnParticles(
							ParticleTypes.SMOKE,
							position.getX(), position.getY(), position.getZ(),
							15,
							0.25, 0.25,0.25,
							0.5
					);
					serverWorld.spawnParticles(
							ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
							position.getX(), position.getY(), position.getZ(),
							5,
							0.25, 0.25,0.25,
							0.05
					);
					world.playSound(null, position.getX(), position.getY(), position.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2.0f, 1.0f);
				}
				return Util.make(new CannonballProjectileEntity(world, position.getX(), position.getY(), position.getZ()), entity -> entity.setItem(stack));
			}

			@Override
			protected float getForce() {
				return 3f;
			}

			@Override
			protected float getVariation() {
				return 0;
			}
		});
		DispenserBlock.registerBehavior(ModItems.GRAPESHOT, new ProjectileDispenserBehavior(){

			@Override
			public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
				ServerWorld world = pointer.getWorld();
				Position position = DispenserBlock.getOutputLocation(pointer);
				Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
				for (int projectilesFired = 0; projectilesFired < 10; projectilesFired++) {
					ProjectileEntity projectileEntity = this.createProjectile(world, position, stack);
					projectileEntity.setVelocity(direction.getOffsetX(), (float)direction.getOffsetY() + 0.1f, direction.getOffsetZ(), this.getForce(), this.getVariation());
					world.spawnEntity(projectileEntity);
				}
				world.spawnParticles(
						ParticleTypes.EXPLOSION,
						position.getX(), position.getY(), position.getZ(),
						2,
						0.5, 0.5,0.5,
						0.75
				);
				world.spawnParticles(
						ParticleTypes.SMOKE,
						position.getX(), position.getY(), position.getZ(),
						15,
						0.25, 0.25,0.25,
						0.5
				);
				world.spawnParticles(
						ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
						position.getX(), position.getY(), position.getZ(),
						5,
						0.25, 0.25,0.25,
						0.05
				);
				world.playSound(null, position.getX(), position.getY(), position.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2.0f, 1.0f);
				stack.decrement(1);
				return stack;
			}

			@Override
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				MusketBallEntity projectileEntity = new MusketBallEntity(world, position.getX(), position.getY(), position.getZ());
				projectileEntity.setDamage(18f);

				return projectileEntity;
			}

			@Override
			protected float getForce() {
				return 2.5f;
			}

			@Override
			protected float getVariation() {
				return 7.5f;
			}
		});
	}
}