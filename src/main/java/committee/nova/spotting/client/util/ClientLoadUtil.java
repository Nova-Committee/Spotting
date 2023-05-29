package committee.nova.spotting.client.util;

import committee.nova.spotting.client.config.screen.ClientConfigScreen;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class ClientLoadUtil {
    public static DistExecutor.SafeRunnable handleCapabilitySyncMsg(int id, int highlight, int cd) {
        return () -> {
            final Level world = Minecraft.getInstance().level;
            if (world == null) return;
            final Entity e = world.getEntity(id);
            if (e == null) return;
            e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> s.setHighlightRemainTime(highlight));
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> s.setSpottingCd(cd));
        };
    }

    public static Supplier<ConfigGuiHandler.ConfigGuiFactory> getCfgSupplier() {
        return () -> new ConfigGuiHandler.ConfigGuiFactory((mc, s) -> new ClientConfigScreen(s));
    }
}
