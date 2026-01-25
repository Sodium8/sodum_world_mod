package net.sodium.sodiumworld.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.DildoEntity;
import net.sodium.sodiumworld.entity.custom.CarrotCarEntity;

public class ModEntities
{
    public static final EntityType<DildoEntity> DILDO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "dildo_entity"),
            EntityType.Builder.create(DildoEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());
    public static final EntityType<CarrotCarEntity> CARROT_CAR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "carrot_car_entity"),
            EntityType.Builder.create(CarrotCarEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());


    public static void registerModEntities() {
        SodiumWorld.LOGGER.info("Registering Mod Entities for " + SodiumWorld.MOD_ID);
    }
}
