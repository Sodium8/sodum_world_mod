package net.sodium.sodiumworld.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.custom.GasContainerEntity;
import net.sodium.sodiumworld.util.GasStack;
import net.sodium.sodiumworld.util.IEntityDataSaver;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.List;

public record SyncGasS2CPacket(String inv, Vector3f pos) implements CustomPayload { // Добавили переменную в record
    public static final Id<SyncGasS2CPacket> ID = new Id<>(Identifier.of(SodiumWorld.MOD_ID, "sync_gas"));

    public static final PacketCodec<RegistryByteBuf, SyncGasS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, SyncGasS2CPacket::inv,
            PacketCodecs.VECTOR3F, SyncGasS2CPacket::pos,
            SyncGasS2CPacket::new
    );

    public static void receive(SyncGasS2CPacket payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            List<String> gases = Arrays.stream(payload.inv.split("/")).toList();

            BlockEntity ent = context.client().world.getBlockEntity(new BlockPos((int)payload.pos.x, (int)payload.pos.y, (int)payload.pos.z));
            if (ent instanceof ImplementedGasInventory igi){
                igi.getGasInventory().clear();
                for (String i : gases) {
                    List<String> info = Arrays.stream(i.split(":")).toList();
                    if (info.size() > 1) {
                        igi.addGas(ent, info.getFirst(), Float.parseFloat(info.get(1)));
                    }
                }
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}