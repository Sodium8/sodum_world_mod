package net.sodium.sodiumworld.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.custom.GasPipeBlock;
import net.sodium.sodiumworld.block.entity.custom.*;

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
    public static BlockEntityType<LemonCropEntity> LEMON_CROP_ENTITY =
                Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(SodiumWorld.MOD_ID, "lemon_crop_block_entity"),
                BlockEntityType.Builder.create(LemonCropEntity::new, ModBlocks.LEMON_CROP).build(null)
                );
    public static BlockEntityType<BurnerBlockEntity> BURNER_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "burner_block_entity"),
                    BlockEntityType.Builder.create(BurnerBlockEntity::new, ModBlocks.BURNER_BLOCK).build(null)
            );
    public static BlockEntityType<GasContainerEntity> GAS_CONTAINER_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "gas_container_entity"),
                    BlockEntityType.Builder.create(GasContainerEntity::new, ModBlocks.GAS_CONTAINER).build(null)
            );
    public static BlockEntityType<ElectrolizerBlockEntity> ELECTROLIZER_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "electrolizer_block_entity"),
                    BlockEntityType.Builder.create(ElectrolizerBlockEntity::new, ModBlocks.ELECTROLIZER_BLOCK).build(null)
            );
    public static BlockEntityType<GasPipeEntity> GAS_PIPE_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "gas_pipe_block_entity"),
                    BlockEntityType.Builder.create(GasPipeEntity::new, ModBlocks.GAS_PIPE).build(null)
            );
    public static BlockEntityType<GasPumpEntity> GAS_PUMP_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "gas_pump_block_entity"),
                    BlockEntityType.Builder.create(GasPumpEntity::new, ModBlocks.GAS_PUMP).build(null)
            );

    public static void registerBlockEntities() {
        SodiumWorld.LOGGER.info("Registering Block Entities for " + SodiumWorld.MOD_ID);
    }
}