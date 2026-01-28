package net.sodium.sodiumworld.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

public class BlyatItem extends Item {
    public BlyatItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            EntityType.PIG.spawn((ServerWorld) context.getWorld(), context.getBlockPos(), SpawnReason.TRIGGERED);
        }
            return super.useOnBlock(context);
    }
}
