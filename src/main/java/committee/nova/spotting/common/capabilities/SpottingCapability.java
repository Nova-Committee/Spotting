package committee.nova.spotting.common.capabilities;

import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpottingCapability {
    @CapabilityInject(ISpottable.class)
    public static Capability<ISpottable> SPOTTABLE = null;

    @CapabilityInject(ISpotter.class)
    public static Capability<ISpotter> SPOTTER = null;

    public interface ISpottable extends INBTSerializable<IntNBT> {
        int getHighlightRemainTime();

        default boolean isHighlighted() {
            return getHighlightRemainTime() > 0;
        }

        void setHighlightRemainTime(int time);

        void goDark();

        int tick();
    }

    public interface ISpotter extends INBTSerializable<IntNBT> {
        void setSpottingCd(int cd);

        int getSpottingCd();

        default boolean canSpot() {
            return getSpottingCd() == 0;
        }

        int tick();
    }

    public static class Spottable implements ISpottable {
        private int highlight;

        @Override
        public int getHighlightRemainTime() {
            return highlight;
        }

        @Override
        public void setHighlightRemainTime(int time) {
            if (time < 0) return;
            highlight = time;
        }

        @Override
        public void goDark() {
            highlight = 0;
        }

        @Override
        public int tick() {
            if (highlight > 0) highlight--;
            return highlight;
        }

        @Override
        public IntNBT serializeNBT() {
            return IntNBT.valueOf(highlight);
        }

        @Override
        public void deserializeNBT(IntNBT nbt) {
            highlight = nbt.getInt();
        }
    }

    public static class Spotter implements ISpotter {
        private int cd;

        @Override
        public void setSpottingCd(int cd) {
            if (cd < 0) return;
            this.cd = cd;
        }

        @Override
        public int getSpottingCd() {
            return cd;
        }

        @Override
        public int tick() {
            if (cd > 0) cd--;
            return cd;
        }

        @Override
        public IntNBT serializeNBT() {
            return IntNBT.valueOf(cd);
        }

        @Override
        public void deserializeNBT(IntNBT nbt) {
            cd = nbt.getInt();
        }
    }

    public static class SpottableProvider implements ICapabilityProvider, INBTSerializable<IntNBT> {
        private Spottable spottable;

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == SPOTTABLE ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
        }

        @Override
        public IntNBT serializeNBT() {
            return getOrCreate().serializeNBT();
        }

        @Override
        public void deserializeNBT(IntNBT nbt) {
            getOrCreate().deserializeNBT(nbt);
        }

        @Nonnull
        Spottable getOrCreate() {
            if (spottable == null) spottable = new Spottable();
            return spottable;
        }
    }

    public static class SpotterProvider implements ICapabilityProvider, INBTSerializable<IntNBT> {
        private Spotter spotter;

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == SPOTTER ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
        }

        @Override
        public IntNBT serializeNBT() {
            return getOrCreate().serializeNBT();
        }

        @Override
        public void deserializeNBT(IntNBT nbt) {
            getOrCreate().deserializeNBT(nbt);
        }

        @Nonnull
        Spotter getOrCreate() {
            if (spotter == null) spotter = new Spotter();
            return spotter;
        }
    }
}
