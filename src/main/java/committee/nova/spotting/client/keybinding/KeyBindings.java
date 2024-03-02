package committee.nova.spotting.client.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyMapping spotting = new KeyMapping("key.spotting.spotting", KeyConflictContext.IN_GAME, KeyModifier.NONE,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, "category.spotting");
}
