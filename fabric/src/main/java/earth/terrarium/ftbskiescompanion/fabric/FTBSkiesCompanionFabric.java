package earth.terrarium.ftbskiescompanion.fabric;

import com.simibubi.create.foundation.utility.Couple;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.StressInfo;
import earth.terrarium.ftbskiescompanion.common.FTBStressProvider;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlocks;

public class FTBSkiesCompanionFabric {

    public static void init() {
        FTBSkiesCompanion.init();
        FTBStressProvider.BLOCKS.put(ModBlocks.LIQUID_CRYSTALLIZER.get(), new StressInfo(8.0, 1024, true, true, Couple.create(0, 1024)));
    }
}
