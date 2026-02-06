package net.sodium.sodiumworld.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.sodium.sodiumworld.world.ModPlacedFeatures;

public class ModMountainsGeneration {
    public static void addSulfurToMountains() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.STONY_PEAKS),
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.SULFUR_MOUNTAINS_PATCH_PLACED
        );
    }
}
