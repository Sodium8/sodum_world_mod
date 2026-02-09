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
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.custom.NBTUtils.GasContainerNBTUtil;
import net.sodium.sodiumworld.networking.packet.SyncGasS2CPacket;
import net.sodium.sodiumworld.util.GasRecipe;
import net.sodium.sodiumworld.util.GasStack;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class GasContainerEntity extends BlockEntity implements ImplementedGasInventory {

    public GasContainerEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAS_CONTAINER_ENTITY, pos, state);
        List<GasStack> input = new ArrayList<>();
        input.add(new GasStack("sulfuric_dioxide", 2));
        input.add(new GasStack("oxygen", 1));
        List<GasStack> output = new ArrayList<>();
        output.add(new GasStack("sulfuric_trioxide", 2));
        this.gasRecipes.add(new GasRecipe(this, input, output));
    }
    private ArrayList<GasStack> gasInventory = new ArrayList<>();  // ← индивидуальный инвентарь
    private float maxSize = 1000;
    public ArrayList<GasRecipe> gasRecipes = new ArrayList<>();

    @Override
    public ArrayList<GasStack> getGasInventory() {
        return gasInventory;
    }

    public void setGasInventory(ArrayList<GasStack> new_inv) {
        this.gasInventory = new_inv;
    }

    @Override
    public float getMaxSize() {
        return maxSize;
    }
    public static void tick(World world, BlockPos pos, BlockState state, GasContainerEntity blockEntity) {
        ArrayList<GasStack> new_inv = new ArrayList<GasStack>();
        for (GasStack i : blockEntity.getGasInventory()){
            if(i.getVolume() > 0){
                new_inv.add(i);
            }
        }
        blockEntity.setGasInventory(new_inv);
        blockEntity.craft();
    }
    private void craft(){
        for(GasRecipe i:this.gasRecipes){
            i.try_to_craft();
        }
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        GasContainerNBTUtil<GasContainerEntity> util = new GasContainerNBTUtil<GasContainerEntity>();
        util.writeGasInventory(this, nbt);
        super.writeNbt(nbt, registryLookup);
        sync();
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        GasContainerNBTUtil<GasContainerEntity> util = new GasContainerNBTUtil<GasContainerEntity>();
        util.readGasInventory(this, nbt);
        super.readNbt(nbt, registryLookup);
        sync();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        sync();
    }
    public void sync(){
        if(world != null) {
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
