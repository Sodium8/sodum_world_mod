package net.sodium.sodiumworld.block.entity.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.custom.GasPipeBlock;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.NBTUtils.GasContainerNBTUtil;
import net.sodium.sodiumworld.networking.packet.SyncGasS2CPacket;
import net.sodium.sodiumworld.util.GasStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;

public class GasPipeEntity  extends BlockEntity implements ImplementedGasInventory {
    public GasPipeEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAS_PIPE_ENTITY, pos, state);
    }

    private final ArrayList<GasStack> gasInventory = new ArrayList<>();
    private float maxSize = 10;
    private BlockPos offset = new BlockPos(0, 0, 0);

    @Override
    public ArrayList<GasStack> getGasInventory() {
        return gasInventory;
    }

    @Override
    public float getMaxSize() {
        return maxSize;
    }

    public static void tick(World world, BlockPos pos, BlockState state, GasPipeEntity blockEntity) {
        blockEntity.offset =
                switch (state.get(GasPipeBlock.PIPE_ORIENTATION)){
                    case 0 -> new BlockPos(1, 0, 0);
                    case 1 -> new BlockPos(-1, 0, 0);
                    case 2 -> new BlockPos(0, 1, 0);
                    case 3 -> new BlockPos(0, -1, 0);
                    case 4 -> new BlockPos(0, 0, 1);
                    case 5 -> new BlockPos(0, 0, -1);
                    default -> new BlockPos(1, 0, 0);
                };
        if (!blockEntity.getGasInventory().isEmpty()){
            BlockEntity be = world.getBlockEntity(pos.add(blockEntity.offset));
            if (be instanceof ImplementedGasInventory igi) {
                GasStack decrementer = new GasStack(blockEntity.getGasInventory().getFirst().getId(),
                        -Math.min(blockEntity.getGasInventory().getFirst().getVolume(), 10));
                blockEntity.addGas(blockEntity, decrementer.getId(), decrementer.getVolume());
                igi.addGas(be, decrementer.getId(), -decrementer.getVolume());
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        GasContainerNBTUtil<GasPipeEntity> util = new GasContainerNBTUtil<>();
        util.writeGasInventory(this, nbt);
        super.writeNbt(nbt, registryLookup);
        sync();
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        GasContainerNBTUtil<GasPipeEntity> util = new GasContainerNBTUtil<>();
        util.readGasInventory(this, nbt);
        super.readNbt(nbt, registryLookup);
        sync();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        sync();
    }

    public void sync() {
        if (world != null) {
            if (!this.world.isClient()) {
                String data = "";
                for (GasStack i : this.getGasInventory()) {
                    data = data + i.getId() + ":" + i.getVolume() + "/";
                }
                for (PlayerEntity i : this.world.getPlayers()) {
                    ServerPlayNetworking.send((ServerPlayerEntity) i, new SyncGasS2CPacket(data, new Vector3f(this.pos.getX(),
                            this.pos.getY(), this.pos.getZ())));
                }
            }
        }
    }
}