package net.sodium.sodiumworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> SLUT_SMELTABLES = List.of(ModItems.PENIS);

        offerSmelting(recipeExporter, SLUT_SMELTABLES, RecipeCategory.MISC, ModItems.SLUT, 1, 200, "slut");
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BROKEN_PENIS_BLOCK)
                .pattern("SPS")
                .pattern("SPS")
                .pattern("PPP")
                .input('P', ModItems.PENIS)
                .criterion(hasItem(ModItems.PENIS), conditionsFromItem(ModItems.PENIS))
                .input('S', ModItems.SLUT)
                .criterion(hasItem(ModItems.SLUT), conditionsFromItem(ModItems.SLUT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAGOUT_OF_PENISES)
                .pattern("PBP")
                .pattern("MPM")
                .pattern(" M ")
                .input('P', ModItems.PENIS)
                .criterion(hasItem(ModItems.PENIS), conditionsFromItem(ModItems.PENIS))
                .input('B', ModBlocks.PENIS_BLOCK)
                .criterion(hasItem(ModBlocks.PENIS_BLOCK), conditionsFromItem(ModBlocks.PENIS_BLOCK))
                .input('M', Items.BOWL)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PENIS_STAFF)
                .pattern("PSS")
                .pattern("SBB")
                .pattern("SBB")
                .input('P', ModItems.PENIS)
                .criterion(hasItem(ModItems.PENIS), conditionsFromItem(ModItems.PENIS))
                .input('B', ModBlocks.PENIS_BLOCK)
                .criterion(hasItem(ModBlocks.PENIS_BLOCK), conditionsFromItem(ModBlocks.PENIS_BLOCK))
                .input('S', ModItems.SLUT)
                .criterion(hasItem(ModItems.SLUT), conditionsFromItem(ModItems.SLUT))
                .offerTo(recipeExporter, Identifier.of(SodiumWorld.MOD_ID, "penis_staff_simple_craft"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PENIS_HOLDER_BLOCK)
                .pattern("BBB")
                .pattern("B B")
                .pattern("BBB")
                .input('B', ModBlocks.PENIS_BLOCK)
                .criterion(hasItem(ModBlocks.PENIS_BLOCK), conditionsFromItem(ModBlocks.PENIS_BLOCK))
                .offerTo(recipeExporter, Identifier.of(SodiumWorld.MOD_ID, "penis_holder_simple_craft"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.IRON_DILDO)
                .pattern(" PI")
                .pattern("PIP")
                .pattern("IP ")
                .input('P', ModItems.PENIS)
                .criterion(hasItem(ModItems.PENIS), conditionsFromItem(ModItems.PENIS))
                .input('I', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, Identifier.of(SodiumWorld.MOD_ID, "iron_dildo_simple_craft"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DISH)
                .pattern("   ")
                .pattern("   ")
                .pattern("WSW")
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .input('W', Blocks.OAK_PLANKS)
                .criterion(hasItem(Blocks.OAK_PLANKS), conditionsFromItem(Blocks.OAK_PLANKS))
                .offerTo(recipeExporter, Identifier.of(SodiumWorld.MOD_ID, "dish_simple_craft"));
    }
}
