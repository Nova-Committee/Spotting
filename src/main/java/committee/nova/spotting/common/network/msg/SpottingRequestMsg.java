package committee.nova.spotting.common.network.msg;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.util.SpottingUtil;
import committee.nova.spotting.common.voice.VoiceManager;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class SpottingRequestMsg {
    private final int id;
    private final int target;
    private final IVoiceType voiceType;

    public SpottingRequestMsg(FriendlyByteBuf buffer) {
        id = buffer.readInt();
        target = buffer.readInt();
        voiceType = VoiceManager.getVoiceType(buffer.readUtf());
    }

    public SpottingRequestMsg(int id, int target, IVoiceType voiceType) {
        this.id = id;
        this.target = target;
        this.voiceType = voiceType;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeInt(target);
        buffer.writeUtf(voiceType.getVoiceId().toString());
    }

    public void handler(Supplier<NetworkEvent.Context> ctxSupplier) {
        final NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            final Level world = player.level;
            if (!Objects.equals(world.getEntity(id), player)) return;
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> {
                if (s.canSpot()) SpottingUtil.trySpot(player, world.getEntity(target), voiceType);
            });
        });
        ctx.setPacketHandled(true);
    }
}
