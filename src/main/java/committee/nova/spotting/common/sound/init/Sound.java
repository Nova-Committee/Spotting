package committee.nova.spotting.common.sound.init;

import committee.nova.spotting.Spotting;
import committee.nova.spotting.common.voice.BuiltInVoiceType;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;

public enum Sound {
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

    public String getSoundBaseName() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }

    @Nullable
    public SoundEvent get(IVoiceType type) {
        return type.getSoundEvent(this.getSoundBaseName());
    }

    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Spotting.MODID);
    private static final HashMap<String, RegistryObject<SoundEvent>> sounds = new HashMap<>();

    public static SoundEvent getSoundEvent(String id) {
        return sounds.get(id).get();
    }

    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final Sound[] indexes = Sound.values();
        for (final Sound s : indexes) {
            final String m = BuiltInVoiceType.MALE.getSuffixedSoundEventName(s.getSoundBaseName());
            sounds.put(m, SOUNDS.register(m, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Spotting.MODID, m))));
            final String f = BuiltInVoiceType.FEMALE.getSuffixedSoundEventName(s.getSoundBaseName());
            sounds.put(f, SOUNDS.register(f, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Spotting.MODID, f))));
        }
        SOUNDS.register(bus);
    }
}
