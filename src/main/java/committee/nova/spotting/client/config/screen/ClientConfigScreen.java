package committee.nova.spotting.client.config.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import committee.nova.spotting.client.config.ClientConfig;
import committee.nova.spotting.common.sound.init.Sound;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.IteratableOption;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;

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
    private OptionsRowList optionsRowList;

    public ClientConfigScreen(Screen parent) {
        super(new TranslationTextComponent("screen.spotting.cfg.title"));
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, font, title, width / 2, titleOffset, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void init() {
        if (minecraft == null) return;
        optionsRowList = new OptionsRowList(
                minecraft, width, height,
                OPTIONS_LIST_TOP_HEIGHT,
                height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );
        children.add(this.optionsRowList);
        addButton(new Button(
                (width - BUTTON_WIDTH) / 2,
                height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                new TranslationTextComponent("gui.done"),
                button -> {
                    ClientConfig.CONFIG.save();
                    getMinecraft().displayGuiScreen(parent);
                }
        ));
        optionsRowList.addOption(new IteratableOption(
                "screen.spotting.cfg.options.voice_type",
                (settings, index) -> ClientConfig.VOICE_TYPE.set(Sound.VoiceType.values()[
                        (ClientConfig.VOICE_TYPE.get().ordinal() + index) % Sound.VoiceType.values().length]),
                (settings, option) -> new TranslationTextComponent("screen.spotting.cfg.selection.voice_type",
                        new TranslationTextComponent("screen.spotting.cfg.option." +
                                ClientConfig.VOICE_TYPE.get().name().toLowerCase(Locale.ENGLISH)))
        ));
    }

    @Override
    public void onClose() {
        ClientConfig.CONFIG.save();
    }

    @Override
    public void closeScreen() {
        getMinecraft().displayGuiScreen(parent);
    }
}
