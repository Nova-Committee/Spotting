package committee.nova.spotting;

import committee.nova.spotting.common.config.CommonConfig;
import committee.nova.spotting.common.network.init.NetworkHandler;
import committee.nova.spotting.common.sound.init.VanillaSound;
import committee.nova.spotting.common.strategy.SoundStrategies;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Spotting.MODID)
public class Spotting {
    public static final String MODID = "spotting";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final int internalVersion = 1;

    public Spotting() {
        NetworkHandler.registerMessage();
        MinecraftForge.EVENT_BUS.register(this);
        VanillaSound.init();
        SoundStrategies.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);
    }
}
