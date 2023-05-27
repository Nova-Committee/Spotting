package committee.nova.spotting.client.event.handler;

import committee.nova.spotting.client.config.ClientConfig;
import committee.nova.spotting.client.keybinding.KeyBindings;
import committee.nova.spotting.client.util.RaytraceUtil;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.SpottingRequestMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEventHandler {
    @SubscribeEvent
    public static void onInput(InputEvent.KeyInputEvent event) {
        if (!KeyBindings.spotting.isPressed()) return;
        final Minecraft mc = Minecraft.getInstance();
        final PlayerEntity player = mc.player;
        if (player == null) return;
        RaytraceUtil.getEntityInCrosshair(mc.getRenderPartialTicks(), 120.0)
                .ifPresent(l -> NetworkHandler.INSTANCE.sendToServer(new SpottingRequestMsg(player.getEntityId(), l.getEntityId(), ClientConfig.VOICE_GENDER.get().isMale())));
    }
}
