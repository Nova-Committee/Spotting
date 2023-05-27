package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.sound.init.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;

import java.util.function.BiFunction;

public class SoundStrategy {
    private final int priority;
    private final BiFunction<Entity, Boolean, SoundEvent> fun;

    public SoundStrategy(int priority, BiFunction<Entity, Boolean, SoundEvent> fun) {
        this.priority = priority;
        this.fun = fun;
    }

    public SoundStrategy(Sound s) {
        this(0, (e, m) -> s.get(m));
    }

    public BiFunction<Entity, Boolean, SoundEvent> getFun() {
        return fun;
    }

    public int getPriority() {
        return priority;
    }
}
