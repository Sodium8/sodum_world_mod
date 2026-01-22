package net.sodium.sodiumworld.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;


public class PenisVirusEntity extends BlockEntity {
    private int tickCounter = 0;
    private int next_tick = 10;
    public PenisVirusEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PENIS_VIRUS_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, PenisVirusEntity blockEntity) {
        if (world.isClient()) return;

        blockEntity.tickCounter++;
        if (blockEntity.tickCounter >= blockEntity.next_tick){
            blockEntity.next_tick = Random.create().nextBetween(5, 60);
            blockEntity.tickCounter=0;
            blockEntity.convertNearbyBlocks(world, pos);
        }
    }



    private void convertNearbyBlocks(World world, BlockPos center) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (Math.abs(x) + Math.abs(y)+Math.abs(z) == 1) {
                        BlockPos targetPos = center.add(x, y, z);
                        BlockState targetState = world.getBlockState(targetPos);
                        if (targetState.getBlock() != Blocks.AIR && targetState.getBlock() != ModBlocks.PENIS_VIRUS_BLOCK) {
                            world.setBlockState(targetPos, ModBlocks.PENIS_VIRUS_BLOCK.getDefaultState());
                        }
                    }
                }
            }
        }
        world.setBlockState(center, Blocks.AIR.getDefaultState());
    }
}
