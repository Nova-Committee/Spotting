package committee.nova.spotting;

import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.sound.init.Sound;
import committee.nova.spotting.common.strategy.SoundStrategies;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Spotting.MODID)
public class Spotting {
    public static final String MODID = "spotting";
    private static final Logger LOGGER = LogManager.getLogger();

    public Spotting() {
        NetworkHandler.registerMessage();
        Sound.init();
        MinecraftForge.EVENT_BUS.register(this);
        SoundStrategies.init();
    }
}
