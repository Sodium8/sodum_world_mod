package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.item.ModItems;

public class broken_penis_block extends Block {
    public static final IntProperty PENIS_IN = IntProperty.of("penis_in", 0, 10);
    public broken_penis_block(Settings settings) {
        super(settings);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PENIS_IN);
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(player.getMainHandStack().getItem() == ModItems.PENIS) {
            player.getMainHandStack().decrement(1);
            world.setBlockState(pos, ModBlocks.BROKEN_PENIS_BLOCK.getDefaultState().with(PENIS_IN, world.getBlockState(pos).get(PENIS_IN)+1));
            if (world.getBlockState(pos).get(PENIS_IN) >= 5)
                world.setBlockState(pos, ModBlocks.PENIS_BLOCK.getDefaultState());
            world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.BLOCKS, 1f, 1f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity itemEntity){
            if(itemEntity.getStack().getItem() == ModItems.PENIS){
                itemEntity.setStack(new ItemStack(ModItems.SLUT, itemEntity.getStack().getCount()));
            }
        }


        super.onSteppedOn(world, pos, state, entity);
    }
}
