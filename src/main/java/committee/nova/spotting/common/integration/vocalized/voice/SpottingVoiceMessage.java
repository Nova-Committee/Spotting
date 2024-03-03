package committee.nova.spotting.common.integration.vocalized.voice;

import committee.nova.spotting.Spotting;
import committee.nova.vocalized.api.IVoiceMessage;
import committee.nova.vocalized.api.IVoiceMessageType;
import committee.nova.vocalized.api.IVoiceType;
import committee.nova.vocalized.common.ref.BuiltInVoiceMessageType;
import net.minecraft.resources.ResourceLocation;

public class SpottingVoiceMessage implements IVoiceMessage {
    private final ResourceLocation id;

    public SpottingVoiceMessage(ResourceLocation entityId) {
        this.id = new ResourceLocation(Spotting.MODID, entityId.toString().replace(':', '.'));
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String getTranslationKey(IVoiceType type) {
        return String.format(
                "v_msg.vocalized.%s.%s.%s.%s",
                type.getIdentifier().getNamespace(),
                type.getIdentifier().getPath(),
                Spotting.MODID,
                "spotted"
        );
    }

    @Override
    public String getDefaultTranslationKey() {
        return String.format("v_msg.vocalized.default.%s.%s", Spotting.MODID, "spotted");
    }

    @Override
    public IVoiceMessageType getType() {
        return BuiltInVoiceMessageType.COMMON.get();
    }
}
