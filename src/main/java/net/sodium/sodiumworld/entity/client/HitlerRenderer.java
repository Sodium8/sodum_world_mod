package net.sodium.sodiumworld.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.HitlerEntity;

public class HitlerRenderer extends MobEntityRenderer<HitlerEntity, HitlerModel<HitlerEntity>> {
    public HitlerRenderer(EntityRendererFactory.Context context) {
        super(context, new HitlerModel<>(context.getPart(HitlerModel.HITLER)), 0.75f);
    }

    @Override
    public Identifier getTexture(HitlerEntity entity) {
        return Identifier.of(SodiumWorld.MOD_ID, "textures/entity/hitler/hitler.png");
    }
}
