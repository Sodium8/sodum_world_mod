package net.sodium.sodiumworld.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.entity.custom.JopaEntity;
import org.joml.Vector3f;

import java.util.Objects;

public record ChangeJopaRootPosS2CPacket (int entityId, Vector3f pos) implements CustomPayload {
    public static final Id<ChangeJopaRootPosS2CPacket> ID = new Id<>(Identifier.of(SodiumWorld.MOD_ID, "jopa_root_sync"));

    public static final PacketCodec<RegistryByteBuf, ChangeJopaRootPosS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT,    ChangeJopaRootPosS2CPacket::entityId,
            PacketCodecs.VECTOR3F, ChangeJopaRootPosS2CPacket::pos,
            ChangeJopaRootPosS2CPacket::new
    );

    public static void receive(ChangeJopaRootPosS2CPacket payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            JopaEntity ent = (JopaEntity) Objects.requireNonNull(context.client().world).getEntityById(payload.entityId());
            assert ent != null;
            ent.setRootPos(payload.pos.x, payload.pos.y, payload.pos.z);
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
