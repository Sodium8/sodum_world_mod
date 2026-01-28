package net.sodium.sodiumworld.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.HitlerEntity;

public class HitlerModel <T extends HitlerEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer HITLER = new EntityModelLayer(Identifier.of(SodiumWorld.MOD_ID, "hitler"), "main");
    private final ModelPart bone6;
    private final ModelPart bone5;
    private final ModelPart bone4;
    private final ModelPart bone3;
    private final ModelPart bone2;
    private final ModelPart bone;
    private final ModelPart root;

    public HitlerModel(ModelPart root) {
        this.bone6 = root.getChild("bone6");
        this.bone5 = root.getChild("bone5");
        this.bone4 = root.getChild("bone4");
        this.bone3 = root.getChild("bone3");
        this.bone2 = root.getChild("bone2");
        this.bone = root.getChild("bone");
        this.root = root;
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
    public void setAngles(HitlerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(HitlerAnimation.animatiobegaetihrukaet, limbSwing, limbSwingAmount, 2f, 2.5f);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bone6.render(matrices, vertexConsumer, light, overlay, color);
        bone5.render(matrices, vertexConsumer, light, overlay, color);
        bone4.render(matrices, vertexConsumer, light, overlay, color);
        bone3.render(matrices, vertexConsumer, light, overlay, color);
        bone2.render(matrices, vertexConsumer, light, overlay, color);
        bone.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
