package net.sodium.sodiumworld.entity.client.Portal;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.PortalEntity;

public class PortalRenderer extends MobEntityRenderer<PortalEntity, PortalModel<PortalEntity>> {
    public PortalRenderer(EntityRendererFactory.Context context) {
        super(context, new PortalModel<>(context.getPart(PortalModel.PORTAL)), 0.75f);
    }

    @Override
    public Identifier getTexture(PortalEntity entity) {
        return Identifier.of(SodiumWorld.MOD_ID, "textures/entity/portal/portal.png");
    }
}
