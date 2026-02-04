package net.sodium.sodiumworld.item.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sodium.sodiumworld.entity.ModEntities;
import net.sodium.sodiumworld.entity.custom.JopaEntity;
import net.sodium.sodiumworld.entity.custom.PortalEntity;
import net.sodium.sodiumworld.networking.packet.ChangeJopaRootPosS2CPacket;
import org.joml.Vector3f;

import java.util.EnumSet;


public class PortalGun extends Item {
    public PortalGun(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient()) return ActionResult.SUCCESS;

        PlayerEntity player = context.getPlayer();
        if (player == null) return ActionResult.PASS;
        Vec3d hit = context.getHitPos();
        double x = hit.x;
        double y = hit.y;
        double z = hit.z;

        PortalEntity portal = new PortalEntity(ModEntities.PORTAL, (ServerWorld) world);
        portal.setPosition(x, y, z);

        float yaw = player.getYaw();
        portal.setYaw(yaw);
        portal.setPitch(0.0f);
        boolean success = portal.teleport((ServerWorld) world,
                x, y, z,
                EnumSet.of(PositionFlag.X_ROT, PositionFlag.Y_ROT),
                yaw,
                0
        );

        if (!success) {
            portal.discard();
        }
        portal.calculateDimensions();
        world.spawnEntity(portal);

        JopaEntity jopa = new JopaEntity(ModEntities.JOPA, (ServerWorld) world);
        jopa.setPosition(x, y, z);
        jopa.setRootPos(x, y, z);
        ServerPlayNetworking.send((ServerPlayerEntity)context.getPlayer(), new ChangeJopaRootPosS2CPacket(jopa.getId(), new Vector3f((float) x, (float)y, (float)z)));
        jopa.setYaw(yaw);
        jopa.setPitch(0.0f);
        jopa.teleport((ServerWorld) world,
                x, y, z,
                EnumSet.of(PositionFlag.X_ROT, PositionFlag.Y_ROT),
                yaw,
                0
        );

        world.spawnEntity(jopa);
        context.getStack().decrement(1);

        return ActionResult.SUCCESS;
    }
}
