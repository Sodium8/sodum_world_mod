package net.sodium.sodiumworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.custom.GiantCarrotCropBlock;
import net.sodium.sodiumworld.block.custom.PenisCropBlock;
import net.sodium.sodiumworld.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BROKEN_PENIS_BLOCK);
        addDrop(ModBlocks.IRON_DILDO);
        addDrop(ModBlocks.GLASS_BOTTLE);
        addDrop(ModBlocks.GLASS_BOTTLE_WITH_LEMONADE);
        addDrop(ModBlocks.GLASS_BOTTLE_WITH_SUGAR);
        addDrop(ModBlocks.GLASS_BOTTLE_WITH_SUGAR_AND_SPARKLING_WATER);
        addDrop(ModBlocks.DISH);
        addDrop(ModBlocks.LEMON_SAPLING);
        addDrop(ModBlocks.LEMON_LEAVES, leavesDrops(ModBlocks.LEMON_LEAVES, ModBlocks.LEMON_SAPLING, 0.06f));
        addDrop(ModBlocks.PENIS_BLOCK, multipleOreDrops(ModBlocks.PENIS_BLOCK, ModItems.PENIS, 9, 9));

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.PENIS_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(PenisCropBlock.AGE, PenisCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.PENIS_CROP, this.multipleCropDrops(ModBlocks.PENIS_CROP, ModItems.PENIS, ModItems.PENIS_SEEDS, builder2));
        BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.GIANT_CARROT_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GiantCarrotCropBlock.AGE, GiantCarrotCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.GIANT_CARROT_CROP, justDrop1(ModBlocks.GIANT_CARROT_CROP, ModItems.GIANT_CARROT, true, builder3));
        this.addDrop(ModBlocks.LEMON_CROP, justDrop1(ModBlocks.LEMON_CROP, ModItems.LEMON, false, null));
    }
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
    public LootTable.Builder justDrop1(Block block, Item to_drop, boolean conditionally, @Nullable LootCondition.Builder condition) {
        if (conditionally){
        return this.applyExplosionDecay(
                block,
                LootTable.builder()
                        .pool(LootPool.builder().with(ItemEntry.builder(to_drop).conditionally(condition)))

        );}
        else{
            return this.applyExplosionDecay(
                    block,
                    LootTable.builder()
                            .pool(LootPool.builder().with(ItemEntry.builder(to_drop)))

            );
        }
    }
    public LootTable.Builder justDrop2(Block block, Item to_drop1, Item to_drop2, LootCondition.Builder condition) {
        return this.applyExplosionDecay(
                block,
                LootTable.builder()
                        .pool(LootPool.builder().with(ItemEntry.builder(to_drop1).conditionally(condition)))
                        .pool(
                                LootPool.builder()
                                        .conditionally(condition)
                                        .with(ItemEntry.builder(to_drop2))
                        )
        );
    }
    public LootTable.Builder multipleCropDrops(Block crop, Item product, Item seeds, LootCondition.Builder condition) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.applyExplosionDecay(
                crop,
                LootTable.builder()
                        .pool(LootPool.builder().with(ItemEntry.builder(product).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))).conditionally(condition).alternatively(ItemEntry.builder(seeds))))
                        .pool(
                                LootPool.builder()
                                        .conditionally(condition)
                                        .with(ItemEntry.builder(seeds).apply(ApplyBonusLootFunction.binomialWithBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))
                        )
        );
    }
}
