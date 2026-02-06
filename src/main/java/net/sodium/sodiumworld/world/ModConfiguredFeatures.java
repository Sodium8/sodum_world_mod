package net.sodium.sodiumworld.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.util.LemonFruitDecorator;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEMON_WOOD_KEY = registerKey("lemonwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHALK_BEACH_KEY = registerKey("chalk_beach");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SULFUR_MOUNTAINS_KEY = registerKey("sulfur_mountains");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, LEMON_WOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.of(ModBlocks.LEMON_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(2), 6),
                new TwoLayersFeatureSize(1, 0, 2)
        ).decorators(List.of(
                new LemonFruitDecorator(0.12f)
        ))
                .build());
        context.register(CHALK_BEACH_KEY,
                new ConfiguredFeature<>(CustomModFeatures.CHALK_BEACH_PATCH, DefaultFeatureConfig.DEFAULT));
        context.register(SULFUR_MOUNTAINS_KEY,
                new ConfiguredFeature<>(CustomModFeatures.SULFUR_MOUNTAINS_PATCH, DefaultFeatureConfig.DEFAULT));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(SodiumWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}