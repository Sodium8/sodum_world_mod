package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.sodium.sodiumworld.entity.custom.goals.FollowEntityGoal;
import net.sodium.sodiumworld.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class HitlerEntity extends AnimalEntity{


    public HitlerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new TemptGoal(this, 3f, Ingredient.ofItems(ModItems.PENIS), false));

        this.goalSelector.add(2, new FollowEntityGoal<>(this, PigEntity.class, 3f, 16f, 1f));

        this.goalSelector.add(3, new WanderAroundFarGoal(this, 3f));

        this.goalSelector.add(4, new LookAroundGoal(this));
    }





    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
