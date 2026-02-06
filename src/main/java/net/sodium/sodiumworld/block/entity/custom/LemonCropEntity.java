package net.sodium.sodiumworld.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.Sound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;

public class LemonCropEntity extends BlockEntity {
    public LemonCropEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LEMON_CROP_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, LemonCropEntity blockEntity) {
        if (world.isClient()) return;
        if (!world.getBlockState(pos.add(0, 1, 0)).isOf(ModBlocks.LEMON_LEAVES)){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS);
        }
    }
}
