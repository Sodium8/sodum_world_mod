package net.sodium.sodiumworld.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.GasContainerEntity;
import net.sodium.sodiumworld.component.ModDataComponentTypes;
import net.sodium.sodiumworld.util.GasStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static net.sodium.sodiumworld.block.entity.custom.Utils.GasContainerNBTUtil.getGasName;


public class GasContainer extends Block implements BlockEntityProvider {
    public GasContainer(Settings settings) {
        super(settings);
    }
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 4.0, 4.0, 12.0, 12.0, 12.0));

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GasContainerEntity(pos, state);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.GAS_CONTAINER_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof GasContainerEntity) {
                GasContainerEntity.tick(world1, pos, state1, (GasContainerEntity) blockEntity);
            }
        };
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!world.isClient()) {
            GasContainerEntity ent = (GasContainerEntity) Objects.requireNonNull(world.getBlockEntity(pos));
            ItemStack item = new ItemStack(ModBlocks.GAS_CONTAINER.asItem());
            if (!ent.getGasInventory().isEmpty()) {
                item.set(ModDataComponentTypes.GAS, ent.getGasInventory());
            }
            ItemEntity ie = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, item);
            world.spawnEntity(ie);
        }
        world.removeBlock(pos, false);
        return state;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient()) {
            List<GasStack> gasses;
            if (itemStack.get(ModDataComponentTypes.GAS) != null) {
                gasses = itemStack.get(ModDataComponentTypes.GAS);
                super.onPlaced(world, pos, state, placer, itemStack);
                GasContainerEntity gce = (GasContainerEntity) world.getBlockEntity(pos);
                assert gasses != null;
                for (GasStack gs : gasses) {
                    assert gce != null;
                    gce.addGas(gce, gs.getId(), gs.getVolume());
                }
            } else {
                super.onPlaced(world, pos, state, placer, itemStack);
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        var gasList = stack.get(ModDataComponentTypes.GAS);
        if (gasList == null || gasList.isEmpty()) {
            return;
        }
        tooltip.add(Text.literal("Содержимое:").formatted(Formatting.GRAY));

        for (GasStack gas : gasList) {
            String name = getGasName(gas.getId());
            float vol  = gas.getVolume();


            Text line = Text.literal(" • ")
                    .append(Text.literal(name).formatted(Formatting.YELLOW))
                    .append(Text.literal(": "))
                    .append(Text.literal(String.format("%.1f", vol)).formatted(Formatting.WHITE))
                    .append(Text.literal(" lbs").formatted(Formatting.GRAY));

            tooltip.add(line);
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

}
