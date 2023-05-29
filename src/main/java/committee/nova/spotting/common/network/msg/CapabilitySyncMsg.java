package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.client.util.ClientPacketUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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
        ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientPacketUtil.handleCapabilitySyncMsg(id, highlight, cd)));
        ctx.setPacketHandled(true);
    }
}
