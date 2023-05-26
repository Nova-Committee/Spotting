package committee.nova.spotting.common.event.handler;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.capabilities.SpottingCapability;
import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.network.msg.CapabilitySyncMsg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.network.PacketDistributor;

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
        final int[] datas = new int[2];
        living.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> datas[0] = s.tick());
        if ((living instanceof PlayerEntity))
            living.getCapability(SpottingCapability.SPOTTER).ifPresent(s -> datas[1] = s.tick());
        NetworkHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> living.world.getDimensionKey()), new CapabilitySyncMsg(living.getEntityId(), datas[0], datas[1]));
    }

    @SubscribeEvent
    public static void onServerAboutToStart(FMLServerAboutToStartEvent e) {
        SpottingManager.setFrozen();
    }
}
