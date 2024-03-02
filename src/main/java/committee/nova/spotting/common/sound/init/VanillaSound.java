package committee.nova.spotting.common.sound.init;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.integration.vocalized.voice.SpottingVoiceMessage;
import committee.nova.vocalized.common.registry.VocalizedRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.Objects;

public enum VanillaSound {
    ALLAY,
    AXOLOTL,
    BAT,
    BEE,
    BLAZE,
    CAMEL,
    CAT,
    CAVE_SPIDER,
    CHARGED_CREEPER(EntityType.CREEPER.getDescription()),
    CHICKEN,
    CHICKEN_JOCKEY(EntityType.CHICKEN.getDescription()),
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

    private final ResourceLocation id = new ResourceLocation("minecraft", this.name().toLowerCase(Locale.ROOT));
    private final ResourceLocation msgId = new ResourceLocation(Spotting.MODID, id.toString().replace(':', '.'));
    private final SpottingVoiceMessage msg;

    VanillaSound() {
        this(null);
    }

    VanillaSound(Component entityName) {
        if (entityName == null)
            entityName = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getValue(id)).getDescription();
        this.msg = new SpottingVoiceMessage(id, entityName);
        VocalizedRegistry.INSTANCE.registerVoiceMessage(msg);
    }

    public SpottingVoiceMessage getMsg() {
        return msg;
    }

    public ResourceLocation getMsgId() {
        return msgId;
    }

    public static void init() {
    }
}
