package net.sleepykairo.piratemod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.sleepykairo.piratemod.entity.custom.BlokeEntity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BlokeModel<T extends BlokeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	public BlokeModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 10).cuboid(-0.5F, -25.0F, -0.5F, 1.0F, 15.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = right_arm.addChild("cube_r1", ModelPartBuilder.create().uv(8, 10).cuboid(-0.5F, -10.0F, -1.25F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 3.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, -2.0F));

		ModelPartData cube_r2 = left_arm.addChild("cube_r2", ModelPartBuilder.create().uv(4, 10).cuboid(-0.5F, -10.0F, -0.75F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData leg = body.addChild("leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = leg.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, -2.0F));

		ModelPartData cube_r3 = right_leg.addChild("cube_r3", ModelPartBuilder.create().uv(12, 0).cuboid(-0.5F, -10.0F, -1.25F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 5.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData left_leg = leg.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 3.0F));

		ModelPartData cube_r4 = left_leg.addChild("cube_r4", ModelPartBuilder.create().uv(12, 11).cuboid(-0.5F, -10.0F, -0.75F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 2).cuboid(0.0F, 0.75F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(15, 9).cuboid(0.0F, 1.75F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, 0.75F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.5F, -2.0F, -1.5F, 1.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(7, 2).cuboid(0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -4.0F, -1.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(BlokeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return body;
	}
}