package net.sodium.sodiumworld.block;

import net.minecraft.block.*;
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
import net.sodium.sodiumworld.world.tree.ModSaplingGenerators;

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
    public static final Block GIANT_CARROT_CROP = registerBlockWithoutItem("giant_carrot_crop",
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
    public static final Block LEMON_CROP = registerBlockWithoutItem("lemon_crop", new LemonCropBlock(AbstractBlock.Settings.create()
            .breakInstantly()
            .noCollision()
            .nonOpaque()
            .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block CHALK = registerBlock("chalk_block", new Block(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()));
    public static final Block SULFUR = registerBlock("sulfur", new Block(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()));
    public static final Block LEMON_LEAVES = registerBlock("lemon_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block LEMON_SAPLING = registerBlock("lemon_sapling",
            new SaplingBlock(ModSaplingGenerators.LEMONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block BURNER_BLOCK = registerBlock("burner_block", new BurnerBlock(AbstractBlock.Settings.create().nonOpaque().sounds(BlockSoundGroup.GLASS)));
    public static final Block GAS_CONTAINER = registerBlock("gas_container_block", new GasContainer(AbstractBlock.Settings.create().nonOpaque().sounds(BlockSoundGroup.GLASS)));
    public static final Block ELECTROLIZER_BLOCK = registerBlock("electrolizer_block", new ElectrolizerBlock(AbstractBlock.Settings.create().nonOpaque().sounds(BlockSoundGroup.GLASS)));
    public static final Block GAS_PIPE = registerBlock("gas_pipe", new GasPipeBlock(AbstractBlock.Settings.create().nonOpaque().sounds(BlockSoundGroup.GLASS)));



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
