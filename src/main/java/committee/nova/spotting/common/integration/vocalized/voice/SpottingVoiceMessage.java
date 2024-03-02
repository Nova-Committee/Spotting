package committee.nova.spotting.common.integration.vocalized.voice;

import committee.nova.spotting.Spotting;
import committee.nova.vocalized.api.IVoiceMessage;
import committee.nova.vocalized.api.IVoiceMessageType;
import committee.nova.vocalized.api.IVoiceType;
import committee.nova.vocalized.common.ref.BuiltInVoiceMessageType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class SpottingVoiceMessage implements IVoiceMessage {
    private final ResourceLocation id;
    private final MutableComponent entityName;

    public SpottingVoiceMessage(ResourceLocation entityId, Component entityName) {
        this.id = new ResourceLocation(Spotting.MODID, entityId.toString().replace(':', '.'));
        this.entityName = entityName.copy().withStyle(ChatFormatting.YELLOW);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public Optional<Component> getText(IVoiceType type) {
        String key = String.format("v_msg.%s.%s.%s", type.getIdentifier().getNamespace(), type.getIdentifier().getPath(), Spotting.MODID);
        if (I18n.exists(key)) {
            return Optional.of(Component.translatable(
                    key,
                    entityName
            ));
        } else {
            key = String.format("v_msg.default.%s", this.getId().getNamespace());
            return I18n.exists(key) ? Optional.of(Component.translatable(
                    key,
                    entityName
            )) : Optional.empty();
        }
    }

    @Override
    public IVoiceMessageType getType() {
        return BuiltInVoiceMessageType.COMMON.get();
    }
}
