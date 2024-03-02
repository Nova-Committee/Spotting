package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.util.SpottingUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class SpottingRequestMsg {
    private final int id;
    private final int target;

    public SpottingRequestMsg(FriendlyByteBuf buffer) {
        id = buffer.readInt();
        target = buffer.readInt();
    }

    public SpottingRequestMsg(int id, int target) {
        this.id = id;
        this.target = target;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeInt(target);
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            final Level world = player.level();
            if (!Objects.equals(world.getEntity(id), player)) return;
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> {
                if (s.canSpot()) SpottingUtil.trySpot(player, world.getEntity(target));
            });
        });
        ctx.setPacketHandled(true);
    }
}