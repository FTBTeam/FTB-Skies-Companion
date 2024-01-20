package earth.terrarium.ftbskiescompanion.common;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.utility.Couple;
import earth.terrarium.ftbskiescompanion.StressInfo;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FTBStressProvider implements BlockStressValues.IStressValueProvider {
    public static final Map<Block, StressInfo> BLOCKS = new HashMap<>();
    public static final FTBStressProvider INSTANCE = new FTBStressProvider();
    public static final StressInfo DEFAULT = new StressInfo(0, 0, false, false, Couple.create(0, 0));

    @Override
    public double getImpact(Block block) {
        return BLOCKS.getOrDefault(block, DEFAULT).impact();
    }

    @Override
    public double getCapacity(Block block) {
        return BLOCKS.getOrDefault(block, DEFAULT).capacity();
    }

    @Override
    public boolean hasImpact(Block block) {
        return BLOCKS.getOrDefault(block, DEFAULT).hasImpact();
    }

    @Override
    public boolean hasCapacity(Block block) {
        return BLOCKS.getOrDefault(block, DEFAULT).hasCapacity();
    }

    @Nullable
    @Override
    public Couple<Integer> getGeneratedRPM(Block block) {
        return BLOCKS.getOrDefault(block, DEFAULT).generatedRPM();
    }
}
