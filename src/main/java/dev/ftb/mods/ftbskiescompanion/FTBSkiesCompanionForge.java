package dev.ftb.mods.ftbskiescompanion;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.utility.Couple;
import dev.ftb.mods.ftbskiescompanion.common.FTBStressProvider;
import dev.ftb.mods.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModBlockEntities;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModBlocks;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModItems;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModRecipes;
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

@Mod(FTBSkiesCompanionForge.MOD_ID)
public class FTBSkiesCompanionForge {

    public static final String MOD_ID = "ftbskies_companion";

    public FTBSkiesCompanionForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRecipes.RECIPE_TYPES.register(eventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(eventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);

        BlockStressValues.registerProvider(MOD_ID, FTBStressProvider.INSTANCE);
        eventBus.addListener(FTBSkiesCompanionForge::wrapUp);
        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, FTBSkiesCompanionForge::manaCaps);
    }

    public static void wrapUp(FMLCommonSetupEvent event) {
        FTBStressProvider.BLOCKS.put(ModBlocks.LIQUID_CRYSTALLIZER.get(), new StressInfo(8.0, 1024, true, true, Couple.create(0, 1024)));
    }

    public static void manaCaps(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof LiquidCrystallizerBlockEntity be) {
            event.addCapability(new ResourceLocation(MOD_ID, "mana"), CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_RECEIVER, be));
        }
    }
}
