package earth.terrarium.ftbskiescompanion.compat;

import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class FTBSkiesJEI implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(FTBSkiesCompanion.MOD_ID, "jei");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystallizationCategory());
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addRecipes(CrystallizationCategory.RECIPE, CrystallizationCategory.INFO.recipes().get());
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        CrystallizationCategory.INFO.catalysts().forEach(catalyst -> registration.addRecipeCatalyst(catalyst.get(), CrystallizationCategory.RECIPE));
    }
}
