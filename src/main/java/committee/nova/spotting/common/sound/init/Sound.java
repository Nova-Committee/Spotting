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
    ZOMBIE_VILLAGER_M,
    ALLAY_F,
    AXOLOTL_F,
    BAT_F,
    BEE_F,
    BLAZE_F,
    CAT_F,
    CAVE_SPIDER_F,
    CHARGED_CREEPER_F,
    CHICKEN_F,
    CHICKEN_JOCKEY_F,
    COW_F,
    CREEPER_F,
    DOLPHIN_F,
    DROWNED_F,
    ELDER_GUARDIAN_F,
    ENDER_DRAGON_F,
    ENDERMAN_F,
    ENDERMITE_F,
    EVOKER_F,
    FISH_F,
    FROG_F,
    GHAST_F,
    GIANT_F,
    GOAT_F,
    GUARDIAN_F,
    HOGLIN_F,
    HORSE_F,
    HUSK_F,
    ILLUSIONER_F,
    IRON_GOLEM_F,
    LLAMA_F,
    MAGMA_CUBE_F,
    MOOSHROOM_F,
    OCELOT_F,
    PANDA_F,
    PARROT_F,
    PHANTOM_F,
    PIG_F,
    PIGLIN_F,
    PIGLIN_BRUTE_F,
    PILLAGER_F,
    PLAYER_F,
    POLAR_BEAR_F,
    PUFFERFISH_F,
    RABBIT_F,
    RAVAGER_F,
    SHEEP_F,
    SHULKER_F,
    SILVERFISH_F,
    SKELETON_F,
    SLIME_F,
    SNOW_GOLEM_F,
    SPIDER_F,
    SQUID_F,
    STRAY_F,
    STRIDER_F,
    TADPOLE_F,
    TURTLE_F,
    VEX_F,
    VILLAGER_F,
    VINDICATOR_F,
    WANDERING_TRADER_F,
    WARDEN_F,
    WITCH_F,
    WITHER_F,
    WITHER_SKELETON_F,
    WOLF_F,
    ZOGLIN_F,
    ZOMBIE_F,
    ZOMBIE_VILLAGER_F;
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
