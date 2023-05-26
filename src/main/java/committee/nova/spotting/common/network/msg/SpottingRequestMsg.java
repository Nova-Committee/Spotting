package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.util.SpottingUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class SpottingRequestMsg {
    private final int id;
    private final int target;

    public SpottingRequestMsg(PacketBuffer buffer) {
        id = buffer.readInt();
        target = buffer.readInt();
    }

    public SpottingRequestMsg(int id, int target) {
        this.id = id;
        this.target = target;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(id);
        buffer.writeInt(target);
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            final World world = player.world;
            if (!Objects.equals(world.getEntityByID(id), player)) return;
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> {
                if (s.canSpot()) SpottingUtil.trySpot(player, world.getEntityByID(target));
            });
        });
        ctx.setPacketHandled(true);
    }
}
