package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Set;

public class PortalEntity extends DisplayEntity {
    public PortalEntity(EntityType<PortalEntity> entityType, World world) {
        super(entityType, world);
    }
    public static final TrackedData<Float> DATA_SIZE_X = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);
    public static final TrackedData<Float> DATA_SIZE_Y = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);
    public static final TrackedData<Float> DATA_SIZE_Z = DataTracker.registerData(PortalEntity.class,
            TrackedDataHandlerRegistry.FLOAT);

    @Override
    protected void refreshData(boolean shouldLerp, float lerpProgress) {

    }
    public boolean isEntityInsidePortal(Entity entity) {
        Vec3d pos = entity.getPos();
        Vec3d local = pos.subtract(this.getPos());

        float yawRad = (float) Math.toRadians(this.getYaw());
        double localX =  local.x * Math.cos(yawRad) + local.z * Math.sin(yawRad);
        double localZ = -local.x * Math.sin(yawRad) + local.z * Math.cos(yawRad);

        float halfWidth = getWidth() / 2f;
        float halfDepth = getWidth() / 2f;

        return Math.abs(localX) <= halfWidth &&
                Math.abs(localZ) <= halfDepth &&
                local.y >= -getHeight() / 2f &&
                local.y <= getHeight() / 2f;
    }

    @Override
    protected void initDataTracker(net.minecraft.entity.data.DataTracker.Builder builder) {
        builder.add(DATA_SIZE_X, 1.625f);
        builder.add(DATA_SIZE_Y, 2.1875f);
        builder.add(DATA_SIZE_Z, 0.0725f);
        super.initDataTracker(builder);
    }
    @Override
    public boolean isPushable() {
        return false;               // ← нельзя толкать
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
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

    @Override
    public void tick() {
        PlayerEntity plr = this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), 10, false);
        if (plr != null) {
            if (isEntityInsidePortal(plr)) {
                if (!this.getWorld().isClient()) {
                    plr.teleport((ServerWorld) this.getWorld(), this.getX()+random.nextBetween(0, 1000), 90, this.getZ()+random.nextBetween(0, 1000), Set.of(), 0, 0);
                }
            }
        }
        super.tick();
    }
}
