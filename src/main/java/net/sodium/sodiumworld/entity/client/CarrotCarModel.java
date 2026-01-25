package net.sodium.sodiumworld.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.CarrotCarEntity;

public class CarrotCarModel<T extends CarrotCarEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer CAR_CARROT = new EntityModelLayer(Identifier.of(SodiumWorld.MOD_ID, "carrot_car"), "main");
    private final ModelPart bone5;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone;
    private final ModelPart root;

    public CarrotCarModel(ModelPart root) {
        this.root = root;
        this.bone5 = root.getChild("bone5");
        this.bone2 = root.getChild("bone2");
        this.bone3 = root.getChild("bone3");
        this.bone4 = root.getChild("bone4");
        this.bone = root.getChild("bone");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone5 = modelPartData.addChild("bone5", ModelPartBuilder.create().uv(20, 20).cuboid(14.0F, -4.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 12).cuboid(10.0F, -5.0F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(4.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(2.0F, -7.0F, -3.0F, 2.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(1.0F, -4.0F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(22, -2).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 21.0F, -1.0F));

        ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(6, 4).cuboid(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 21.5F, 4.0F));

        ModelPartData cube_r1 = bone2.addChild("cube_r1", ModelPartBuilder.create().uv(6, 4).cuboid(-3.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(6, 4).cuboid(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 21.5F, -4.0F));

        ModelPartData cube_r2 = bone3.addChild("cube_r2", ModelPartBuilder.create().uv(6, 4).cuboid(-3.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData bone4 = modelPartData.addChild("bone4", ModelPartBuilder.create().uv(6, 4).cuboid(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 21.5F, -4.0F));

        ModelPartData cube_r3 = bone4.addChild("cube_r3", ModelPartBuilder.create().uv(6, 4).cuboid(-3.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(6, 4).cuboid(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 21.5F, 4.0F));

        ModelPartData cube_r4 = bone.addChild("cube_r4", ModelPartBuilder.create().uv(6, 4).cuboid(-3.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, 0.0F, 0.0F, 0.0F, -1.5708F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bone5.render(matrices, vertexConsumer, light, overlay, color);
        bone2.render(matrices, vertexConsumer, light, overlay, color);
        bone3.render(matrices, vertexConsumer, light, overlay, color);
        bone4.render(matrices, vertexConsumer, light, overlay, color);
        bone.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(CarrotCarEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(CarrotCarAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, CarrotCarAnimation.idle, ageInTicks, 1f);
    }
}
