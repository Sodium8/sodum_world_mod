package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class PortalEntity extends MobEntity {
    public PortalEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    public final AnimationState popaAnimationState = new AnimationState();
    private int popaAnimationTimeout = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    private void setupAnimationStates() {
        if (this.popaAnimationTimeout <= 0 && !this.hasPassengers()) {
            this.popaAnimationTimeout = 100;
            this.popaAnimationState.start(this.age);
        } else {
            if (popaAnimationTimeout > 15) {
                this.idleAnimationState.start(this.age);
            }
            --this.popaAnimationTimeout;
        }
    }
    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100);
    }
    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }
}
