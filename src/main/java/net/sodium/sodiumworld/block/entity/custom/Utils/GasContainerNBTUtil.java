package net.sodium.sodiumworld.block.entity.custom.Utils;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.util.GasStack;

public class GasContainerNBTUtil<T extends BlockEntity> {
    private static final String GAS_INVENTORY_KEY = "GasInventory";

    public void writeGasInventory(T blockEntity, NbtCompound nbt) {
        if (blockEntity instanceof ImplementedGasInventory igi) {
            NbtList gasList = new NbtList();

            for (GasStack stack : igi.getGasInventory()) {
                NbtCompound gasTag = new NbtCompound();
                gasTag.putString("Id", stack.getId());
                gasTag.putFloat("Volume", stack.getVolume());
                gasList.add(gasTag);
            }

            nbt.put(GAS_INVENTORY_KEY, gasList);
        }
    }

    public void readGasInventory(T blockEntity, NbtCompound nbt) {
        if (blockEntity instanceof ImplementedGasInventory igi) {
            igi.getGasInventory().clear();

            if (nbt.contains(GAS_INVENTORY_KEY, NbtElement.LIST_TYPE)) {
                NbtList gasList = nbt.getList(GAS_INVENTORY_KEY, NbtElement.COMPOUND_TYPE);

                for (int i = 0; i < gasList.size(); i++) {
                    NbtCompound gasTag = gasList.getCompound(i);
                    String id = gasTag.getString("Id");
                    float volume = gasTag.getFloat("Volume");

                    if (volume > 0 && !id.isEmpty()) {
                        igi.addGas(blockEntity, id, volume);
                    }
                }
            }
        }
    }
    public static String getGasName(String id){
        return switch (id) {
            case "sulfuric_dioxide"   -> "Sulfuric dioxide";
            case "hydrogen"   -> "Hydrogen";
            case "oxygen"   -> "Oxygen";
            default           -> id;
        };
    }
}
