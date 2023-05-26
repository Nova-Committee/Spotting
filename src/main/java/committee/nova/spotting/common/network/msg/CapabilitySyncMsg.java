package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CapabilitySyncMsg {
    private final int id;
    private final int highlight;
    private final int cd;

    public CapabilitySyncMsg(PacketBuffer buffer) {
        id = buffer.readInt();
        highlight = buffer.readInt();
        cd = buffer.readInt();
    }

    public CapabilitySyncMsg(int id, int highlight, int cd) {
        this.id = id;
        this.highlight = highlight;
        this.cd = cd;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(id);
        buffer.writeInt(highlight);
        buffer.writeInt(cd);
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final World world = Minecraft.getInstance().world;
            if (world == null) return;
            final Entity e = world.getEntityByID(id);
            if (e == null) return;
            e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> s.setHighlightRemainTime(highlight));
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> s.setSpottingCd(cd));
        });
        ctx.setPacketHandled(true);
    }
}
