package dev.ftb.mods.ftbskiescompanion.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import dev.ftb.mods.ftbskiescompanion.FTBSkiesCompanionForge;
import dev.ftb.mods.ftbskiescompanion.common.recipe.CrystallizationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registry.RECIPE_SERIALIZER_REGISTRY, FTBSkiesCompanionForge.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, FTBSkiesCompanionForge.MOD_ID);

    public static Supplier<RecipeType<CrystallizationRecipe>> CRYSTALLIZATION_RECIPE_TYPE = RECIPE_TYPES.register("crystallization", () -> CodecRecipeType.of("crystallization"));

    public static Supplier<RecipeSerializer<CrystallizationRecipe>> CRYSTALLIZATION_SERIALIZER = RECIPE_SERIALIZERS.register("crystallization", () -> new CodecRecipeSerializer<>(CRYSTALLIZATION_RECIPE_TYPE.get(), CrystallizationRecipe::codec));
}
