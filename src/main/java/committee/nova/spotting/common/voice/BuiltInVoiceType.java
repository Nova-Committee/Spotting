package committee.nova.spotting.common.voice;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.sound.init.Sound;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.Locale;

public enum BuiltInVoiceType implements IVoiceType {
    MALE(".m", "_m"),
    FEMALE(".f", "_f"),
    NONE("", "");

    private final String localeSuffix;
    private final String soundEventSuffix;

    BuiltInVoiceType(String localeSuffix, String soundEventSuffix) {
        this.localeSuffix = localeSuffix;
        this.soundEventSuffix = soundEventSuffix;
    }

    @Override
    public ResourceLocation getVoiceId() {
        return new ResourceLocation(Spotting.MODID, this.name().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public SoundEvent getSoundEvent(String entity) {
        switch (this) {
            case MALE:
                return Sound.getSoundEvent(entity + "_m");
            case FEMALE:
                return Sound.getSoundEvent(entity + "_f");
            default:
                return null;
        }
    }

    @Override
    public String getOptionName() {
        return "screen.spotting.cfg.option." + name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String get$Spotted$MessageKey() {
        return "msg.spotting.spotted" + localeSuffix;
    }

    @Override
    public String get$There$MessageKey() {
        return "msg.spotting.there" + localeSuffix;
    }

    public String getSuffixedSoundEventName(String entity) {
        return entity + soundEventSuffix;
    }
}
