package committee.nova.spotting.common.event.handler;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(SpottingCapability.ISpotter.class, getNilStorage(), () -> null);
            CapabilityManager.INSTANCE.register(SpottingCapability.ISpottable.class, getNilStorage(), () -> null);
        });
    }

    private static <T> Capability.IStorage<T> getNilStorage() {
        return new Capability.IStorage<T>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
                return null;
            }

            @Override
            public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {

            }
        };
    }
}
