package earth.terrarium.ftbskiescompanion.forge;

import com.simibubi.create.foundation.utility.Couple;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.StressInfo;
import earth.terrarium.ftbskiescompanion.common.FTBStressProvider;
import earth.terrarium.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.forge.CapabilityUtil;

@Mod(FTBSkiesCompanion.MOD_ID)
public class FTBSkiesCompanionForge {

    public FTBSkiesCompanionForge() {
        FTBSkiesCompanion.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(FTBSkiesCompanionForge::wrapUp);
        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, FTBSkiesCompanionForge::manaCaps);
    }

    public static void wrapUp(FMLCommonSetupEvent event) {
        FTBSkiesCompanion.initStress();
    }

    public static void manaCaps(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof LiquidCrystallizerBlockEntity be) {
            event.addCapability(new ResourceLocation(FTBSkiesCompanion.MOD_ID, "mana"), CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_RECEIVER, be));
        }
    }
}
