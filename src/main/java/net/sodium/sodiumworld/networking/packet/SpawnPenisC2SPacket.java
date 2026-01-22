package net.sodium.sodiumworld.networking.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.item.ModItems;
import net.sodium.sodiumworld.util.IEntityDataSaver;
import net.sodium.sodiumworld.util.ManaData;

public record SpawnPenisC2SPacket() implements CustomPayload {
    public static final Id<SpawnPenisC2SPacket> ID = new Id<>(Identifier.of(SodiumWorld.MOD_ID, "spawn_penis"));

    public static final PacketCodec<RegistryByteBuf, SpawnPenisC2SPacket> CODEC = PacketCodec.unit(new SpawnPenisC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void receive(SpawnPenisC2SPacket payload, ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            World world = context.player().getWorld();
            Vec3d pos = context.player().getPos();
            ManaData.addMana((IEntityDataSaver) context.player(), 10);
            ItemStack mIS = new ItemStack(ModItems.PENIS, 1);
            ItemEntity iE = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), mIS);
            iE.setVelocity(context.player().getRotationVector().multiply(1.5));
            world.spawnEntity(iE);
        });
    }
}
