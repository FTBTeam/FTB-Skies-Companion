package earth.terrarium.ftbskiescompanion.client.fabric;

import earth.terrarium.ftbskiescompanion.client.FTBSkiesCreateClient;
import net.fabricmc.api.ClientModInitializer;

public class FTBSkiesCompanionFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FTBSkiesCreateClient.init();
    }
}
