package earth.terrarium.ftbskiescompanion.client.renderer;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import earth.terrarium.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class LiquidCrystallizerRenderer extends KineticBlockEntityRenderer<LiquidCrystallizerBlockEntity> {

	public LiquidCrystallizerRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(LiquidCrystallizerBlockEntity be, BlockState state) {
		return CachedBufferer.partial(AllPartialModels.MILLSTONE_COG, state);
	}

}
