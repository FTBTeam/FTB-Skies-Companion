package earth.terrarium.ftbskiescompanion.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModItems {

    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registry.ITEM, FTBSkiesCompanion.MOD_ID);

    public static final Supplier<BlockItem> LIQUID_CRYSTALLIZER = ITEMS.register("liquid_crystallizer", () -> new BlockItem(ModBlocks.LIQUID_CRYSTALLIZER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
