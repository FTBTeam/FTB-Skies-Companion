package earth.terrarium.ftbskiescompanion;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.utility.Couple;
import earth.terrarium.ftbskiescompanion.common.FTBStressProvider;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlockEntities;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlocks;
import earth.terrarium.ftbskiescompanion.common.registry.ModItems;
import earth.terrarium.ftbskiescompanion.common.registry.ModRecipes;

public class FTBSkiesCompanion {

    public static final String MOD_ID = "ftbskies_companion";

    public static void init() {
        ModRecipes.RECIPE_TYPES.init();
        ModRecipes.RECIPE_SERIALIZERS.init();
        ModBlockEntities.BLOCK_ENTITIES.init();
        ModBlocks.BLOCKS.init();
        ModItems.ITEMS.init();

        BlockStressValues.registerProvider(MOD_ID, FTBStressProvider.INSTANCE);
    }

    public static void initStress() {
        FTBStressProvider.BLOCKS.put(ModBlocks.LIQUID_CRYSTALLIZER.get(), new StressInfo(8.0, 1024, true, true, Couple.create(0, 1024)));
    }
}
