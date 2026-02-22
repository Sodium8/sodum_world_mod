package net.sodium.sodiumworld.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.util.GasStack;

import java.util.ArrayList;
import java.util.Objects;


public class FilterBlockEntity  extends BlockEntity implements ImplementedGasInventory {
    private ArrayList<GasStack> gasInventory = new ArrayList<>();
    private String filtered_id = "sulfuric_dioxide";
    public FilterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAS_FILTER_ENTITY, pos, state);
    }

    @Override
    public ArrayList<GasStack> getGasInventory() {
        return gasInventory;
    }

    @Override
    public float getMaxSize() {
        return 200;
    }

    @Override
    public boolean canAdd(String id, float amount) {
        return ImplementedGasInventory.super.canAdd(id, amount) && Objects.equals(id, filtered_id);
    }
}
