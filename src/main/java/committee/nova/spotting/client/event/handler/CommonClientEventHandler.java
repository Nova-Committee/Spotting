package committee.nova.spotting.client.event.handler;

import committee.nova.spotting.client.keybinding.KeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CommonClientEventHandler {
    @SubscribeEvent
    public static void onKeyBinding(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.spotting);
    }
}
