package net.sodium.sodiumworld.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.custom.*;

public class ModBlocks {

    public static final Block PENIS_BLOCK = registerBlock("penis_block", new ExperienceDroppingBlock(UniformIntProvider.create(2,5),
            AbstractBlock.Settings.create().strength(0.5f).requiresTool()));

    public static final Block BROKEN_PENIS_BLOCK = registerBlock("broken_penis_block", new broken_penis_block(AbstractBlock.Settings.create()));
    public static final Block PENIS_HOLDER_BLOCK = registerBlock("penis_holder_block", new penis_holder_block(AbstractBlock.Settings.create().strength(1f).luminance(state -> state.get(penis_holder_block.HOLDING) ? 15 : 0)));
    public static final Block PENIS_CROP = registerBlockWithoutItem("penis_crop",
            new PenisCropBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .ticksRandomly()
                    .mapColor(MapColor.GOLD)));
    public static final Block PENIS_VIRUS_BLOCK = registerBlock("penis_virus_block", new penis_virus_block(AbstractBlock.Settings.create()));
    public static final Block IRON_DILDO = registerBlock("iron_dildo", new iron_dildo_block(AbstractBlock.Settings.create().strength(1.5f).nonOpaque()));
    public static final Block DISH = registerBlock("dish", new dish(AbstractBlock.Settings.create().strength(0.25f)));
    public static final Block GLASS_BOTTLE = registerBlock("glass_bottle", new GlassBottle(AbstractBlock.Settings.create().strength(0.1f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GLASS_BOTTLE_WITH_SUGAR = registerBlock("glass_bottle_with_sugar", new GlassBottleWithSugar(AbstractBlock.Settings.create().strength(0.1f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GLASS_BOTTLE_WITH_SUGAR_AND_SPARKLING_WATER = registerBlock("glass_bottle_with_sugar_and_sparkling_water", new GlassBottleWithSugarAndSparklingWater(AbstractBlock.Settings.create().strength(0.1f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GLASS_BOTTLE_WITH_LEMONADE = registerBlock("glass_bottle_with_lemonade", new GlassBottleWithLemonade(AbstractBlock.Settings.create().strength(0.1f).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SodiumWorld.MOD_ID, name), block);
    }
    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(SodiumWorld.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(SodiumWorld.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks(){
        SodiumWorld.LOGGER.info("Registering Mod Blocks for "+SodiumWorld.MOD_ID);
    }
}
