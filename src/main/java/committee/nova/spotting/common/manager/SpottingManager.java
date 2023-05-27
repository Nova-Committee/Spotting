package committee.nova.spotting.common.manager;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import committee.nova.spotting.common.sound.init.Sound;
import committee.nova.spotting.common.strategy.SoundStrategy;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nullable;

public class SpottingManager {
    private static boolean frozen = false;
    private static final Multimap<Class<? extends Entity>, SoundStrategy> strategies = LinkedListMultimap.create();

    public static void addStrategy(Class<? extends Entity> entityClass, SoundStrategy fun) {
        if (frozen) return;
        strategies.put(entityClass, fun);
    }

    public static void setFrozen() {
        frozen = true;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static SoundEvent getSoundForSpotted(Entity spotted, Sound.VoiceType male) {
        final Class<? extends Entity> entityClass = spotted.getClass();
        int priority = Integer.MIN_VALUE;
        SoundEvent sound = null;
        for (SoundStrategy y : strategies.get(entityClass)) {
            if (y.getPriority() <= priority) continue;
            final SoundEvent s1 = y.getFun().apply(spotted, male);
            if (s1 == null) continue;
            priority = y.getPriority();
            sound = s1;
        }
        Class<? extends Entity> tempClass = entityClass;
        while (sound == null) {
            final Class<?> superClz = tempClass.getSuperclass();
            if (!Entity.class.isAssignableFrom(superClz)) return null;
            tempClass = (Class<? extends Entity>) superClz;
            for (SoundStrategy y : strategies.get(tempClass)) {
                if (y.getPriority() <= priority) continue;
                final SoundEvent s1 = y.getFun().apply(spotted, male);
                if (s1 == null) continue;
                priority = y.getPriority();
                sound = s1;
            }
        }
        return sound;
    }

    private static boolean assignableButNotEq(Class<?> a, Class<?> b) {
        return a.isAssignableFrom(b) && !a.equals(b);
    }
}
