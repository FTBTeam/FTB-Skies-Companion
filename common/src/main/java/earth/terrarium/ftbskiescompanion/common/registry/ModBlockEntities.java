package earth.terrarium.ftbskiescompanion.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.ftbskiescompanion.FTBSkiesCompanion;
import earth.terrarium.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITIES = ResourcefulRegistries.create(Registry.BLOCK_ENTITY_TYPE, FTBSkiesCompanion.MOD_ID);

    public static final Supplier<BlockEntityType<LiquidCrystallizerBlockEntity>> LIQUID_CRYSTALLIZER = BLOCK_ENTITIES.register("liquid_crystallizer", () -> BlockEntityType.Builder.of(LiquidCrystallizerBlockEntity::new, ModBlocks.LIQUID_CRYSTALLIZER.get()).build(null));
}
