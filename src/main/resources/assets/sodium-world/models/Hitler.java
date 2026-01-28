// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Hitler extends EntityModel<Entity> {
	private final ModelPart bone6;
	private final ModelPart bone5;
	private final ModelPart bone4;
	private final ModelPart bone3;
	private final ModelPart bone2;
	private final ModelPart bone;
	public Hitler(ModelPart root) {
		this.bone6 = root.getChild("bone6");
		this.bone5 = root.getChild("bone5");
		this.bone4 = root.getChild("bone4");
		this.bone3 = root.getChild("bone3");
		this.bone2 = root.getChild("bone2");
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone6 = modelPartData.addChild("bone6", ModelPartBuilder.create().uv(16, 48).cuboid(-1.0F, -12.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bone5 = modelPartData.addChild("bone5", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -12.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 12.0F, 0.0F));

		ModelPartData bone4 = modelPartData.addChild("bone4", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -12.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 12.0F, 0.0F));

		ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, -12.0F, -3.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 12.0F, 0.0F));

		ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(32, 0).cuboid(-1.0F, -10.0F, -3.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 2.0F, -2.0F));

		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -12.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone6.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone5.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}