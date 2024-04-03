package dev.ftb.mods.ftbskiescompanion.common.registry;

import dev.ftb.mods.ftbskiescompanion.FTBSkiesCompanionForge;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_REGISTRY, FTBSkiesCompanionForge.MOD_ID);

    public static final Supplier<BlockItem> LIQUID_CRYSTALLIZER = ITEMS.register("liquid_crystallizer", () -> new BlockItem(ModBlocks.LIQUID_CRYSTALLIZER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
