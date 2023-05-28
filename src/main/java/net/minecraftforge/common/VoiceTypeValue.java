package net.minecraftforge.common;

import com.electronwill.nightconfig.core.Config;
import committee.nova.spotting.common.voice.BuiltInVoiceType;
import committee.nova.spotting.common.voice.api.IVoiceType;

import java.util.List;
import java.util.function.Supplier;

public class VoiceTypeValue extends ForgeConfigSpec.ConfigValue<IVoiceType> {
    VoiceTypeValue(ForgeConfigSpec.Builder parent, List<String> path) {
        super(parent, path, () -> BuiltInVoiceType.MALE);
    }

    @Override
    protected IVoiceType getRaw(Config config, List<String> path, Supplier<IVoiceType> defaultSupplier) {
        final IVoiceType raw = config.get(path);
        return raw == null ? defaultSupplier.get() : raw;
    }
}
