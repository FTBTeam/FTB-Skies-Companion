package earth.terrarium.ftbskiescompanion.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlocks;

public class AnimatedLiquidCrystallizer extends AnimatedKinetics {

	@Override
	public void draw(PoseStack matrixStack, int xOffset, int yOffset) {
		matrixStack.pushPose();
		matrixStack.translate(xOffset, yOffset, 0);
		AllGuiTextures.JEI_SHADOW.render(matrixStack, -16, 13);
		matrixStack.translate(-2, 18, 0);
		int scale = 22;

		blockElement(AllPartialModels.MILLSTONE_COG)
			.rotateBlock(22.5, getCurrentAngle() * 2, 0)
			.scale(scale)
			.render(matrixStack);

		blockElement(ModBlocks.LIQUID_CRYSTALLIZER.get().defaultBlockState())
			.rotateBlock(22.5, 22.5, 0)
			.scale(scale)
			.render(matrixStack);

		matrixStack.popPose();
	}

}