package net.sodium.sodiumworld.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sodium.sodiumworld.block.custom.ElectrolizerBlock;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ImplementedInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.screen.custom.ElectrolizerScreenHandler;
import net.sodium.sodiumworld.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ElectrolizerBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int tick_count = 0;
    private int current_reaction_id = -1;
    private final List<ModRecipe<Item>> recipes = new ArrayList<ModRecipe<Item>>();

    public ElectrolizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTROLIZER_BLOCK_ENTITY, pos, state);
        var l1 = new ArrayList<Item>();
        l1.add(Items.WATER_BUCKET.asItem());
        recipes.add(new ModRecipe<>(l1));
    }

    public static void tick(World world, BlockPos pos, BlockState state, ElectrolizerBlockEntity blockEntity) {
        BlockState bs1 = world.getBlockState(pos.add(1, 0, 0));
        BlockState bs2 = world.getBlockState(pos.add(-1, 0, 0));
        if (blockEntity.inventory.getFirst().getItem() == Items.WATER_BUCKET){
            assert blockEntity.world != null;
            blockEntity.world.setBlockState(pos, state.with(ElectrolizerBlock.HAS_WATER, true));
        }else{
            assert blockEntity.world != null;
            blockEntity.world.setBlockState(pos, state.with(ElectrolizerBlock.HAS_WATER, false));
        }

        if (bs1.getProperties().contains(RedstoneWireBlock.POWER) &&
                bs2.getProperties().contains(RedstoneWireBlock.POWER)){
            if (!bs1.get(RedstoneWireBlock.POWER).equals(0) &&
                    !bs2.get(RedstoneWireBlock.POWER).equals(0)){
                blockEntity.craft();
            }
        }
    }

    private void craft() {
        World world = this.getWorld();
        if (world == null || world.isClient) {
            return;
        }

        var l1 = new ArrayList<Item>();
        l1.add(this.inventory.getFirst().getItem());
        if (recipes.getFirst().try_craft(l1)) {
            if (this.current_reaction_id != 0) {
                this.current_reaction_id = 0;
                this.tick_count = 2;
            } else if (this.tick_count == 0) {
                this.try_to_add_on_left("hydrogen", 10);
                this.try_to_add_on_right("oxygen", 10);
                this.current_reaction_id = -1;
            } else {
                tick_count--;
            }
        }
    }

    private void try_to_add_on_left(String id, float amount) {
        World world = this.getWorld();
        if (world == null || world.isClient) {
            return;
        }
        BlockPos Pos = this.getPos().add(0, 0, -1);
        BlockEntity aboveEntity = world.getBlockEntity(Pos);
        if (aboveEntity instanceof ImplementedGasInventory igi) {
            igi.addGas(aboveEntity, id, amount);
        }
    }
    private void try_to_add_on_right(String id, float amount) {
        World world = this.getWorld();
        if (world == null || world.isClient) {
            return;
        }
        BlockPos Pos = this.getPos().add(0, 0, 1);
        BlockEntity aboveEntity = world.getBlockEntity(Pos);
        if (aboveEntity instanceof ImplementedGasInventory igi) {
            igi.addGas(aboveEntity, id, amount);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Electrolizer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ElectrolizerScreenHandler(syncId, playerInventory, this.pos);
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
