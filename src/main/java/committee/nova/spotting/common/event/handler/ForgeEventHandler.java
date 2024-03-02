package committee.nova.spotting.common.event.handler;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.config.CommonConfig;
import committee.nova.spotting.common.util.SpottingUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(SpottingCapability.Spotter.class);
        event.register(SpottingCapability.Spottable.class);
    }

    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<Entity> event) {
        final Entity e = event.getObject();
        if (!(e instanceof LivingEntity)) return;
        event.addCapability(new ResourceLocation(Spotting.MODID, "spottable"), new SpottingCapability.SpottableProvider());
        if (!(e instanceof Player)) return;
        event.addCapability(new ResourceLocation(Spotting.MODID, "spotter"), new SpottingCapability.SpotterProvider());
    }

    @SubscribeEvent
    public static void onTick(LivingEvent.LivingTickEvent event) {
        final LivingEntity living = event.getEntity();
        if (living.level().isClientSide || living.level().getGameTime() % 20 != living.getId() % 20) return;
        SpottingUtil.syncSpottingStatus(living);
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        final Entity e = event.getEntity();
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(SpottingCapability.ISpottable::goDark);
        e.getCapability(SpottingCapability.SPOTTER).ifPresent(SpottingCapability.ISpotter::clearCd);
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        CommonConfig.refreshIfNeeded(event.getServer());
    }
}
