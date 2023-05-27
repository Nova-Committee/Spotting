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
    private final boolean male;

    public SpottingRequestMsg(PacketBuffer buffer) {
        id = buffer.readInt();
        target = buffer.readInt();
        male = buffer.readBoolean();
    }

    public SpottingRequestMsg(int id, int target, boolean male) {
        this.id = id;
        this.target = target;
        this.male = male;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(id);
        buffer.writeInt(target);
        buffer.writeBoolean(male);
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            final World world = player.world;
            if (!Objects.equals(world.getEntityByID(id), player)) return;
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> {
                if (s.canSpot()) SpottingUtil.trySpot(player, world.getEntityByID(target), male);
            });
        });
        ctx.setPacketHandled(true);
    }
}
