package net.sodium.sodiumworld.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.*;

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
    public static final EntityType<HitlerEntity> HITLER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "hitler_entity"),
            EntityType.Builder.create(HitlerEntity::new, SpawnGroup.MISC)
                    .dimensions(1f, 2f).build());
    public static final EntityType<PortalEntity> PORTAL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "portal"),
            EntityType.Builder.create(PortalEntity::new, SpawnGroup.MISC)
                    .dimensions(1.625f, 2.1875f)
                    .build()
    );
    public static final EntityType<JopaEntity> JOPA = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "jopa"),
            EntityType.Builder.create(JopaEntity::new, SpawnGroup.MISC)
                    .build()
    );


    public static void registerModEntities() {
        SodiumWorld.LOGGER.info("Registering Mod Entities for " + SodiumWorld.MOD_ID);
    }
}
