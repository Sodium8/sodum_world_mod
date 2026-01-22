package net.sodium.sodiumworld.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.util.IEntityDataSaver;

public record SyncManaS2CPacket(int mana) implements CustomPayload { // Добавили переменную в record
    public static final Id<SyncManaS2CPacket> ID = new Id<>(Identifier.of(SodiumWorld.MOD_ID, "mana_sync"));

    // Заменяем .unit на кодек, который умеет читать и записывать INT
    public static final PacketCodec<RegistryByteBuf, SyncManaS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, SyncManaS2CPacket::mana,
            SyncManaS2CPacket::new
    );

    public static void receive(SyncManaS2CPacket payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            ((IEntityDataSaver) context.player()).getPersistentData().putInt("mana", payload.mana());
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}