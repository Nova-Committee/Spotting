package committee.nova.spotting.common.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpottingCapability {
    public static final Capability<ISpottable> SPOTTABLE = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ISpotter> SPOTTER = CapabilityManager.get(new CapabilityToken<>() {
    });

    public interface ISpottable extends INBTSerializable<IntTag> {
        int getHighlightRemainTime();

        default boolean isHighlighted() {
            return getHighlightRemainTime() > 0;
        }

        void setHighlightRemainTime(int time);

        void goDark();

        int tick();
    }

    public interface ISpotter extends INBTSerializable<IntTag> {
        void setSpottingCd(int cd);

        int getSpottingCd();

        default boolean canSpot() {
            return getSpottingCd() == 0;
        }

        default void clearCd() {
            setSpottingCd(0);
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
        public IntTag serializeNBT() {
            return IntTag.valueOf(highlight);
        }

        @Override
        public void deserializeNBT(IntTag nbt) {
            highlight = nbt.getAsInt();
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
        public IntTag serializeNBT() {
            return IntTag.valueOf(cd);
        }

        @Override
        public void deserializeNBT(IntTag nbt) {
            cd = nbt.getAsInt();
        }
    }

    public static class SpottableProvider implements ICapabilityProvider, INBTSerializable<IntTag> {
        private Spottable spottable;

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == SPOTTABLE ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
        }

        @Override
        public IntTag serializeNBT() {
            return getOrCreate().serializeNBT();
        }

        @Override
        public void deserializeNBT(IntTag nbt) {
            getOrCreate().deserializeNBT(nbt);
        }

        @Nonnull
        Spottable getOrCreate() {
            if (spottable == null) spottable = new Spottable();
            return spottable;
        }
    }

    public static class SpotterProvider implements ICapabilityProvider, INBTSerializable<IntTag> {
        private Spotter spotter;

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == SPOTTER ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
        }

        @Override
        public IntTag serializeNBT() {
            return getOrCreate().serializeNBT();
        }

        @Override
        public void deserializeNBT(IntTag nbt) {
            getOrCreate().deserializeNBT(nbt);
        }

        @Nonnull
        Spotter getOrCreate() {
            if (spotter == null) spotter = new Spotter();
            return spotter;
        }
    }
}
