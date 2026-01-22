package net.sodium.sodiumworld.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.component.ModDataComponentTypes;

import java.util.List;

public class PenisStaff extends Item {

    public PenisStaff(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        if (!world.isClient()) {
            if (clickedBlock == ModBlocks.PENIS_BLOCK) {
                world.setBlockState(context.getBlockPos(), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(0, 1, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(0, 2, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(1, 0, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(-1, 0, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
            }
        }


        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(stack.get(ModDataComponentTypes.COORDINATES) != null){
            tooltip.add(Text.literal("Last penis created at "+stack.get(ModDataComponentTypes.COORDINATES)));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
