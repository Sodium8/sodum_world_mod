package net.sodium.sodiumworld.item.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.component.ModDataComponentTypes;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;
import net.sodium.sodiumworld.util.IEntityDataSaver;
import net.sodium.sodiumworld.util.ManaData;

import java.util.List;

public class PenisStaff extends Item {

    public PenisStaff(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        IEntityDataSaver dataPlayer = ((IEntityDataSaver) context.getPlayer());
        assert dataPlayer != null;
        int mana = dataPlayer.getPersistentData().getInt("mana");
        if (!world.isClient()) {
            if (clickedBlock == ModBlocks.PENIS_BLOCK && mana >= 10) {
                world.setBlockState(context.getBlockPos(), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(0, 1, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(0, 2, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(1, 0, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                world.setBlockState(context.getBlockPos().add(-1, 0, 0), ModBlocks.PENIS_BLOCK.getDefaultState());
                assert context.getPlayer() != null;
                ManaData.removeMana((IEntityDataSaver) context.getPlayer(), 10);
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
