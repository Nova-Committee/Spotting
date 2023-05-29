package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.sound.init.Sound;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;

import java.util.function.BiFunction;

public class SoundStrategy {
    private final int priority;
    private final BiFunction<Entity, IVoiceType, SoundEvent> fun;

    public SoundStrategy(int priority, BiFunction<Entity, IVoiceType, SoundEvent> fun) {
        this.priority = priority;
        this.fun = fun;
    }

    public SoundStrategy(Sound s) {
        this(0, (e, m) -> s.get(m));
    }

    public BiFunction<Entity, IVoiceType, SoundEvent> getFun() {
        return fun;
    }

    public int getPriority() {
        return priority;
    }
}
