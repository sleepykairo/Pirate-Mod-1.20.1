package net.sleepykairo.piratemod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.sleepykairo.piratemod.PirateMod;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.entity.custom.CannonballProjectileEntity;

public class ModEntities {

    public static final EntityType<CannonballProjectileEntity> CANNONBALL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PirateMod.MOD_ID, "cannonball_projectile"),
            FabricEntityTypeBuilder.<CannonballProjectileEntity>create(SpawnGroup.MISC, CannonballProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
}