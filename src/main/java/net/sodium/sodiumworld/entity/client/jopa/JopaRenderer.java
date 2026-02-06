package net.sodium.sodiumworld.entity.client.jopa;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.JopaEntity;
import net.sodium.sodiumworld.entity.custom.PortalEntity;

public class JopaRenderer extends EntityRenderer<JopaEntity> {
    public final JopaModel<JopaEntity> model;

    public JopaRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new JopaModel<>(context.getPart(JopaModel.JOPA));
    }

    @Override
    public Identifier getTexture(JopaEntity entity) {
        return Identifier.of(SodiumWorld.MOD_ID, "textures/entity/portal/portal.png");
    }

    @Override
    public void render(
            JopaEntity entity,
            float entityYaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {
        matrices.push();
        matrices.translate(0.0, 1.09375, 0.0);

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-entityYaw));

        model.setAngles(entity, 0.0F, 0.0F, entity.age + tickDelta, entityYaw, entity.getPitch());

        VertexConsumer buffer = vertexConsumers.getBuffer(model.getLayer(getTexture(entity)));
        matrices.scale(entity.SCALEX, -1, entity.SCALEZ);

        model.render(matrices, buffer, light, OverlayTexture.DEFAULT_UV, 0xFFFFFFFF);

        matrices.pop();

        super.render(entity, entityYaw, tickDelta, matrices, vertexConsumers, light);
    }
}