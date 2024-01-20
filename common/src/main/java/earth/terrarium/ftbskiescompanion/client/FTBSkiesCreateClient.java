package earth.terrarium.ftbskiescompanion.client;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;
import earth.terrarium.ftbskiescompanion.client.renderer.LiquidCrystallizerRotatingInstance;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlockEntities;

public class FTBSkiesCreateClient {

    public static void init() {
        InstancedRenderRegistry.configure(ModBlockEntities.LIQUID_CRYSTALLIZER.get())
            .factory(LiquidCrystallizerRotatingInstance::new)
            .skipRender(be -> true)
            .apply();
    }
}
