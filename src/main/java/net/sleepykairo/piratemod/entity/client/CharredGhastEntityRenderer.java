package net.sleepykairo.piratemod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.custom.CharredGhastEntity;

//@Environment(value=EnvType.CLIENT)
public class CharredGhastEntityRenderer
        extends MobEntityRenderer<CharredGhastEntity, CharredGhastEntityModel<CharredGhastEntity>> {
    private static final Identifier TEXTURE = new Identifier(PirateMod.MOD_ID,"textures/entity/charred_ghast/charred_ghast.png");
    private static final Identifier ANGRY_TEXTURE = new Identifier(PirateMod.MOD_ID,"textures/entity/charred_ghast/charred_ghast_shooting.png");

    public CharredGhastEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CharredGhastEntityModel<>(context.getPart(ModModelLayers.CHARRED_GHAST)), 1.5f);
    }

    @Override
    public Identifier getTexture(CharredGhastEntity charredGhastEntity) {
        if (charredGhastEntity.isShooting()) {
            return ANGRY_TEXTURE;
        }
        return TEXTURE;
    }

    @Override
    protected void scale(CharredGhastEntity charredGhast, MatrixStack matrixStack, float f) {
        float g = 1.0f;
        float h = 4.5f;
        float i = 4.5f;
        matrixStack.scale(4.5f, 4.5f, 4.5f);
    }
}