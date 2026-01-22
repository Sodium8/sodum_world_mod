package net.sodium.sodiumworld;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public class FireFrighting {
    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.isClient()) return;

            processItemsInWorld(world);
        });
    }

    private static void processItemsInWorld(World world) {
        List<ItemEntity> items = new java.util.ArrayList<>(List.of());
        for (Entity plr : world.getPlayers()){
            for (ItemEntity ent : world.getEntitiesByType(EntityType.ITEM,
                    new Box(plr.getBlockPos()).expand(1000),
                    Entity::isAlive) ) {
                if (!items.contains(ent)){
                    items.add(ent);
                }
            }
        }
        for (ItemEntity item : items) {
            applyFireRepulsion(item, world);
        }
    }

    private static void applyFireRepulsion(ItemEntity item, World world) {
        BlockPos itemPos = item.getBlockPos();
        boolean foundFire = false;
        BlockPos positionOfFire = null;

        int radius = 1;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x == 0 || z == 0) {
                    BlockPos checkPos = itemPos.add(x, 0, z);
                    Block block = world.getBlockState(checkPos).getBlock();

                    if (isFireBlock(block)) {
                        foundFire = true;
                        positionOfFire = new BlockPos(x, 0, z);
                    }
                }
            }
        }

        if(isRail(item.getBlockPos(), world)) {
            if (foundFire) {
                RailShape shape = world.getBlockState(itemPos).get(RailBlock.SHAPE);
                int speed = 1;
                if (positionOfFire.getX() < 0 || positionOfFire.getZ() > 0){
                    Vec3d vel = getDirectionFromShape(shape, positionOfFire).multiply(speed);
                    item.setVelocity(vel);
                }else if (positionOfFire.getX() > 0 || positionOfFire.getZ() < 0){
                    Vec3d vel = getDirectionFromShape(shape, positionOfFire).multiply(-1).multiply(speed);
                    item.setVelocity(vel);
                }
            }
        }
    }
    private static Vec3d getDirectionFromShape(RailShape shape, BlockPos firePos) {
        if (shape == RailShape.NORTH_WEST) {
            if (firePos.getZ() > 0) {
                return new Vec3d(0, 0, -1).normalize();
            } else if (firePos.getX() > 0) {
                return new Vec3d(1, 0, 0).normalize();
            }
        }
        else if (shape == RailShape.NORTH_EAST){
            if (firePos.getZ() > 0) {
                return new Vec3d(0, 0, -1).normalize();
            } else if (firePos.getX() < 0) {
                return new Vec3d(-1, 0, 0).normalize();
            }
        }else if (shape == RailShape.SOUTH_WEST){
            if (firePos.getZ() < 0) {
                return new Vec3d(0, 0, 1).normalize();
            } else if (firePos.getX() > 0) {
                return new Vec3d(1, 0, 0).normalize();
            }
        }else if(shape == RailShape.SOUTH_EAST){
            if (firePos.getZ() < 0) {
                return new Vec3d(0, 0, 1).normalize();
            } else if (firePos.getX() < 0) {
                return new Vec3d(-1, 0, 0).normalize();
            }
        }
        return switch (shape) {
            case NORTH_SOUTH -> new Vec3d(0, 0, -1);
            case EAST_WEST -> new Vec3d(1, 0, 0);
            case ASCENDING_EAST -> new Vec3d(1, 1, 0);
            case ASCENDING_WEST -> new Vec3d(-1, 1, 0);
            case ASCENDING_NORTH -> new Vec3d(0, 1, -1);
            case ASCENDING_SOUTH -> new Vec3d(0, 1, 1);
            default -> Vec3d.ZERO;
        };
    }
    private static boolean isRail(BlockPos pos, World world){
        for (BlockState st : Blocks.RAIL.getStateManager().getStates()){
            if (st == world.getBlockState(pos)){
                return true;
            }
        }
        return false;
    }
    private static boolean isFireBlock(Block block) {
        return block == Blocks.FIRE ||
                block == Blocks.SOUL_FIRE ||
                block == Blocks.CAMPFIRE ||
                block == Blocks.LAVA ||
                block == Blocks.LAVA_CAULDRON;
    }
}
