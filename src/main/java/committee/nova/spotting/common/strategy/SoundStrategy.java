package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.sound.init.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;

import java.util.function.Function;

public class SoundStrategy {
    private final int priority;
    private final Function<Entity, SoundEvent> fun;

    public SoundStrategy(int priority, Function<Entity, SoundEvent> fun) {
        this.priority = priority;
        this.fun = fun;
    }

    public SoundStrategy(SoundEvent s) {
        this(0, e -> s);
    }

    public SoundStrategy(Sound s) {
        this(0, e -> s.getSoundEvent());
    }

    public Function<Entity, SoundEvent> getFun() {
        return fun;
    }

    public int getPriority() {
        return priority;
    }
}
