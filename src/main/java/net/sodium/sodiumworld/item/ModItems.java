package net.sodium.sodiumworld.item;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.item.custom.PenisStaff;

public class ModItems {

    public static final Item PENIS = registerItem("penis", new Item(new Item.Settings()));
    public static final Item SPARKLING_WATER = registerItem("sparkling_water", new Item(new Item.Settings()));
    public static final Item SLUT = registerItem("slut", new Item(new Item.Settings()));
    public static final Item LEMON = registerItem("lemon", new Item(new Item.Settings()));

    public static final Item PENIS_STAFF = registerItem("penis_staff", new PenisStaff(new Item.Settings().maxDamage(10)));

    public static final Item RAGOUT_OF_PENISES = registerItem("ragout_of_penises", new Item(new Item.Settings().food(ModFoodComponents.RAGOUT_OF_PENISES)));
    public static final Item PENIS_SEEDS = registerItem("penis_seeds",
            new AliasedBlockItem(ModBlocks.PENIS_CROP, new Item.Settings()));
    private  static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(SodiumWorld.MOD_ID, name), item);
    }

    public static void registerModItems(){
        SodiumWorld.LOGGER.info("Registering Mod Items for " + SodiumWorld.MOD_ID);
    }
}
