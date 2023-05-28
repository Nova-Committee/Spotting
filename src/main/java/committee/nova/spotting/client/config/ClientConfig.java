package committee.nova.spotting.client.config;

import committee.nova.spotting.common.voice.BuiltInVoiceType;
import committee.nova.spotting.common.voice.VoiceManager;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.stream.Collectors;

public class ClientConfig {
    public static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.ConfigValue<String> VOICE_TYPE;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Spotting Voice Settings").push("voice");
        VOICE_TYPE = builder
                .comment("The type of the voice.")
                .comment("Use spotting:none to turn off the voice.")
                .defineInList("voice_type", BuiltInVoiceType.MALE.getVoiceId().toString(),
                        VoiceManager.getVoiceTypes().stream()
                                .map(v -> v.getVoiceId().toString())
                                .collect(Collectors.toList()));
        builder.pop();
        CONFIG = builder.build();
    }

    public static IVoiceType getVoiceType() {
        return VoiceManager.getVoiceType(VOICE_TYPE.get());
    }
}
