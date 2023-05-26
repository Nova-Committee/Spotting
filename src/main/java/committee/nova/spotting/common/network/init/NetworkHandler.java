package committee.nova.spotting.common.network.init;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.network.msg.CapabilitySyncMsg;
import committee.nova.spotting.common.network.msg.SpottingRequestMsg;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int id = 0;

    public static int nextId() {
        return id++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Spotting.MODID, Spotting.MODID),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(SpottingRequestMsg.class, nextId())
                .encoder(SpottingRequestMsg::toBytes)
                .decoder(SpottingRequestMsg::new)
                .consumer(SpottingRequestMsg::handler)
                .add();
        INSTANCE.messageBuilder(CapabilitySyncMsg.class, nextId())
                .encoder(CapabilitySyncMsg::toBytes)
                .decoder(CapabilitySyncMsg::new)
                .consumer(CapabilitySyncMsg::handler)
                .add();
    }
}
