package net.sleepykairo.piratemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PLANT_MATTER, 1)
                .pattern("PP")
                .pattern("B")
                .pattern("B")
                .input('P', ModItems.PLANT_MATTER)
                .input('B', Items.BAMBOO)
                .criterion(hasItem(ModItems.PLANT_MATTER), conditionsFromItem(ModItems.PLANT_MATTER))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PLANT_MATTER)));
    }
}
