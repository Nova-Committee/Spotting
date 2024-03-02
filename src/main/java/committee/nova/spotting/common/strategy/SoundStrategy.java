package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.sound.init.VanillaSound;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

public class SoundStrategy {
    private final int priority;
    private final Function<Entity, VanillaSound> fun;

    public SoundStrategy(int priority, Function<Entity, VanillaSound> fun) {
        this.priority = priority;
        this.fun = fun;
    }

    public SoundStrategy(VanillaSound s) {
        this(0, e -> s);
    }

    public Function<Entity, VanillaSound> getFun() {
        return fun;
    }

    public int getPriority() {
        return priority;
    }
}
