package committee.nova.spotting.client.config;

import committee.nova.spotting.common.sound.init.Sound;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.EnumValue<Sound.VoiceType> VOICE_TYPE;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Spotting Voice Settings").push("voice");
        VOICE_TYPE = builder
                .comment("The type of the voice.")
                .comment("The 2 genders in it are ONLY about YOUR voice to be played when spotted")
                .comment("Use NONE to turn off the voice.")
                .defineEnum("voice_gender", Sound.VoiceType.MALE);
        builder.pop();
        CONFIG = builder.build();
    }
}
