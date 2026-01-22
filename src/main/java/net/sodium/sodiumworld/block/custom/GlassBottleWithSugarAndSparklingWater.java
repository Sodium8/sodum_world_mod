package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.item.ModItems;

public class GlassBottleWithSugarAndSparklingWater extends Block {
    public GlassBottleWithSugarAndSparklingWater(Settings settings) {
        super(settings);
    }
    public static final VoxelShape SHAPE =
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0);
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!stack.isEmpty()){
            if(stack.getItem() == ModItems.LEMON){
                stack.decrement(1);
                world.setBlockState(pos, ModBlocks.GLASS_BOTTLE_WITH_LEMONADE.getDefaultState());
                world.playSound(player, pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundCategory.BLOCKS);
            }
        }
        return ItemActionResult.SUCCESS;
    }
}
