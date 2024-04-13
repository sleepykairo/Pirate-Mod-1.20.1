package net.sleepykairo.piratemod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.sleepykairo.piratemod.PirateMod;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.entity.custom.BlokeEntity;
import net.sleepykairo.piratemod.entity.custom.CannonballProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.DartfishProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.PoisonDartfishProjectileEntity;

public class ModEntities {

    public static final EntityType<CannonballProjectileEntity> CANNONBALL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PirateMod.MOD_ID, "cannonball_projectile"),
            FabricEntityTypeBuilder.<CannonballProjectileEntity>create(SpawnGroup.MISC, CannonballProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<DartfishProjectileEntity> DARTFISH_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PirateMod.MOD_ID, "dartfish_projectile"),
            FabricEntityTypeBuilder.<DartfishProjectileEntity>create(SpawnGroup.MISC, DartfishProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());
    public static final EntityType<PoisonDartfishProjectileEntity> POISON_DARTFISH_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PirateMod.MOD_ID, "poison_dartfish_projectile"),
            FabricEntityTypeBuilder.<PoisonDartfishProjectileEntity>create(SpawnGroup.MISC, PoisonDartfishProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<BlokeEntity> BlOKE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PirateMod.MOD_ID, "bloke"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BlokeEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
}