package committee.nova.spotting.common.util;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.event.impl.SpottingEvent;
import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.CapabilitySyncMsg;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class SpottingUtil {
    public static void trySpot(Player player, @Nullable Entity spottee, IVoiceType voiceType) {
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
                    "You can reasonably suspect that this player is using some cheat mod.");
            return;
        }
        final AtomicReference<Component> traced = new AtomicReference<>(null);
        final AtomicReference<BlockPos> hit = new AtomicReference<>(null);
        spottee.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> {
            final SpottingEvent.Pre pre = new SpottingEvent.Pre(player, spottee);
            if (MinecraftForge.EVENT_BUS.post(pre)) return;
            s.setHighlightRemainTime(pre.getHighlightTime());
            player.swing(InteractionHand.MAIN_HAND, true);
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(p -> p.setSpottingCd(pre.getSpottingCd()));
            final SoundEvent sound = SpottingManager.getSoundForSpotted(spottee, voiceType);
            if (sound != null)
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 1.0F, 1.0F);
            traced.set(Component.literal(spottee.getName().getString()).setStyle(Style.EMPTY).withStyle(y -> y.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY,
                    new HoverEvent.EntityTooltipInfo(spottee.getType(), spottee.getUUID(), spottee.getName())))));
            hit.set(spottee.blockPosition());
            MinecraftForge.EVENT_BUS.post(new SpottingEvent.Post(player, spottee));
            syncSpottingStatus(spottee);
        });
        if (traced.get() != null) {
            final MinecraftServer server = player.getServer();
            if (server == null) return;
            server.getPlayerList().getPlayers().stream()
                    .filter(p -> p.level().dimension().equals(player.level().dimension()))
                    .forEach(p -> p.sendSystemMessage(getSpottingMsg(player.getDisplayName(), traced.get(), hit.get(),
                            voiceType.get$Spotted$MessageKey(), voiceType.get$There$MessageKey())));
        }
    }

    public static Component getSpottingMsg(Component player, Component traced, @Nullable BlockPos hit, String spottedKey, String thereKey) {
        return Component.translatable("chat.type.text", player, Component.translatable(spottedKey,
                Component.translatable(thereKey)
                        .setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW))
                        .withStyle(s -> {
                            if (hit == null) return s;
                            return s.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("[" + hit.getX() + ", " + hit.getY() + ", " + hit.getZ() + "]")));
                        }), traced));
    }

    public static void syncSpottingStatus(Entity e) {
        final int[] datas = new int[2];
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> datas[0] = s.tick());
        if ((e instanceof Player))
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> datas[1] = s.tick());
        NetworkHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> e.level().dimension()), new CapabilitySyncMsg(e.getId(), datas[0], datas[1]));
    }
}
