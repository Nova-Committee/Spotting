package committee.nova.spotting.common.sound.init;

public enum SoundIndex {
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
    COW,
    CREEPER,
    DOLPHIN,
    DROWNED,
    ELDER_GUARDIAN,
    ENDER_DRAGON,
    ENDERMAN,
    ENDERMITE,
    EVOKER,
    FISH,
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
    SHEEP,
    SHULKER,
    SILVERFISH,
    SKELETON,
    SLIME,
    SNOW_GOLEM,
    SPIDER,
    SQUID,
    STRAY,
    STRIDER,
    TADPOLE,
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
    ZOMBIE_VILLAGER;

    public Sound male() {
        return Sound.valueOf(this.name() + "_M");
    }

    public Sound female() {
        return Sound.valueOf(this.name() + "_F");
    }

    public Sound get(boolean male) {
        return male ? male() : female();
    }
}
