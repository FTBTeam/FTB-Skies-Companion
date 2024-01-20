package earth.terrarium.ftbskiescompanion.fabric;

import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.StressInfo;
import earth.terrarium.ftbskiescompanion.common.FTBStressProvider;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;

public class FTBSkiesCompanionFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FTBSkiesCompanion.init();
        FTBSkiesCompanion.initStress();
    }
}
