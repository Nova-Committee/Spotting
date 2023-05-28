package committee.nova.spotting.common.voice.api;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public interface IVoiceType {
    ResourceLocation getVoiceId();

    SoundEvent getSoundEvent(String entity);

    String getOptionName();

    String get$Spotted$MessageKey();

    String get$There$MessageKey();
}
