package earth.terrarium.ftbskiescompanion.client.renderer;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import earth.terrarium.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;

public class LiquidCrystallizerRotatingInstance extends SingleRotatingInstance<LiquidCrystallizerBlockEntity> {

    public LiquidCrystallizerRotatingInstance(MaterialManager materialManager, LiquidCrystallizerBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(AllPartialModels.MILLSTONE_COG, blockEntity.getBlockState());
    }
}