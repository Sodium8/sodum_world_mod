package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.PenisVirusEntity;
import org.jetbrains.annotations.Nullable;

public class penis_virus_block extends Block implements BlockEntityProvider {

    public penis_virus_block(Settings settings) {
        super(settings);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PenisVirusEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.PENIS_VIRUS_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof PenisVirusEntity) {
                PenisVirusEntity.tick(world1, pos, state1, (PenisVirusEntity) blockEntity);
            }
        };
    }
}
