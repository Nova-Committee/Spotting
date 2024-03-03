package committee.nova.spotting.common.util;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.event.impl.SpottingEvent;
import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.CapabilitySyncMsg;
import committee.nova.vocalized.common.manager.VocalizedServerManager;
import committee.nova.vocalized.common.voice.VoiceContexts;
import committee.nova.vocalized.common.voice.VoiceEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class SpottingUtil {
    public static void trySpot(ServerPlayer player, @Nullable Entity spottee) {
        if (spottee == null) return;
        final SpottingEvent.TracingRange range = new SpottingEvent.TracingRange(player);
        MinecraftForge.EVENT_BUS.post(range);
        final double tracingRange = range.getTracingRange();
        final double realRange = spottee.position().distanceTo(player.getEyePosition(1.0F));
        if (tracingRange < realRange) {
            Spotting.LOGGER.warn("{} was sending a spotting request packet with a suspicious tracing range {}. " +
                            "While the maximum tracing range for the player was {}",
                    player.getDisplayName().getString(), realRange, tracingRange);
            Spotting.LOGGER.warn("If the real tracing range reported is much greater than the maximum tracing range. " +
                    "You can reasonably suspect that this player is using some cheat mods.");
            return;
        }
        spottee.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> {
            final SpottingEvent.Pre pre = new SpottingEvent.Pre(player, spottee);
            if (MinecraftForge.EVENT_BUS.post(pre)) return;
            s.setHighlightRemainTime(pre.getHighlightTime());
            player.swing(InteractionHand.MAIN_HAND, true);
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(p -> p.setSpottingCd(pre.getSpottingCd()));
            SpottingManager.getMessageIdForSpotted(spottee)
                    .ifPresent(msg -> VocalizedServerManager.sendVoiceMsg(
                            player,
                            msg,
                            VoiceContexts.dim(VoiceEffect.NONE),
                            spottee.getName().copy().withStyle(ChatFormatting.YELLOW)
                    ));
            MinecraftForge.EVENT_BUS.post(new SpottingEvent.Post(player, spottee));
            syncSpottingStatus(spottee);
        });
    }

    public static void syncSpottingStatus(Entity e) {
        final int[] data = new int[2];
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> data[0] = s.tick());
        if ((e instanceof Player))
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> data[1] = s.tick());
        NetworkHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> e.level().dimension()), new CapabilitySyncMsg(e.getId(), data[0], data[1]));
    }
}
