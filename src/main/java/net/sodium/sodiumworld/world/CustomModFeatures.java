package net.sodium.sodiumworld.world;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.world.gen.CustomGen.ChalkBeachPatchFeature;
import net.sodium.sodiumworld.world.gen.CustomGen.SulfurMountainsPatchFeature;

public class CustomModFeatures {
    public static final Feature<DefaultFeatureConfig> CHALK_BEACH_PATCH =
            new ChalkBeachPatchFeature(DefaultFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> SULFUR_MOUNTAINS_PATCH =
            new SulfurMountainsPatchFeature(DefaultFeatureConfig.CODEC);

    public static void registerFeatures() {
        Registry.register(Registries.FEATURE, Identifier.of(SodiumWorld.MOD_ID, "chalk_beach_patch"), CHALK_BEACH_PATCH);
        Registry.register(Registries.FEATURE, Identifier.of(SodiumWorld.MOD_ID, "sulfur_mountains"), SULFUR_MOUNTAINS_PATCH);
    }
}