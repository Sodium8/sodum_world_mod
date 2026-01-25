package net.sodium.sodiumworld.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.CarrotCarEntity;

public class CarrotCarRenderer extends MobEntityRenderer<CarrotCarEntity, CarrotCarModel<CarrotCarEntity>> {
    public CarrotCarRenderer(EntityRendererFactory.Context context) {
        super(context, new CarrotCarModel<>(context.getPart(CarrotCarModel.CAR_CARROT)), 0.75f);
    }
    @Override
    protected void setupTransforms(CarrotCarEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90));
        super.setupTransforms(entity, matrices, animationProgress, bodyYaw, tickDelta, scale);
    }
    @Override
    public Identifier getTexture(CarrotCarEntity entity) {
        return Identifier.of(SodiumWorld.MOD_ID, "textures/entity/carrot_car/carrot_car.png");
    }

    @Override
    public void render(CarrotCarEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2, 2, 2);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
