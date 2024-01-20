package earth.terrarium.ftbskiescompanion.common.block;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import earth.terrarium.botarium.common.item.SerializableContainer;
import earth.terrarium.ftbskiescompanion.common.blockentity.LiquidCrystallizerBlockEntity;
import earth.terrarium.ftbskiescompanion.common.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.lwjgl.system.NonnullDefault;

@NonnullDefault
public class LiquidCrystallizerBlock extends KineticBlock implements IBE<LiquidCrystallizerBlockEntity>, ICogWheel {
    public LiquidCrystallizerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AllShapes.MILLSTONE;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == Direction.DOWN;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                 BlockHitResult hit) {
        if (worldIn.isClientSide)
            return InteractionResult.SUCCESS;

        withBlockEntityDo(worldIn, pos, millstone -> {
            SerializableContainer inv = millstone.getContainer();
            if (!inv.isEmpty()) {
                for (int slot = 0; slot < inv.getContainerSize(); slot++) {
                    player.getInventory()
                        .placeItemBackInInventory(inv.getItem(slot));
                    inv.setItem(slot, ItemStack.EMPTY);
                }
            }

            millstone.setChanged();
            millstone.sendData();
        });

        return InteractionResult.SUCCESS;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public Class<LiquidCrystallizerBlockEntity> getBlockEntityClass() {
        return LiquidCrystallizerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends LiquidCrystallizerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.LIQUID_CRYSTALLIZER.get();
    }
}
