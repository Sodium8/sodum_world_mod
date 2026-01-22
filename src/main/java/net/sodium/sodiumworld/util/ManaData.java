package net.sodium.sodiumworld.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;

public class ManaData {
    public static int addMana(IEntityDataSaver player, int amount){
        NbtCompound nbt =player.getPersistentData();
        int mana = nbt.getInt("mana");
        if (mana + amount >= 100) {
            mana = 100;
        }else{
            mana += amount;
        }
        nbt.putInt("mana", mana);

        ServerPlayNetworking.send((ServerPlayerEntity) player, new SyncManaS2CPacket(mana));
        return mana;
    }
    public static int removeMana(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if(mana - amount < 0) {
            mana = 0;
        } else {
            mana -= amount;
        }

        ServerPlayNetworking.send((ServerPlayerEntity) player, new SyncManaS2CPacket(mana));
        nbt.putInt("mana", mana);
        return mana;
    }
}
