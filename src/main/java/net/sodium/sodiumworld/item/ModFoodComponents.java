package net.sodium.sodiumworld.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent RAGOUT_OF_PENISES = new FoodComponent.Builder().nutrition(10).saturationModifier(2).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 3), 1).build();
}
