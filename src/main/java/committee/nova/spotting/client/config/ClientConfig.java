package committee.nova.spotting.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.EnumValue<VoiceGender> VOICE_GENDER;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Spotting Voice Settings").push("voice");
        VOICE_GENDER = builder.comment("The gender of the voice, ONLY about the voice to be played when spotted.").defineEnum("voice_gender", VoiceGender.MALE);
        builder.pop();
        CONFIG = builder.build();
    }

    public enum VoiceGender {
        MALE,
        FEMALE;

        public boolean isMale() {
            return this.equals(MALE);
        }
    }
}
