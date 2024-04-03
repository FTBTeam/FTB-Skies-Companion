package dev.ftb.mods.ftbskiescompanion.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.DoubleItemIcon;
import com.simibubi.create.compat.jei.EmptyBackground;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import dev.ftb.mods.ftbskiescompanion.FTBSkiesCompanionForge;
import dev.ftb.mods.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModItems;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModRecipes;
import earth.terrarium.botarium.common.fluid.FluidConstants;
import dev.ftb.mods.ftbskiescompanion.client.AnimatedLiquidCrystallizer;
import dev.ftb.mods.ftbskiescompanion.common.recipe.CrystallizationRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vazkii.botania.client.gui.HUDHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class CrystallizationCategory extends CreateRecipeCategory<CrystallizationRecipe> {

    private final AnimatedLiquidCrystallizer millstone = new AnimatedLiquidCrystallizer();
    public static final RecipeType<CrystallizationRecipe> RECIPE = new RecipeType<>(new ResourceLocation(FTBSkiesCompanionForge.MOD_ID, "crystallization"), CrystallizationRecipe.class);
    public static CreateRecipeCategory.Info<CrystallizationRecipe> INFO = new CreateRecipeCategory.Info<>(
        CrystallizationCategory.RECIPE,
        Component.translatable("recipe.ftbskies_companion.crystallization"),
        new EmptyBackground(177, 59),
        new DoubleItemIcon(() -> ModItems.LIQUID_CRYSTALLIZER.get().getDefaultInstance(), Items.WATER_BUCKET::getDefaultInstance),
        () -> Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(ModRecipes.CRYSTALLIZATION_RECIPE_TYPE.get()),
        List.<Supplier<? extends ItemStack>>of(() -> ModItems.LIQUID_CRYSTALLIZER.get().getDefaultInstance())
    );

    public CrystallizationCategory() {
        super(INFO);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystallizationRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder fluidSlot = builder
            .addSlot(RecipeIngredientRole.INPUT, 15, 9)
            .setBackground(getRenderedSlot(), -1, -1);

        recipe.input().getFluids().forEach(fluid -> {
            fluidSlot.addFluidStack(fluid.getFluid(), fluid.getFluidAmount());
        });

        fluidSlot.addTooltipCallback((recipeSlotView, tooltip) -> tooltip.add(Component.translatable("recipe.ftbskies_companion.crystallization.mb", FluidConstants.toMillibuckets(recipe.input().getFluidAmount()))));

        builder
            .addSlot(RecipeIngredientRole.OUTPUT, 139, 27)
            .setBackground(getRenderedSlot(1), -1, -1)
            .addItemStack(recipe.result().copy());
    }

    @Override
    public void draw(CrystallizationRecipe recipe, IRecipeSlotsView iRecipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
        AllGuiTextures.JEI_ARROW.render(matrixStack, 85, 32);
        AllGuiTextures.JEI_DOWN_ARROW.render(matrixStack, 43, 4);
        millstone.draw(matrixStack, 48, 27);
        HUDHandler.renderManaBar(matrixStack, 15, 55, 0x0000FF, 0.75F, recipe.mana(), LiquidCrystallizerBlockEntity.getMaxMana());
    }
}
