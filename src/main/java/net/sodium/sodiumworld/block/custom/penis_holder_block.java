package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.item.ModItems;
import net.sodium.sodiumworld.sound.ModSounds;

public class penis_holder_block extends Block {
    public static final BooleanProperty HOLDING = BooleanProperty.of("holding");
    public penis_holder_block(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(HOLDING, false));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            if (world.getBlockState(pos) == ModBlocks.PENIS_HOLDER_BLOCK.getDefaultState()){
                if (player.getMainHandStack().getItem() == ModItems.PENIS){

                    world.playSound(null, pos, ModSounds.HORNY, SoundCategory.BLOCKS, 1f, 1f);
                    player.getMainHandStack().decrement(1);
                    world.setBlockState(pos, ModBlocks.PENIS_HOLDER_BLOCK.getDefaultState().with(HOLDING, true));
                }
            }else {
                world.playSound(null, pos, ModSounds.HORNY, SoundCategory.BLOCKS, 1f, 1f);
                BlockPos abovePos = pos.up();

                ItemStack itemStack = new ItemStack(ModItems.PENIS, 1);
                ItemEntity itemEntity = new ItemEntity(world,
                        abovePos.getX() + 0.5,  // Центр блока по X
                        abovePos.getY() + 0.5,  // Центр блока по Y
                        abovePos.getZ() + 0.5,  // Центр блока по Z
                        itemStack);

                itemEntity.setVelocity(
                        world.random.nextDouble() * 0.2 - 0.1,
                        0.2,
                        world.random.nextDouble() * 0.2 - 0.1
                );

                world.spawnEntity(itemEntity);
                world.setBlockState(pos, ModBlocks.PENIS_HOLDER_BLOCK.getDefaultState());
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(world.getBlockState(pos) == ModBlocks.PENIS_HOLDER_BLOCK.getDefaultState().with(HOLDING, true)){
            ItemStack itemStack = new ItemStack(ModItems.PENIS, 1);
            ItemEntity itemEntity = new ItemEntity(world,
                    pos.getX(),  // Центр блока по X
                    pos.getY(),  // Центр блока по Y
                    pos.getZ(),  // Центр блока по Z
                    itemStack);

            itemEntity.setVelocity(
                    world.random.nextDouble() * 0.2 - 0.1,
                    0.2,
                    world.random.nextDouble() * 0.2 - 0.1
            );

            world.spawnEntity(itemEntity);
        }
        ItemStack itemStack = new ItemStack(ModBlocks.PENIS_HOLDER_BLOCK, 1);
        ItemEntity itemEntity = new ItemEntity(world,
                pos.getX(),  // Центр блока по X
                pos.getY(),  // Центр блока по Y
                pos.getZ(),  // Центр блока по Z
                itemStack);

        itemEntity.setVelocity(
                world.random.nextDouble() * 0.2 - 0.1,
                0.2,
                world.random.nextDouble() * 0.2 - 0.1
        );

        world.spawnEntity(itemEntity);
        return Blocks.AIR.getDefaultState();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HOLDING);
    }
}
