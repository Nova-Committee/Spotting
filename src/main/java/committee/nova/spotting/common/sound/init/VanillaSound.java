package committee.nova.spotting.common.sound.init;

import committee.nova.spotting.common.integration.vocalized.voice.SpottingVoiceMessage;
import committee.nova.vocalized.common.registry.VocalizedRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public enum VanillaSound {
    ALLAY,
    AXOLOTL,
    BAT,
    BEE,
    BLAZE,
    CAMEL,
    CAT,
    CAVE_SPIDER,
    CHARGED_CREEPER,
    CHICKEN,
    CHICKEN_JOCKEY,
    COD,
    COW,
    CREEPER,
    DOLPHIN,
    DONKEY,
    DROWNED,
    ELDER_GUARDIAN,
    ENDER_DRAGON,
    ENDERMAN,
    ENDERMITE,
    EVOKER,
    FISH,
    FOX,
    FROG,
    GHAST,
    GIANT,
    GOAT,
    GUARDIAN,
    HOGLIN,
    HORSE,
    HUSK,
    ILLUSIONER,
    IRON_GOLEM,
    LLAMA,
    MAGMA_CUBE,
    MOOSHROOM,
    MULE,
    OCELOT,
    PANDA,
    PARROT,
    PHANTOM,
    PIG,
    PIGLIN,
    PIGLIN_BRUTE,
    PILLAGER,
    PLAYER,
    POLAR_BEAR,
    PUFFERFISH,
    RABBIT,
    RAVAGER,
    SALMON,
    SHEEP,
    SHULKER,
    SILVERFISH,
    SKELETON,
    SKELETON_HORSE,
    SLIME,
    SNOW_GOLEM,
    SPIDER,
    SQUID,
    STRAY,
    STRIDER,
    TADPOLE,
    TROPICAL_FISH,
    TURTLE,
    VEX,
    VILLAGER,
    VINDICATOR,
    WANDERING_TRADER,
    WARDEN,
    WITCH,
    WITHER,
    WITHER_SKELETON,
    WOLF,
    ZOGLIN,
    ZOMBIE,
    ZOMBIE_HORSE,
    ZOMBIE_VILLAGER,
    ZOMBIFIED_PIGLIN;

    private final SpottingVoiceMessage msg;

    VanillaSound() {
        this.msg = new SpottingVoiceMessage(new ResourceLocation(
                "minecraft",
                this.name().toLowerCase(Locale.ROOT)
        ));
        VocalizedRegistry.INSTANCE.registerVoiceMessage(msg);
    }

    public SpottingVoiceMessage getMsg() {
        return msg;
    }

    public static void init() {
    }
}
