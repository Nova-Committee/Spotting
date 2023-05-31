package committee.nova.spotting.client.config.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import committee.nova.spotting.client.config.ClientConfig;
import committee.nova.spotting.common.voice.VoiceManager;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ClientConfigScreen extends Screen {
    private final Screen parent;
    private final int titleOffset = 8;
    private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;
    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_TOP_OFFSET = 26;
    private OptionsList optionsRowList;
    public Button quit;
    private final OptionInstance<IVoiceType> voiceType = new OptionInstance<>("screen.spotting.cfg.selection.voice_type", OptionInstance.noTooltip(),
            OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(VoiceManager.getVoiceTypes(),
            Codec.STRING.xmap(VoiceManager::getVoiceType, v -> v.getVoiceId().toString())), VoiceManager.getVoiceType(ClientConfig.VOICE_TYPE.get()), r -> {
        ClientConfig.VOICE_TYPE.set(r.getVoiceId().toString());
        ClientConfig.CONFIG.save();
    });

    public ClientConfigScreen(Screen parent) {
        super(Component.translatable("screen.spotting.cfg.title"));
        this.parent = parent;
    }

    @Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(PoseStack);
        optionsRowList.render(PoseStack, mouseX, mouseY, partialTicks);

        drawCenteredString(PoseStack, font, title, width / 2, titleOffset, 0xFFFFFF);
        super.render(PoseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void init() {
        if (minecraft == null) return;
        optionsRowList = new OptionsList(
                minecraft, width, height,
                OPTIONS_LIST_TOP_HEIGHT,
                height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );
        optionsRowList.addBig(voiceType);
        quit = addRenderableWidget(Button.builder(Component.translatable("gui.done"), b -> {
            ClientConfig.CONFIG.save();
            getMinecraft().setScreen(parent);
        }).bounds((width - BUTTON_WIDTH) / 2,
                height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT).build());
        addWidget(optionsRowList);
    }

    @Override
    public void removed() {
        ClientConfig.CONFIG.save();
    }

    @Override
    public void onClose() {
        getMinecraft().setScreen(parent instanceof PauseScreen ? null : parent);
    }
}
