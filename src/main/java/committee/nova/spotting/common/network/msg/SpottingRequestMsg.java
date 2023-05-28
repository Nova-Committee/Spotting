package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.util.SpottingUtil;
import committee.nova.spotting.common.voice.VoiceManager;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class SpottingRequestMsg {
    private final int id;
    private final int target;
    private final IVoiceType voiceType;

    public SpottingRequestMsg(PacketBuffer buffer) {
        id = buffer.readInt();
        target = buffer.readInt();
        voiceType = VoiceManager.getVoiceType(buffer.readString());
    }

    public SpottingRequestMsg(int id, int target, IVoiceType voiceType) {
        this.id = id;
        this.target = target;
        this.voiceType = voiceType;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(id);
        buffer.writeInt(target);
        buffer.writeString(voiceType.getVoiceId().toString());
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            final World world = player.world;
            if (!Objects.equals(world.getEntityByID(id), player)) return;
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> {
                if (s.canSpot()) SpottingUtil.trySpot(player, world.getEntityByID(target), voiceType);
            });
        });
        ctx.setPacketHandled(true);
    }
}
