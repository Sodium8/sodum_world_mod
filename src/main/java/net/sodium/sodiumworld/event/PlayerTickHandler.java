package net.sodium.sodiumworld.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;
import net.sodium.sodiumworld.util.IEntityDataSaver;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
            int mana = dataPlayer.getPersistentData().getInt("mana");
            String text = ""+mana;
            //player.sendMessage(Text.literal(text));
        }
    }
}
