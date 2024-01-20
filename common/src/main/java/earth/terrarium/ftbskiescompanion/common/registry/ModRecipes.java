package earth.terrarium.ftbskiescompanion.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.common.recipe.CrystallizationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {
    public static final ResourcefulRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ResourcefulRegistries.create(Registry.RECIPE_SERIALIZER, FTBSkiesCompanion.MOD_ID);
    public static final ResourcefulRegistry<RecipeType<?>> RECIPE_TYPES = ResourcefulRegistries.create(Registry.RECIPE_TYPE, FTBSkiesCompanion.MOD_ID);

    public static Supplier<RecipeType<CrystallizationRecipe>> CRYSTALLIZATION_RECIPE_TYPE = RECIPE_TYPES.register("crystallization", () -> CodecRecipeType.of("crystallization"));

    public static Supplier<RecipeSerializer<CrystallizationRecipe>> CRYSTALLIZATION_SERIALIZER = RECIPE_SERIALIZERS.register("crystallization", () -> new CodecRecipeSerializer<>(CRYSTALLIZATION_RECIPE_TYPE.get(), CrystallizationRecipe::codec));
}
