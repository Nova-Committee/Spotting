package committee.nova.spotting.common.sound.init;

import committee.nova.spotting.Spotting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Locale;

public enum Sound {
    ALLAY_M,
    AXOLOTL_M,
    BAT_M,
    BEE_M,
    BLAZE_M,
    CAT_M,
    CAVE_SPIDER_M,
    CHARGED_CREEPER_M,
    CHICKEN_M,
    CHICKEN_JOCKEY_M,
    COW_M,
    CREEPER_M,
    DOLPHIN_M,
    DROWNED_M,
    ELDER_GUARDIAN_M,
    ENDER_DRAGON_M,
    ENDERMAN_M,
    ENDERMITE_M,
    EVOKER_M,
    FISH_M,
    FROG_M,
    GHAST_M,
    GIANT_M,
    GOAT_M,
    GUARDIAN_M,
    HOGLIN_M,
    HORSE_M,
    HUSK_M,
    ILLUSIONER_M,
    IRON_GOLEM_M,
    LLAMA_M,
    MAGMA_CUBE_M,
    MOOSHROOM_M,
    OCELOT_M,
    PANDA_M,
    PARROT_M,
    PHANTOM_M,
    PIG_M,
    PIGLIN_M,
    PIGLIN_BRUTE_M,
    PILLAGER_M,
    PLAYER_M,
    POLAR_BEAR_M,
    PUFFERFISH_M,
    RABBIT_M,
    RAVAGER_M,
    SHEEP_M,
    SHULKER_M,
    SILVERFISH_M,
    SKELETON_M,
    SLIME_M,
    SNOW_GOLEM_M,
    SPIDER_M,
    SQUID_M,
    STRAY_M,
    STRIDER_M,
    TADPOLE_M,
    TURTLE_M,
    VEX_M,
    VILLAGER_M,
    VINDICATOR_M,
    WANDERING_TRADER_M,
    WARDEN_M,
    WITCH_M,
    WITHER_M,
    WITHER_SKELETON_M,
    WOLF_M,
    ZOGLIN_M,
    ZOMBIE_M,
    ZOMBIE_VILLAGER_M;
    private final String id;

    Sound() {
        this.id = this.name().toLowerCase(Locale.ENGLISH);
    }

    public String getId() {
        return id;
    }

    public SoundEvent getSoundEvent() {
        final Collection<RegistryObject<SoundEvent>> c = SOUNDS.getEntries();
        for (RegistryObject<SoundEvent> r : c) {
            if (r.getId().getPath().equals(getId())) return r.get();
        }
        return null;
    }

    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Spotting.MODID);

    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final Sound[] sounds = Sound.values();
        for (final Sound s : sounds)
            SOUNDS.register(s.getId(), () -> new SoundEvent(new ResourceLocation(Spotting.MODID, s.getId())));
        SOUNDS.register(bus);
    }
}
