package committee.nova.spotting.common.util;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.event.impl.SpottingEvent;
import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.CapabilitySyncMsg;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class SpottingUtil {
    public static void trySpot(PlayerEntity player, @Nullable Entity spottee, IVoiceType voiceType) {
        if (spottee == null) return;
        final SpottingEvent.TracingRange range = new SpottingEvent.TracingRange(player);
        MinecraftForge.EVENT_BUS.post(range);
        final double tracingRange = range.getTracingRange();
        final double realRange = spottee.getPositionVec().distanceTo(player.getEyePosition(1.0F));
        if (tracingRange < realRange) {
            Spotting.LOGGER.warn("{} was sending a spotting request packet with a suspicious tracing range {}. " +
                            "While the maximum tracing range for the player was {}",
                    player.getDisplayName().getString(), realRange, tracingRange);
            Spotting.LOGGER.warn("If the real tracing range reported is much greater than the maximum tracing range. " +
                    "You can reasonably suspect that this player is using some cheat mod.");
            return;
        }
        final AtomicReference<ITextComponent> traced = new AtomicReference<>(null);
        final AtomicReference<BlockPos> hit = new AtomicReference<>(null);
        spottee.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> {
            final SpottingEvent.Pre pre = new SpottingEvent.Pre(player, spottee);
            if (MinecraftForge.EVENT_BUS.post(pre)) return;
            s.setHighlightRemainTime(pre.getHighlightTime());
            player.swing(Hand.MAIN_HAND, true);
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(p -> p.setSpottingCd(pre.getSpottingCd()));
            final SoundEvent sound = SpottingManager.getSoundForSpotted(spottee, voiceType);
            if (sound != null)
                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
            traced.set(new StringTextComponent(spottee.getName().getString()).setStyle(Style.EMPTY).modifyStyle(y -> y.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY,
                    new HoverEvent.EntityHover(spottee.getType(), spottee.getUniqueID(), spottee.getName())))));
            hit.set(spottee.getPosition());
            MinecraftForge.EVENT_BUS.post(new SpottingEvent.Post(player, spottee));
            syncSpottingStatus(spottee);
        });
        if (traced.get() != null) {
            final MinecraftServer server = player.getServer();
            if (server == null) return;
            server.getPlayerList().func_232641_a_(getSpottingMsg(player.getDisplayName(), traced.get(), hit.get(),
                    voiceType.get$Spotted$MessageKey(), voiceType.get$There$MessageKey()), ChatType.CHAT, player.getUniqueID());
        }
    }

    public static TextComponent getSpottingMsg(ITextComponent player, ITextComponent traced, @Nullable BlockPos hit, String spottedKey, String thereKey) {
        return new TranslationTextComponent("chat.type.text", player, new TranslationTextComponent(spottedKey,
                new TranslationTextComponent(thereKey)
                        .setStyle(Style.EMPTY.setFormatting(TextFormatting.YELLOW))
                        .modifyStyle(s -> {
                            if (hit == null) return s;
                            return s.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("[" + hit.getCoordinatesAsString() + "]")));
                        }), traced));
    }

    public static void syncSpottingStatus(Entity e) {
        final int[] datas = new int[2];
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> datas[0] = s.tick());
        if ((e instanceof PlayerEntity))
            e.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> datas[1] = s.tick());
        NetworkHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> e.world.getDimensionKey()), new CapabilitySyncMsg(e.getEntityId(), datas[0], datas[1]));
    }
}
