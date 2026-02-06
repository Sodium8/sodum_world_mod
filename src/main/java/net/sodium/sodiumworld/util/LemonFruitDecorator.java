package net.sodium.sodiumworld.util;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.sodium.sodiumworld.block.ModBlocks;

public class LemonFruitDecorator extends TreeDecorator {
    public static final MapCodec<LemonFruitDecorator> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(d -> d.probability)
            ).apply(instance, LemonFruitDecorator::new)
    );

    private final float probability;   // e.g. 0.15f → ~15% of leaf positions get a lemon

    public LemonFruitDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorTypes.LEMON_FRUIT;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();

        for (BlockPos leafPos : generator.getLeavesPositions()) {
            if (random.nextFloat() >= probability) continue;

            BlockPos below = leafPos.down();
            if (generator.isAir(below)) {
                generator.replace(below, ModBlocks.LEMON_CROP.getDefaultState());
            }
        }
    }
}