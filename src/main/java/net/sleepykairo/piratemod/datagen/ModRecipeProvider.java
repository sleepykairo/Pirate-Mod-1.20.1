package net.sleepykairo.piratemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CANNONBALL, 16)
//                .pattern(" I ")
//                .pattern("IGI")
//                .pattern(" I ")
//                .input('I', Items.IRON_INGOT)
//                .input('G', Items.GUNPOWDER)
//                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
//                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
//                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CANNONBALL)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CANNONBALL, 1)
                .input(Items.RAW_IRON)
                .criterion(hasItem(Items.RAW_IRON), conditionsFromItem(Items.RAW_IRON))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CANNONBALL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MUSKET, 1)
                .pattern("FII")
                .pattern("WW ")
                .input('F', Items.FLINT)
                .input('I', Items.IRON_INGOT)
                .input('W', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MUSKET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLINTLOCK, 1)
                .pattern("FI ")
                .pattern("W  ")
                .input('F', Items.FLINT)
                .input('I', Items.IRON_INGOT)
                .input('W', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FLINTLOCK)));
    }
}
