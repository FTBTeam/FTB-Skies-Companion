package dev.ftb.mods.ftbskiescompanion.common.blockentity;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.VecHelper;
import earth.terrarium.botarium.common.fluid.FluidConstants;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.impl.InsertOnlyFluidContainer;
import earth.terrarium.botarium.common.item.SerializableContainer;
import earth.terrarium.botarium.common.item.SimpleItemContainer;
import earth.terrarium.botarium.common.fluid.base.BotariumFluidBlock;
import earth.terrarium.botarium.common.fluid.impl.WrappedBlockFluidContainer;
import earth.terrarium.botarium.common.item.ItemContainerBlock;
import dev.ftb.mods.ftbskiescompanion.common.recipe.CrystallizationRecipe;
import dev.ftb.mods.ftbskiescompanion.common.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaReceiver;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.xplat.XplatAbstractions;

public class LiquidCrystallizerBlockEntity extends KineticBlockEntity implements ItemContainerBlock, BotariumFluidBlock<WrappedBlockFluidContainer>, ManaReceiver {
    private SerializableContainer container;
    private WrappedBlockFluidContainer fluidContainer;
    private int timer;
    private int mana;
    private CrystallizationRecipe lastRecipe;

    public LiquidCrystallizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIQUID_CRYSTALLIZER.get(), pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        if (getSpeed() == 0)
            return;

        if (timer > 0) {
            timer -= getProcessingSpeed();

            if (level.isClientSide) {
                spawnParticles();
                return;
            }
            if (timer <= 0) process();

            return;
        }

        if (getFluidContainer().isEmpty()) return;

        FluidHolder holder = getFluidContainer().getFluids().get(0);
        if (lastRecipe == null || !lastRecipe.input().test(holder)) {
            CrystallizationRecipe recipe = CrystallizationRecipe.find(holder, level);
            if (recipe == null) {
                lastRecipe = null;
                timer = 100;
                sendData();
            } else {
                lastRecipe = recipe;
                timer = lastRecipe.processTime();
                sendData();
            }
            return;
        }

        timer = lastRecipe.processTime();
        sendData();
    }

    private void process() {
        FluidHolder holder = getFluidContainer().getFluids().get(0);
        if (lastRecipe == null || !lastRecipe.input().test(holder)) {
            CrystallizationRecipe recipe = CrystallizationRecipe.find(holder, level);
            if (recipe == null)
                return;
            lastRecipe = recipe;
        }

        if (mana < lastRecipe.mana()) return;

        ItemStack stack = XplatAbstractions.INSTANCE.insertToInventory(level, getBlockPos(), null, lastRecipe.result().copy(), true);

        if (stack.isEmpty()) {
            XplatAbstractions.INSTANCE.insertToInventory(level, getBlockPos(), null, lastRecipe.result().copy(), false);
            FluidHolder toExtract = holder.copyWithAmount(lastRecipe.input().getFluidAmount());
            getFluidContainer().internalExtract(toExtract, false);
            mana -= lastRecipe.mana();
        }

        sendData();
        setChanged();
    }

    public int getProcessingSpeed() {
        return Mth.clamp((int) Math.abs(getSpeed() / 16f), 1, 512);
    }

    public void spawnParticles() {
        if (lastRecipe == null) return;
        ItemStack stackInSlot = lastRecipe.result().copy();

        ItemParticleOption data = new ItemParticleOption(ParticleTypes.ITEM, stackInSlot);
        float angle = level.random.nextFloat() * 360;
        Vec3 offset = new Vec3(0, 0, 0.5f);
        offset = VecHelper.rotate(offset, angle, Direction.Axis.Y);
        Vec3 target = VecHelper.rotate(offset, getSpeed() > 0 ? 25 : -25, Direction.Axis.Y);

        Vec3 center = offset.add(VecHelper.getCenterOf(worldPosition));
        target = VecHelper.offsetRandomly(target.subtract(offset), level.random, 1 / 128f);
        level.addParticle(data, center.x, center.y, center.z, target.x, target.y, target.z);
    }

    @Override
    public SerializableContainer getContainer() {
        if (this.container == null) {
            this.container = new SimpleItemContainer(this, 1);
        }
        return container;
    }

    @Override
    public WrappedBlockFluidContainer getFluidContainer(Level level, BlockPos pos, BlockState state, @Nullable BlockEntity entity, @Nullable Direction direction) {
        if (this.fluidContainer == null) {
            this.fluidContainer = new WrappedBlockFluidContainer(this, new InsertOnlyFluidContainer((ignored) -> FluidConstants.toMillibuckets(4000), 1, (slot, holder) -> true));
        }
        return direction == Direction.UP || direction == null ? fluidContainer : null;
    }

    public WrappedBlockFluidContainer getFluidContainer() {
        return getFluidContainer(level, worldPosition, getBlockState(), this, Direction.UP);
    }

    @Override
    public Level getManaReceiverLevel() {
        return level;
    }

    @Override
    public BlockPos getManaReceiverPos() {
        return getBlockPos();
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    public static int getMaxMana() {
        return ManaPoolBlockEntity.MAX_MANA / 10;
    }

    @Override
    public boolean isFull() {
        return mana >= getMaxMana();
    }

    @Override
    public void receiveMana(int mana) {
        int old = this.mana;
        this.mana = Math.max(0, Math.min(getCurrentMana() + mana, getMaxMana()));
        if (old != this.mana) {
            setChanged();
            sendData();
        }
    }

    @Override
    public boolean canReceiveManaFromBursts() {
        return true;
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);

        compound.putInt("Mana", mana);
        compound.putInt("Timer", timer);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        mana = compound.getInt("Mana");
        timer = compound.getInt("Timer");
    }
}
