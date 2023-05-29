package committee.nova.spotting.client.util;

import committee.nova.spotting.client.config.screen.ClientConfigScreen;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ClientLoadUtil {
    public static DistExecutor.SafeRunnable handleCapabilitySyncMsg(int id, int highlight, int cd) {
        return () -> {
            final World world = Minecraft.getInstance().world;
            if (world == null) return;
            final Entity e = world.getEntityByID(id);
            if (e == null) return;
            e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> s.setHighlightRemainTime(highlight));
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> s.setSpottingCd(cd));
        };
    }

    public static Supplier<BiFunction<Minecraft, Screen, Screen>> getCfgScreenSupplier() {
        return () -> (mc, s) -> new ClientConfigScreen(s);
    }
}
