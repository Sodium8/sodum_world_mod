package net.sodium.sodiumworld.entity.client.Portal;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.PortalEntity;

public class PortalModel <T extends PortalEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer PORTAL = new EntityModelLayer(Identifier.of(SodiumWorld.MOD_ID, "portal"), "main");
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart root;
    public PortalModel(ModelPart root) {
        this.root = root;
        this.bone = root.getChild("bone");
        this.bone2 = root.getChild("bone2");
        this.bone3 = root.getChild("bone3");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(-1, -1).cuboid(15.0F, -36.0F, 0.5F, 14.0F, 35.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 31).cuboid(29.0F, -34.0F, 1.0F, 2.0F, 31.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 31).cuboid(13.0F, -34.0F, 1.0F, 2.0F, 31.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 35).cuboid(11.0F, -32.0F, 1.0F, 2.0F, 27.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 35).cuboid(31.0F, -32.0F, 1.0F, 2.0F, 27.0F, 0.0F, new Dilation(0.0F))
                .uv(8, 35).cuboid(33.0F, -29.0F, 1.0F, 2.0F, 21.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 35).cuboid(9.0F, -29.0F, 1.0F, 2.0F, 21.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-22.0F, 24.0F, 0.0F));

        ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(40, 0).cuboid(-5.0F, -5.0F, -1.0F, 10.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 0.0F));

        ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(42, 15).cuboid(-4.0F, -4.0F, -5.0F, 8.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 3.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bone.render(matrices, vertexConsumer, light, overlay, color);
        bone2.render(matrices, vertexConsumer, light, overlay, color);
        bone3.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.updateAnimation(entity.popaAnimationState, PortalAnimation.popa, animationProgress, 1f);
        this.updateAnimation(entity.idleAnimationState, PortalAnimation.idle, animationProgress, 1f);
    }
}
