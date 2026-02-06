package net.sodium.sodiumworld.world.gen.CustomGen;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.sodium.sodiumworld.block.ModBlocks;

public class SulfurMountainsPatchFeature extends Feature<DefaultFeatureConfig> {

    public SulfurMountainsPatchFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random rand = context.getRandom();

        int radius = 3 + rand.nextInt(5);
        int replaceChance = 25;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx * dx + dz * dz > radius * radius) continue;

                BlockPos column = origin.add(dx, 0, dz);

                for (int dy = 8; dy >= -8; dy--) {
                    BlockPos pos = column.add(0, dy, 0);
                    BlockPos above = pos.up();

                    if (world.isAir(above) && !world.isAir(pos)) {

                        if (world.getBlockState(pos).isOf(Blocks.STONE)) {
                            if (rand.nextInt(100) < replaceChance) {
                                world.setBlockState(pos, ModBlocks.SULFUR.getDefaultState(), 3);

                                if (rand.nextInt(100) < 40) {
                                    BlockPos below = pos.down();
                                    if (world.getBlockState(below).isOf(Blocks.STONE)) {
                                        world.setBlockState(below, ModBlocks.SULFUR.getDefaultState(), 3);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}