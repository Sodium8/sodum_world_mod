package net.sodium.sodiumworld.item.custom;

import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.sodium.sodiumworld.entity.ModEntities;

public class BlyatItem extends Item {
    public BlyatItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            ModEntities.HITLER.spawn((ServerWorld) context.getWorld(), context.getBlockPos().add(0, 1, 0), SpawnReason.TRIGGERED);
        }
            return super.useOnBlock(context);
    }
}
