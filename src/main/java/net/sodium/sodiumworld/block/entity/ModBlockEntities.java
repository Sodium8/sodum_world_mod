package net.sodium.sodiumworld.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.custom.DishBlockEntity;
import net.sodium.sodiumworld.block.entity.custom.PenisVirusEntity;

public class ModBlockEntities {
    public static final BlockEntityType<DishBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(SodiumWorld.MOD_ID, "dish_be"),
                    BlockEntityType.Builder.create(DishBlockEntity::new, ModBlocks.DISH).build(null));
    public static BlockEntityType<PenisVirusEntity> PENIS_VIRUS_ENTITY =
            Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(SodiumWorld.MOD_ID, "penis_virus_block_entity"),
            BlockEntityType.Builder.create(PenisVirusEntity::new, ModBlocks.PENIS_VIRUS_BLOCK).build(null)
    );

    public static void registerBlockEntities() {
        SodiumWorld.LOGGER.info("Registering Block Entities for " + SodiumWorld.MOD_ID);
    }
}