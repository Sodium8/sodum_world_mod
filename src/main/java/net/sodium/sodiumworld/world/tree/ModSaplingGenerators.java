package net.sodium.sodiumworld.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator LEMONWOOD = new SaplingGenerator(SodiumWorld.MOD_ID+":lemonwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LEMON_WOOD_KEY), Optional.empty());
}
