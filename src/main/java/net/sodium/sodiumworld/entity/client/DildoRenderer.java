package net.sodium.sodiumworld.entity.client;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.entity.custom.DildoEntity;

public class DildoRenderer extends EntityRenderer<DildoEntity> {
    public DildoRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(DildoEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(DildoEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
