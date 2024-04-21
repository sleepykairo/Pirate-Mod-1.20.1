package net.sleepykairo.piratemod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.sleepykairo.piratemod.entity.custom.GhostEntity;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class GhostModel<T extends GhostEntity> extends SinglePartEntityModel<T> {
	private final ModelPart Body;
	public GhostModel(ModelPart root) {
		this.Body = root.getChild("Body");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData Chest = Body.addChild("Chest", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 1.0F));

		ModelPartData chest_r1 = Chest.addChild("chest_r1", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -13.0F, 1.0F));

		ModelPartData LeftArm = Body.addChild("LeftArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -12.0F, 1.0F));

		ModelPartData leftarm_r1 = LeftArm.addChild("leftarm_r1", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData RightArm = Body.addChild("RightArm", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, -12.0F, 1.0F));

		ModelPartData rightarm_r1 = RightArm.addChild("rightarm_r1", ModelPartBuilder.create().uv(24, 16).cuboid(-1.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return null;
	}

	@Override
	public void setAngles(GhostEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}