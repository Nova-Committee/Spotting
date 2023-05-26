package committee.nova.spotting.client.keybinding;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyBinding spotting = new KeyBinding("key.spotting.spotting", KeyConflictContext.IN_GAME, KeyModifier.NONE,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_Y, "category.spotting");
}
