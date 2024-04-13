package net.sleepykairo.piratemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.client.render.entity.DartfishProjectileEntityRenderer;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.entity.client.BlokeRenderer;
import net.sleepykairo.piratemod.entity.client.ModModelLayers;
import net.sleepykairo.piratemod.entity.client.BlokeModel;
import net.sleepykairo.piratemod.entity.custom.CannonballProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.DartfishProjectileEntity;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.screen.AlchemyTableScreen;
import net.sleepykairo.piratemod.screen.ModScreenHandlers;
import net.sleepykairo.piratemod.util.ModModelPredicateProvider;

public class PirateModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicateProvider.registerModModels();

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BLOKE, BlokeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CANNONBALL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.DARTFISH_PROJECTILE, DartfishProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BlOKE, BlokeRenderer::new);

        HandledScreens.register(ModScreenHandlers.ALCHEMY_TABLE_SCREEN_HANDLER, AlchemyTableScreen::new);
    }
}
