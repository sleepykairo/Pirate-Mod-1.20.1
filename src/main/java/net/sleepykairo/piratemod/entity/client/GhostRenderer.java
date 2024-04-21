package net.sleepykairo.piratemod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.custom.BlokeEntity;
import net.sleepykairo.piratemod.entity.custom.GhostEntity;

public class GhostRenderer extends MobEntityRenderer<GhostEntity, GhostModel<GhostEntity>> {
    private static final Identifier TEXTURE = new Identifier(PirateMod.MOD_ID, "textures/entity/ghost/ghost.png");
    public GhostRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostModel<>(context.getPart(ModModelLayers.GHOST)), 1);
    }

    @Override
    public Identifier getTexture(GhostEntity entity) {
        return TEXTURE;
    }
}
