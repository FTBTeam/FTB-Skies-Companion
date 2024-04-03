package dev.ftb.mods.ftbskiescompanion.common.registry;

import dev.ftb.mods.ftbskiescompanion.FTBSkiesCompanionForge;
import dev.ftb.mods.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_REGISTRY, FTBSkiesCompanionForge.MOD_ID);

    public static final Supplier<BlockEntityType<LiquidCrystallizerBlockEntity>> LIQUID_CRYSTALLIZER = BLOCK_ENTITIES.register("liquid_crystallizer", () -> BlockEntityType.Builder.of(LiquidCrystallizerBlockEntity::new, ModBlocks.LIQUID_CRYSTALLIZER.get()).build(null));
}
