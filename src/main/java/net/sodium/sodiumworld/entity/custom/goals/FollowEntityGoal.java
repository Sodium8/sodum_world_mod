package net.sodium.sodiumworld.entity.custom.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class FollowEntityGoal<T extends LivingEntity> extends Goal {
    private final MobEntity mob;
    private final Class<T> targetClass;
    private T targetEntity;
    private final double speed;
    private final float range;
    private final float stopDistance;
    private final Predicate<LivingEntity> targetPredicate;

    public FollowEntityGoal(MobEntity mob, Class<T> targetClass, double speed, float range, float stopDistance) {
        this.mob = mob;
        this.targetClass = targetClass;
        this.speed = speed;
        this.range = range;
        this.stopDistance = stopDistance;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));

        this.targetPredicate = (entity) -> entity.isAlive() && mob.getVisibilityCache().canSee(entity);
    }

    @Override
    public boolean canStart() {
        List<T> targets = this.mob.getWorld().getEntitiesByClass(
                this.targetClass,
                this.mob.getBoundingBox().expand(range),
                targetPredicate
        );

        if (!targets.isEmpty()) {
            // Берем ближайшую
            this.targetEntity = targets.stream()
                    .min((e1, e2) -> Double.compare(mob.squaredDistanceTo(e1), mob.squaredDistanceTo(e2)))
                    .orElse(null);
            return this.targetEntity != null;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return targetEntity != null && targetEntity.isAlive() && mob.squaredDistanceTo(targetEntity) < (range * range);
    }

    @Override
    public void stop() {
        this.targetEntity = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.mob.getLookControl().lookAt(this.targetEntity, 10.0F, (float)this.mob.getMaxLookPitchChange());

        double distanceSq = this.mob.squaredDistanceTo(this.targetEntity);
        if (distanceSq > (stopDistance * stopDistance)) {
            this.mob.getNavigation().startMovingTo(this.targetEntity, this.speed);
        } else {
            this.mob.getNavigation().stop();
        }
    }
}