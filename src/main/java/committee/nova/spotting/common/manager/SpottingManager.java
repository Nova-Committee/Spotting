package committee.nova.spotting.common.manager;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import committee.nova.spotting.common.strategy.SoundStrategy;
import committee.nova.vocalized.api.IVoiceMessage;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

public class SpottingManager {
    private static final Multimap<Class<? extends Entity>, SoundStrategy> strategies = LinkedListMultimap.create();

    public static void addStrategy(Class<? extends Entity> entityClass, SoundStrategy fun) {
        strategies.put(entityClass, fun);
    }

    @SuppressWarnings("unchecked")
    public static Optional<IVoiceMessage> getMessageIdForSpotted(Entity spotted) {
        final Class<? extends Entity> entityClass = spotted.getClass();
        int priority = Integer.MIN_VALUE;
        Optional<IVoiceMessage> id = Optional.empty();
        for (SoundStrategy y : strategies.get(entityClass)) {
            if (y.getPriority() <= priority) continue;
            final IVoiceMessage s1 = y.getFun().apply(spotted).getMsg();
            if (s1 == null) continue;
            priority = y.getPriority();
            id = Optional.of(s1);
        }
        Class<? extends Entity> tempClass = entityClass;
        while (id.isEmpty()) {
            final Class<?> superClz = tempClass.getSuperclass();
            if (!Entity.class.isAssignableFrom(superClz)) return Optional.empty();
            tempClass = (Class<? extends Entity>) superClz;
            for (SoundStrategy y : strategies.get(tempClass)) {
                if (y.getPriority() <= priority) continue;
                final IVoiceMessage s1 = y.getFun().apply(spotted).getMsg();
                if (s1 == null) continue;
                priority = y.getPriority();
                id = Optional.of(s1);
            }
        }
        return id;
    }
}
