package net.sodium.sodiumworld.entity.client.jopa;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.JopaEntity;

public class JopaModel <T extends JopaEntity> extends EntityModel<T> {
    public static final EntityModelLayer JOPA = new EntityModelLayer(Identifier.of(SodiumWorld.MOD_ID, "jopa"), "main");
    private final ModelPart bone2;
    private final ModelPart root;
    public JopaModel(ModelPart root) {
        this.bone2 = root.getChild("bone2");
        this.root = root;
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(40, 0).cuboid(-5.0F, -5.0F, -1.0F, 10.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(42, 15).cuboid(-4.0F, -4.0F, -2.0F, 8.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        root.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
