package net.sodium.sodiumworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.custom.PenisCropBlock;
import net.sodium.sodiumworld.block.custom.penis_holder_block;
import net.sodium.sodiumworld.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PENIS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BROKEN_PENIS_BLOCK);
        Identifier holderEmptyIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.PENIS_HOLDER_BLOCK, blockStateModelGenerator.modelCollector);
        Identifier holderFullIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.PENIS_HOLDER_BLOCK, "_full", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.PENIS_HOLDER_BLOCK)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(penis_holder_block.HOLDING, holderFullIdentifier, holderEmptyIdentifier)));
        blockStateModelGenerator.registerCrop(ModBlocks.PENIS_CROP, PenisCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.IRON_DILDO);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PENIS_VIRUS_BLOCK);
        blockStateModelGenerator.registerSingleton(ModBlocks.LEMON_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.LEMON_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PENIS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GIANT_CARROT_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLYAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPARKLING_WATER, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORTAL_GUN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORTAL_FLUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAGOUT_OF_PENISES, Models.GENERATED);
        itemModelGenerator.register(ModItems.PENIS_STAFF, Models.GENERATED);
    }
}
