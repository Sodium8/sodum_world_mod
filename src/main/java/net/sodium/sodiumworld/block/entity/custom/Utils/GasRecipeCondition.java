package net.sodium.sodiumworld.block.entity.custom.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class GasRecipeCondition<T, R> {
    String type;
    T arg1;
    R arg2;
    public GasRecipeCondition(String type, T arg1, R arg2){
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    public boolean complete(BlockEntity be){
        if (Objects.equals(type, "block_is")){
            if (arg1 instanceof Block b){
                if (arg2 instanceof BlockPos p){
                    assert be.getWorld() != null;
                    if(be.getWorld().getBlockState(be.getPos().add(p)).getBlock() == b){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
