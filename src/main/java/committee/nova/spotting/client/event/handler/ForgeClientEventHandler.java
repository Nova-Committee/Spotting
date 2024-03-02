package committee.nova.spotting.client.event.handler;

import committee.nova.spotting.client.keybinding.KeyBindings;
import committee.nova.spotting.client.util.RaytraceUtil;
import committee.nova.spotting.common.event.impl.SpottingEvent;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.SpottingRequestMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEventHandler {
    @SubscribeEvent
    public static void onInput(InputEvent.Key event) {
        if (!KeyBindings.spotting.consumeClick()) return;
        final Minecraft mc = Minecraft.getInstance();
        final Player player = mc.player;
        if (player == null) return;
        final SpottingEvent.TracingRange range = new SpottingEvent.TracingRange(player);
        MinecraftForge.EVENT_BUS.post(range);
        RaytraceUtil.getEntityInCrosshair(mc.getFrameTime(), range.getTracingRange())
                .ifPresent(l -> NetworkHandler.INSTANCE.sendToServer(new SpottingRequestMsg(player.getId(), l.getId())));
    }
}
