package net.sleepykairo.piratemod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.entity.custom.BlokeEntity;

public class BlokeRenderer extends MobEntityRenderer<BlokeEntity, BlokeModel<BlokeEntity>> {
    private static final Identifier TEXTURE = new Identifier(PirateMod.MOD_ID, "textures/entity/bloke/bloke.png");
    public BlokeRenderer(EntityRendererFactory.Context context) {
        super(context, new BlokeModel<>(context.getPart(ModModelLayers.BLOKE)), 1);
    }

    @Override
    public Identifier getTexture(BlokeEntity entity) {
        return TEXTURE;
    }

/*    @Override
    protected void scale(BlokeEntity entity, MatrixStack matrices, float amount) {
        float f = entity.getHealth() + 1;
        matrices.scale(f, f, f);
    }*/
}
