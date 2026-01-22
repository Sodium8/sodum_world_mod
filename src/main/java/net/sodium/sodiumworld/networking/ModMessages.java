package net.sodium.sodiumworld.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.sodium.sodiumworld.networking.packet.SpawnPenisC2SPacket;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;

public class ModMessages {
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SpawnPenisC2SPacket.ID, SpawnPenisC2SPacket::receive);
    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SyncManaS2CPacket.ID, SyncManaS2CPacket::receive);
    }
}
