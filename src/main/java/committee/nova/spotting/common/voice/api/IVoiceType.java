package committee.nova.spotting.common.voice.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface IVoiceType {
    ResourceLocation getVoiceId();

    SoundEvent getSoundEvent(String entity);

    String getOptionName();

    String get$Spotted$MessageKey();

    String get$There$MessageKey();
}
