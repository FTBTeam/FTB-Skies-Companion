package earth.terrarium.ftbskiescompanion.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.QuantifiedFluidIngredient;
import earth.terrarium.ftbskiescompanion.common.registry.ModRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

@NonnullDefault
public record CrystallizationRecipe(ResourceLocation id, QuantifiedFluidIngredient input, int processTime, int mana, ItemStack result) implements CodecRecipe<Container> {

    public static Codec<CrystallizationRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                QuantifiedFluidIngredient.CODEC.fieldOf("input").forGetter(CrystallizationRecipe::input),
                Codec.INT.fieldOf("processTime").forGetter(CrystallizationRecipe::processTime),
                Codec.INT.fieldOf("mana").forGetter(CrystallizationRecipe::mana),
                ItemStackCodec.CODEC.fieldOf("output").forGetter(CrystallizationRecipe::result)
        ).apply(instance, CrystallizationRecipe::new));
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRYSTALLIZATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CRYSTALLIZATION_RECIPE_TYPE.get();
    }

    @Nullable
    public static CrystallizationRecipe find(FluidHolder holder, Level level) {
        return level.getRecipeManager().getAllRecipesFor(ModRecipes.CRYSTALLIZATION_RECIPE_TYPE.get()).stream().filter(recipe -> recipe.input.test(holder)).findFirst().orElse(null);
    }
}
