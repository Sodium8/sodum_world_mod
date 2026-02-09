package net.sodium.sodiumworld.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.custom.GasPipeBlock;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;

public class GasPumpEntity extends BlockEntity{
    public GasPumpEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAS_PUMP_ENTITY, pos, state);
    }
    private BlockPos offset = new BlockPos(0, 0, 0);

    public static void tick(World world, BlockPos pos, BlockState state, GasPumpEntity blockEntity) {
        blockEntity.offset =
                switch (state.get(GasPipeBlock.PIPE_ORIENTATION)){
                    case 0 -> new BlockPos(1, 0, 0);
                    case 1 -> new BlockPos(-1, 0, 0);
                    case 2 -> new BlockPos(0, 1, 0);
                    case 3 -> new BlockPos(0, -1, 0);
                    case 4 -> new BlockPos(0, 0, 1);
                    case 5 -> new BlockPos(0, 0, -1);
                    default -> new BlockPos(1, 0, 0);
                };
        BlockEntity be1 = world.getBlockEntity(pos.add(blockEntity.offset));
        BlockEntity be2 = world.getBlockEntity(pos.add(blockEntity.offset.multiply(-1)));

        if (be1 instanceof ImplementedGasInventory igi1) {
            if (be2 instanceof ImplementedGasInventory igi2) {
                if (!igi2.getGasInventory().isEmpty()) {
                    float decrement_count = Math.min(Math.min(igi1.leftFreePlace(), igi2.
                            getGasInventory().getFirst().getVolume()), 10);
                    String id = igi2.getGasInventory().getFirst().getId();
                    igi2.addGas(be2, id, -decrement_count);
                    igi1.addGas(be1, id, decrement_count);
                }
            }
        }
    }
}
