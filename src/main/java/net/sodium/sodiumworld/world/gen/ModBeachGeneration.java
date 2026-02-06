package net.sodium.sodiumworld.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.sodium.sodiumworld.world.ModPlacedFeatures;

public class ModBeachGeneration {
    public static void addChalkToBeaches() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BEACH),
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.CHALK_BEACH_PATCH_PLACED
        );
    }
}
