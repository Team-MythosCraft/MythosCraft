package com.crypticcosmos.crypticcosmos.datagen;

import com.crypticcosmos.crypticcosmos.registries.BlockRegistries;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
public class RecipesGenerator extends RecipeProvider {
    public RecipesGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        // Mondrove planks
        ShapelessRecipeBuilder.shapeless(BlockRegistries.MONDROVE_PLANKS.get(), 4)
                .requires(BlockRegistries.MONDROVE_LOG.get())
                .group("planks")
                .unlockedBy("has_moon_log", has(BlockRegistries.MONDROVE_LOG.get()))
                .save(consumer, BlockRegistries.MONDROVE_PLANKS.getId());

        registerLunonRecipes(consumer);
    }

    private void registerLunonRecipes(Consumer<IFinishedRecipe> consumer) {
        // Lunon bricks
        ShapedRecipeBuilder.shaped(BlockRegistries.LUNON_BRICKS.get(), 4)
                .pattern("bb")
                .pattern("bb")
                .define('b', BlockRegistries.LUNON.get())
                .unlockedBy("has_lunon_bricks", has(BlockRegistries.LUNON_BRICKS.get()))
                .save(consumer, BlockRegistries.LUNON_BRICKS.getId());

        // Lunon bricks (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.POLISHED_LUNON.get()),
                        BlockRegistries.LUNON_BRICKS.get()
                )
                .unlocks("has_polished_lunon", has(BlockRegistries.POLISHED_LUNON.get()))
                .save(consumer, "lunon_bricks_stonecutter");

        // Lunon bricks slab
        ShapedRecipeBuilder.shaped(BlockRegistries.LUNON_BRICK_SLAB.get(), 6)
                .pattern("mmm")
                .define('m', BlockRegistries.LUNON_BRICKS.get())
                .unlockedBy("has_lunon_bricks", has(BlockRegistries.LUNON_BRICKS.get()))
                .save(consumer, BlockRegistries.LUNON_BRICK_SLAB.getId());

        // Lunon bricks slab (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.LUNON_BRICKS.get()),
                        BlockRegistries.LUNON_BRICK_SLAB.get()
                )
                .unlocks("has_lunon_bricks", has(BlockRegistries.LUNON_BRICKS.get()))
                .save(consumer, "lunon_bricks_slab_stonecutter");

        // Lunon bricks stairs
        ShapedRecipeBuilder.shaped(BlockRegistries.LUNON_BRICK_STAIRS.get(), 4)
                .pattern("m  ")
                .pattern("mm ")
                .pattern("mmm")
                .define('m', BlockRegistries.LUNON_BRICKS.get())
                .unlockedBy("has_lunon_bricks", has(BlockRegistries.LUNON_BRICKS.get()))
                .save(consumer, BlockRegistries.LUNON_BRICK_STAIRS.getId());

        // Lunon bricks stairs (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.LUNON_BRICKS.get()),
                        BlockRegistries.LUNON_BRICK_STAIRS.get()
                )
                .unlocks("has_lunon_bricks", has(BlockRegistries.LUNON_BRICKS.get()))
                .save(consumer, "lunon_bricks_stairs_stonecutter");


        // Polished Lunon
        ShapedRecipeBuilder.shaped(BlockRegistries.POLISHED_LUNON.get(), 4)
                .pattern("mm")
                .pattern("mm")
                .define('m', BlockRegistries.LUNON.get())
                .unlockedBy("has_polished_lunon", has(BlockRegistries.LUNON.get()))
                .save(consumer, BlockRegistries.POLISHED_LUNON.getId());

        // Polished Lunon (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.LUNON.get()),
                        BlockRegistries.POLISHED_LUNON.get()
                )
                .unlocks("has_lunon", has(BlockRegistries.LUNON.get()))
                .save(consumer, "polished_lunon_stonecutter");

        // Polished Lunon slab
        ShapedRecipeBuilder.shaped(BlockRegistries.POLISHED_LUNON_SLAB.get(), 6)
                .pattern("mmm")
                .define('m', BlockRegistries.POLISHED_LUNON.get())
                .unlockedBy("has_polished_lunon", has(BlockRegistries.POLISHED_LUNON.get()))
                .save(consumer, BlockRegistries.POLISHED_LUNON_SLAB.getId());

        // Polished Lunon slab (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.POLISHED_LUNON.get()),
                        BlockRegistries.POLISHED_LUNON_SLAB.get()
                )
                .unlocks("has_polished_lunon", has(BlockRegistries.POLISHED_LUNON.get()))
                .save(consumer, "polished_lunon_slab_stonecutter");

        // Chiseled Polished Lunon
        ShapedRecipeBuilder.shaped(BlockRegistries.CHISELED_POLISHED_LUNON.get(), 1)
                .pattern("m")
                .pattern("m")
                .define('m', BlockRegistries.POLISHED_LUNON_SLAB.get())
                .unlockedBy("has_polished_lunon_slab", has(BlockRegistries.POLISHED_LUNON_SLAB.get()))
                .save(consumer, BlockRegistries.CHISELED_POLISHED_LUNON.getId());

        // Polished Lunon (in stonecutter)
        SingleItemRecipeBuilder
                .stonecutting(
                        Ingredient.of(BlockRegistries.POLISHED_LUNON.get()),
                        BlockRegistries.CHISELED_POLISHED_LUNON.get()
                )
                .unlocks("has_polished_lunon", has(BlockRegistries.POLISHED_LUNON.get()))
                .save(consumer, "chiseled_polished_lunon_stonecutter");

        // Mossy lunon
        ShapelessRecipeBuilder
                .shapeless(BlockRegistries.MOSSY_LUNON.get())
                .requires(BlockRegistries.LUNON.get())
                .requires(Blocks.VINE).unlockedBy("has_vine", has(Blocks.VINE))
                .save(consumer);
    }
}
