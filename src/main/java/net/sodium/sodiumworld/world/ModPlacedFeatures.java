package net.sodium.sodiumworld.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> LEMONWOOD_PLACED_KEY = registerKey("lemonwood_placed");
    public static final RegistryKey<PlacedFeature> CHALK_BEACH_PATCH_PLACED =
            registerKey("chalk_beach_patch_placed");
    public static final RegistryKey<PlacedFeature> SULFUR_MOUNTAINS_PATCH_PLACED =
            registerKey("sulfur_mountains_patch_placed");
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(context, LEMONWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_WOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.005f, 1), ModBlocks.LEMON_SAPLING));

        var entry = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
                .getOrThrow(ModConfiguredFeatures.CHALK_BEACH_KEY);

        register(context, CHALK_BEACH_PATCH_PLACED, entry,
                List.of(
                        CountPlacementModifier.of(1),
                        RarityFilterPlacementModifier.of(4),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()
                ));

        var entry1 = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
                .getOrThrow(ModConfiguredFeatures.SULFUR_MOUNTAINS_KEY);

        register(context, SULFUR_MOUNTAINS_PATCH_PLACED, entry1,
                List.of(
                        CountPlacementModifier.of(1),
                        RarityFilterPlacementModifier.of(8),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()
                ));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(SodiumWorld.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}