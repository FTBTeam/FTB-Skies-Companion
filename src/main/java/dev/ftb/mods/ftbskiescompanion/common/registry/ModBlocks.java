package dev.ftb.mods.ftbskiescompanion.common.registry;

import dev.ftb.mods.ftbskiescompanion.FTBSkiesCompanionForge;
import dev.ftb.mods.ftbskiescompanion.common.block.LiquidCrystallizerBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, FTBSkiesCompanionForge.MOD_ID);

    public static final Supplier<Block> LIQUID_CRYSTALLIZER = BLOCKS.register("liquid_crystallizer", () -> new LiquidCrystallizerBlock(Block.Properties.of(Material.METAL).strength(2.0F).sound(SoundType.METAL).noOcclusion()));
}
