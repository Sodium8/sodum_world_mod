package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class GlassBottleWithLemonade extends Block {
    public static final VoxelShape SHAPE =
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0);
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    public GlassBottleWithLemonade(Settings settings) {
        super(settings);
    }
}
