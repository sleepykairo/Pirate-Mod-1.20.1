package net.sleepykairo.piratemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.util.ModModelPredicateProvider;

public class PirateModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.CANNONBALL_PROJECTILE, FlyingItemEntityRenderer::new);
        ModModelPredicateProvider.registerModModels();
    }
}
