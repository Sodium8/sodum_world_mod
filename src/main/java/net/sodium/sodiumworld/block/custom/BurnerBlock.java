package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.BurnerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class BurnerBlock extends Block implements BlockEntityProvider {
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.5, 0.0, 4.5, 11.5, 16.0, 11.5),
            Block.createCuboidShape(3.5, 0.0, 3.5, 12.5, 1.5, 12.5));

    public BurnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BurnerBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.BURNER_BLOCK_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof BurnerBlockEntity) {
                BurnerBlockEntity.tick(world1, pos, state1, (BurnerBlockEntity) blockEntity);
            }
        };
    }
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof BurnerBlockEntity) {
                ItemScatterer.spawn(world, pos, ((BurnerBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {return ActionResult.PASS;}
        player.openHandledScreen((NamedScreenHandlerFactory) world.getBlockEntity(pos));
        return ActionResult.SUCCESS;
    }

}
