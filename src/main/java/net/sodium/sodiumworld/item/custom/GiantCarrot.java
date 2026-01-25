package net.sodium.sodiumworld.item.custom;

import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.entity.ModEntities;

public class GiantCarrot extends Item {
    public GiantCarrot(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            ModEntities.CARROT_CAR.spawn((ServerWorld) context.getWorld(), context.getBlockPos().add(new BlockPos(0, 1, 0)), SpawnReason.TRIGGERED);
            context.getStack().decrement(1);
        }
        return super.useOnBlock(context);
    }
}
