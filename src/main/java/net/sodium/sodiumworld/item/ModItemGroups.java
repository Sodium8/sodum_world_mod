package net.sodium.sodiumworld.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SODIUM_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SodiumWorld.MOD_ID, "sodium_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PENIS))
                    .displayName(Text.translatable("itemgroup.sodium-world.sodium_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PENIS);
                        entries.add(ModItems.SPARKLING_WATER);
                        entries.add(ModItems.LEMON);
                        entries.add(ModItems.SLUT);
                        entries.add(ModItems.PENIS_STAFF);
                        entries.add(ModItems.PENIS_SEEDS);
                        entries.add(ModItems.GIANT_CARROT_SEEDS);
                        entries.add(ModItems.GIANT_CARROT);
                        entries.add(ModItems.PORTAL_GUN);
                        entries.add(ModItems.PORTAL_FLUID);
                        entries.add(ModItems.RAGOUT_OF_PENISES);
                        entries.add(ModBlocks.PENIS_BLOCK);
                        entries.add(ModBlocks.PENIS_HOLDER_BLOCK);
                        entries.add(ModBlocks.BROKEN_PENIS_BLOCK);
                        entries.add(ModBlocks.IRON_DILDO);
                        entries.add(ModBlocks.LEMON_SAPLING);
                        entries.add(ModBlocks.LEMON_LEAVES);
                        entries.add(ModBlocks.PENIS_VIRUS_BLOCK);
                        entries.add(ModBlocks.DISH);
                        entries.add(ModBlocks.GLASS_BOTTLE);
                        entries.add(ModBlocks.CHALK);
                        entries.add(ModBlocks.SULFUR);
                        entries.add(ModBlocks.GLASS_BOTTLE_WITH_SUGAR);
                        entries.add(ModBlocks.GLASS_BOTTLE_WITH_SUGAR_AND_SPARKLING_WATER);
                        entries.add(ModBlocks.GLASS_BOTTLE_WITH_LEMONADE);
                    }).build());
    public static void registerItemGroups() {
        SodiumWorld.LOGGER.info("Registering Item Groups for" + SodiumWorld.MOD_ID);
    }
}
