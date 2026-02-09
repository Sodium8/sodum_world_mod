package net.sodium.sodiumworld.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.sodium.sodiumworld.util.GasStack;

import java.util.ArrayList;
import java.util.Objects;

public interface ImplementedGasInventory {
    ArrayList<GasStack> getGasInventory();

    float getMaxSize();

    default void addGas(BlockEntity be, String id, float amount) {
        ArrayList<GasStack> inv = getGasInventory();

        if (this.canAdd(amount)) {
            boolean added = false;
            for (GasStack stack : inv) {
                if (stack.getId().equals(id)) {
                    stack.add_Volume(amount);
                    added = true;
                    break;
                }
            }
            if (!added) {
                inv.add(new GasStack(id, amount));
            }
            be.markDirty();
        }
    }

    default boolean canAdd(float amount) {
        ArrayList<GasStack> inv = getGasInventory();

        float sum = 0;
        for (GasStack stack : inv) {
            sum += stack.getVolume();
        }
        return (getMaxSize() - sum) >= amount;
    }

    default float getGasAmount(String id) {
        ArrayList<GasStack> inv = getGasInventory();

        for (GasStack stack : inv) {
            if (stack.getId().equals(id)) {
                return stack.getVolume();
            }
        }
        return 0;
    }

}
