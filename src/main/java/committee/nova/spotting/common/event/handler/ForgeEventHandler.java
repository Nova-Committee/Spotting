package committee.nova.spotting.common.event.handler;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.config.CommonConfig;
import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.util.SpottingUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<Entity> event) {
        final Entity e = event.getObject();
        if (!(e instanceof LivingEntity)) return;
        event.addCapability(new ResourceLocation(Spotting.MODID, "spottable"), new SpottingCapability.SpottableProvider());
        if (!(e instanceof PlayerEntity)) return;
        event.addCapability(new ResourceLocation(Spotting.MODID, "spotter"), new SpottingCapability.SpotterProvider());
    }

    @SubscribeEvent
    public static void onTick(LivingEvent.LivingUpdateEvent event) {
        final LivingEntity living = event.getEntityLiving();
        if (living.world.isRemote || living.world.getGameTime() % 20 != living.getEntityId() % 20) return;
        SpottingUtil.syncSpottingStatus(living);
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        final Entity e = event.getEntity();
        e.getCapability(SpottingCapability.SPOTTABLE).ifPresent(SpottingCapability.ISpottable::goDark);
        e.getCapability(SpottingCapability.SPOTTER).ifPresent(SpottingCapability.ISpotter::clearCd);
    }

    @SubscribeEvent
    public static void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        SpottingManager.setFrozen();
    }

    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {
        CommonConfig.refreshIfNeeded(event.getServer());
    }
}
