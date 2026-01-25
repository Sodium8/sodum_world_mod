package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarrotCarEntity extends MobEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public CarrotCarEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            return player.startRiding(this) ? ActionResult.SUCCESS : ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void travel(Vec3d pos) {
        if (this.isAlive()) {
            if (this.getControllingPassenger() instanceof LivingEntity passenger) {
                this.setYaw(passenger.getYaw());
                this.prevYaw = this.getYaw();
                this.setPitch(passenger.getPitch() * 0.5f);
                this.setRotation(this.getYaw(), this.getPitch());
                this.bodyYaw = this.getYaw();
                this.headYaw = this.bodyYaw;

                float forward = passenger.forwardSpeed;
                float sideways = passenger.sidewaysSpeed;

                if (forward <= 0) {
                    forward *= 0.25f;
                }

                this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                super.travel(new Vec3d(sideways, pos.y, forward));
                return;
            }
        }
        super.travel(pos);
    }
    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }
    @Override
    public void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        if (this.hasPassenger(passenger)) {
            updater.accept(passenger, this.getX(), this.getY(), this.getZ());
        }
    }
    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }
    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 && !this.hasPassengers()) {
            this.idleAnimationTimeout = 100;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }
}
