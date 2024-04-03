package dev.ftb.mods.ftbskiescompanion;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;
import dev.ftb.mods.ftbskiescompanion.client.renderer.LiquidCrystallizerRotatingInstance;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = FTBSkiesCompanionForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FTBSkiesCompanionForgeClient {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        InstancedRenderRegistry.configure(ModBlockEntities.LIQUID_CRYSTALLIZER.get())
                .factory(LiquidCrystallizerRotatingInstance::new)
                .skipRender(be -> true)
                .apply();
    }
}
