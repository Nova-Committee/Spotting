package committee.nova.spotting.common.sound.init;

import committee.nova.spotting.Spotting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;

public enum Sound {
    ALLAY,
    AXOLOTL,
    BAT,
    BEE,
    BLAZE,
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

    public SoundEvent maleSound() {
        return sounds.get(maleName()).get();
    }

    public SoundEvent femaleSound() {
        return sounds.get(femaleName()).get();
    }

    @Nullable
    public SoundEvent get(VoiceType gender) {
        switch (gender) {
            case MALE:
                return maleSound();
            case FEMALE:
                return femaleSound();
            default:
                return null;
        }
    }

    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Spotting.MODID);
    private static final HashMap<String, RegistryObject<SoundEvent>> sounds = new HashMap<>();

    public String maleName() {
        return this.name().toLowerCase(Locale.ENGLISH) + "_m";
    }

    public String femaleName() {
        return this.name().toLowerCase(Locale.ENGLISH) + "_f";
    }

    public enum VoiceType {
        MALE,
        FEMALE,
        NONE;
    }

    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final Sound[] indexes = Sound.values();
        for (final Sound s : indexes) {
            sounds.put(s.maleName(), SOUNDS.register(s.maleName(), () -> new SoundEvent(new ResourceLocation(Spotting.MODID, s.maleName()))));
            sounds.put(s.femaleName(), SOUNDS.register(s.femaleName(), () -> new SoundEvent(new ResourceLocation(Spotting.MODID, s.femaleName()))));
        }
        SOUNDS.register(bus);
    }
}
