package net.sleepykairo.piratemod.entity.client.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.client.GhostModel;

@Environment(value= EnvType.CLIENT)
public class GhostFeatureRenderer<T extends Entity, M extends GhostModel<T>>
        extends EyesFeatureRenderer<T, M> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier(PirateMod.MOD_ID, "textures/entity/ghost/ghost.png"));

    public GhostFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture());
        ((Model)this.getContextModel()).render(matrices, vertexConsumer, 0x000000, OverlayTexture.DEFAULT_UV, 0.25f, 0.25f, 0.25f, 0.5f);
    }
}
