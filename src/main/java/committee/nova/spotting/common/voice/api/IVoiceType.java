package committee.nova.spotting.common.voice.api;

import committee.nova.spotting.common.voice.VoiceManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.OptionEnum;

import javax.annotation.Nonnull;

public interface IVoiceType extends OptionEnum {
    ResourceLocation getVoiceId();

    SoundEvent getSoundEvent(String entity);

    String getOptionName();

    String get$Spotted$MessageKey();

    String get$There$MessageKey();

    @Override
    default int getId() {
        return VoiceManager.getIndex(this);
    }

    @Override
    @Nonnull
    default String getKey() {
        return getVoiceId().toString();
    }

    @Override
    @Nonnull
    default Component getCaption() {
        return Component.translatable(getOptionName());
    }
}
