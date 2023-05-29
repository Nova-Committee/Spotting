package committee.nova.spotting.common.voice;

import com.google.common.collect.ImmutableList;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class VoiceManager {
    private static final LinkedList<IVoiceType> voiceTypes = new LinkedList<>();

    public static boolean addVoiceType(IVoiceType voiceType) {
        return voiceTypes.add(voiceType);
    }

    public static List<IVoiceType> getVoiceTypes() {
        return ImmutableList.copyOf(voiceTypes);
    }

    public static List<String> getVoiceTypeSelections() {
        return getVoiceTypes().stream().map(v -> v.getVoiceId().toString()).toList();
    }

    public static IVoiceType getVoiceType(String id) {
        try {
            return getVoiceType(new ResourceLocation(id));
        } catch (ResourceLocationException ignored) {
            return BuiltInVoiceType.MALE;
        }
    }

    public static IVoiceType getVoiceType(ResourceLocation id) {
        for (final IVoiceType t : voiceTypes) if (t.getVoiceId().equals(id)) return t;
        return BuiltInVoiceType.MALE;
    }

    public static int getIndex(IVoiceType t) {
        final int l = voiceTypes.size();
        for (int i = 0; i < l; i++) if (voiceTypes.get(i).equals(t)) return i;
        return 0;
    }

    static {
        voiceTypes.addAll(Arrays.asList(BuiltInVoiceType.values()));
    }
}
