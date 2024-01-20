package earth.terrarium.ftbskiescompanion.forge;

import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.client.FTBSkiesCreateClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = FTBSkiesCompanion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FTBSkiesCompanionForgeClient {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        FTBSkiesCreateClient.init();
    }
}
