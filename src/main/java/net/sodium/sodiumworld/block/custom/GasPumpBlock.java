package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.GasPumpEntity;
import net.sodium.sodiumworld.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class GasPumpBlock extends Block implements BlockEntityProvider {
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.0, 0.0, 7.0, 9.5, 16.0, 9.0),
            Block.createCuboidShape(0.0, 7.0, 7.0, 16.0, 9.0, 9.0),
            Block.createCuboidShape(7.0, 7.0, 0.0, 9.0, 9.0, 16.0));
    // 0 - x+
    // 1 - x-
    // 2 - y+
    // 3 - y-
    // 4 - z+
    // 5 - z-
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    public GasPumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GasPumpEntity(pos, state);
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(stack.getItem() == ModItems.PENIS){
            world.setBlockState(pos, state.with(GasPipeBlock.PIPE_ORIENTATION, (state.get(GasPipeBlock.PIPE_ORIENTATION)+1)%6));
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.GAS_PUMP_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof GasPumpEntity) {
                GasPumpEntity.tick(world1, pos, state1, (GasPumpEntity) blockEntity);
            }
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GasPipeBlock.PIPE_ORIENTATION);
    }
}
