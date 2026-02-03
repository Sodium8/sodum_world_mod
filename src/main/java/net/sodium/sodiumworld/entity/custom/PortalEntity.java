package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class PortalEntity extends MobEntity {
    public PortalEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    public static final TrackedData<Float> DATA_SIZE_X = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);
    public static final TrackedData<Float> DATA_SIZE_Y = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);
    public static final TrackedData<Float> DATA_SIZE_Z = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);
    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100);
    }
    @Override
    protected void initDataTracker(net.minecraft.entity.data.DataTracker.Builder builder) {
        builder.add(DATA_SIZE_X, 1.625f);
        builder.add(DATA_SIZE_Y, 2.1875f);
        builder.add(DATA_SIZE_Z, 0.0725f);
        super.initDataTracker(builder);
    }


    @Override
    protected Box calculateBoundingBox() {
        float sizeX = dataTracker.get(DATA_SIZE_X);
        float sizeY = dataTracker.get(DATA_SIZE_Y);
        float sizeZ = dataTracker.get(DATA_SIZE_Z);
        return Box.of(this.getPos().add(0, sizeY/2, -sizeZ-(sizeZ-0.0625f)/2), sizeX, sizeY,sizeZ);
    }

    public void setHitboxSizes(float sizeX, float sizeY, float sizeZ) {
        if (this.getWorld().isClient()) return;
        dataTracker.set(DATA_SIZE_X, sizeX);
        dataTracker.set(DATA_SIZE_Y, sizeY);
        dataTracker.set(DATA_SIZE_Z, sizeZ);
        this.refreshPosition();
    }
}
