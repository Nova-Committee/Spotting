package committee.nova.spotting.common.util;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.manager.SpottingManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class SpottingUtil {
    public static void trySpot(PlayerEntity player, @Nullable Entity e) {
        if (e == null) return;
        final AtomicReference<ITextComponent> traced = new AtomicReference<>(null);
        final AtomicReference<BlockPos> hit = new AtomicReference<>(null);
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> {
            s.setHighlightRemainTime(300);
            player.swing(Hand.MAIN_HAND, true);
            player.getCapability(SpottingCapability.SPOTTER).ifPresent(p -> p.setSpottingCd(30));
            final SoundEvent sound = SpottingManager.getSoundForSpotted(e);
            if (sound != null)
                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
            traced.set(new StringTextComponent(e.getName().getString()).setStyle(Style.EMPTY).modifyStyle(y -> y.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY,
                    new HoverEvent.EntityHover(e.getType(), e.getUniqueID(), e.getName())))));
            hit.set(e.getPosition());
        });
        if (traced.get() != null) {
            final MinecraftServer server = player.getServer();
            if (server == null) return;
            server.getPlayerList().func_232641_a_(getSpottingMsg(player.getDisplayName(), traced.get(), hit.get()), ChatType.CHAT, player.getUniqueID());
        }
    }

    public static TextComponent getSpottingMsg(ITextComponent player, ITextComponent traced, @Nullable BlockPos hit) {
        return new TranslationTextComponent("chat.type.text", player, new TranslationTextComponent("msg.spotting.spotted", new TranslationTextComponent("msg.spotting.there")
                .setStyle(Style.EMPTY.setFormatting(TextFormatting.YELLOW))
                .modifyStyle(s -> {
                    if (hit == null) return s;
                    return s.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("[" + hit.getCoordinatesAsString() + "]")));
                }), traced));
    }
}
