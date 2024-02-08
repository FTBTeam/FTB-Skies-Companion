package earth.terrarium.ftbskiescompanion.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.common.block.LiquidCrystallizerBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class ModBlocks {
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, FTBSkiesCompanion.MOD_ID);

    public static final Supplier<Block> LIQUID_CRYSTALLIZER = BLOCKS.register("liquid_crystallizer", () -> new LiquidCrystallizerBlock(Block.Properties.of(Material.METAL).strength(2.0F).sound(SoundType.METAL).noOcclusion()));
}
