package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.ElectrolizerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ElectrolizerBlock extends Block implements BlockEntityProvider {
    public static final BooleanProperty HAS_WATER = BooleanProperty.of("has_water");
    public ElectrolizerBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(HAS_WATER, false));
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        var be = world.getBlockEntity(pos);

        if (be == null) {
            player.sendMessage(Text.literal("§cNo block entity!"), false);
            return ActionResult.FAIL;
        }

        player.openHandledScreen((NamedScreenHandlerFactory) be);
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ElectrolizerBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.ELECTROLIZER_BLOCK_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof ElectrolizerBlockEntity) {
                ElectrolizerBlockEntity.tick(world1, pos, state1, (ElectrolizerBlockEntity) blockEntity);
            }
        };
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(HAS_WATER);
    }
}
