package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.block.entity.custom.FilterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class GasFilter extends Block implements BlockEntityProvider {
    public GasFilter(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FilterBlockEntity(pos, state);
    }
}
