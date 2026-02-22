package net.sodium.sodiumworld.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
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
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.ImplementedInventory;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.screen.custom.BurnerScreenHandler;
import net.sodium.sodiumworld.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BurnerBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int tick_count = 0;
    private int current_reaction_id = -1;
    private final List<ModRecipe<Item>> recipes = new ArrayList<ModRecipe<Item>>();
    public BurnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BURNER_BLOCK_ENTITY, pos, state);
        var l1 = new ArrayList<Item>();
        l1.add(ModBlocks.SULFUR.asItem());
        l1.add(Items.FLINT_AND_STEEL);
        recipes.add(new ModRecipe<>(l1));
    }

    public static void tick(World world, BlockPos pos, BlockState state, BurnerBlockEntity blockEntity) {
        blockEntity.craft();

    }
    private void craft(){
        World world = this.getWorld();
        if (world == null || world.isClient) {
            return;
        }

        var l1 = new ArrayList<Item>();
        l1.add(this.inventory.getFirst().getItem());
        l1.add(this.inventory.get(1).getItem());

        BlockPos abovePos = this.getPos().add(0, 1, 0);
        BlockEntity aboveEntity = world.getBlockEntity(abovePos);
        if (this.getWorld().getBlockState(this.getPos().add(0, 1, 0)).getBlock().getDefaultState().isIn(ModTags.Blocks.CONTAIN_GAS)) {
            if (aboveEntity instanceof ImplementedGasInventory gas_inv) {
                if (gas_inv.canAdd("sulfuric_dioxide", 300)) {
                    if (recipes.getFirst().try_craft(l1)) {
                        if (this.current_reaction_id != 0) {
                            this.current_reaction_id = 0;
                            this.tick_count = 20;
                        } else if (this.tick_count == 0) {
                            this.inventory.getFirst().decrement(1);
                            this.try_to_add_on_top("sulfuric_dioxide", 300);
                            this.current_reaction_id = -1;
                        } else {
                            tick_count--;
                        }
                    }
                }
            }
        }
    }
    private void try_to_add_on_top(String id, float amount){
        World world = this.getWorld();
        if (world == null || world.isClient) {
            return;
        }
        BlockPos abovePos = this.getPos().add(0, 1, 0);
        BlockEntity aboveEntity = world.getBlockEntity(abovePos);
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

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Burner block");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BurnerScreenHandler(syncId, playerInventory, this.pos);
    }

}