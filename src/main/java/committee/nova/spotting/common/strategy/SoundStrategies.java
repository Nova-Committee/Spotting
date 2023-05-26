package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.sound.init.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SoundStrategies {
    public static void init() {
        qr(BatEntity.class, Sound.BAT_M);
        qr(BeeEntity.class, Sound.BEE_M);
        qr(BlazeEntity.class, Sound.BLAZE_M);
        qr(CatEntity.class, Sound.CAT_M);
        qr(CaveSpiderEntity.class, Sound.CAVE_SPIDER_M);
        SpottingManager.addStrategy(CreeperEntity.class, new SoundStrategy(0, s -> ((CreeperEntity) s).isCharged() ?
                Sound.CHARGED_CREEPER_M.getSoundEvent() : Sound.CREEPER_M.getSoundEvent()));
        SpottingManager.addStrategy(ChickenEntity.class, new SoundStrategy(0, c -> ((ChickenEntity) c).chickenJockey ?
                Sound.CHICKEN_JOCKEY_M.getSoundEvent() : Sound.CHICKEN_M.getSoundEvent()));
        qr(DolphinEntity.class, Sound.DOLPHIN_M);
        qr(DrownedEntity.class, Sound.DROWNED_M);
        qr(ElderGuardianEntity.class, Sound.ELDER_GUARDIAN_M);
        qr(EnderDragonEntity.class, Sound.ENDER_DRAGON_M);
        qr(EndermanEntity.class, Sound.ENDERMAN_M);
        qr(EndermiteEntity.class, Sound.ENDERMITE_M);
        qr(EvokerEntity.class, Sound.EVOKER_M);
        qr(AbstractFishEntity.class, Sound.FISH_M);
        qr(GhastEntity.class, Sound.GHAST_M);
        qr(GiantEntity.class, Sound.GIANT_M);
        qr(GuardianEntity.class, Sound.GUARDIAN_M);
        qr(HoglinEntity.class, Sound.HOGLIN_M);
        qr(AbstractHorseEntity.class, Sound.HORSE_M);
        qr(HuskEntity.class, Sound.HUSK_M);
        qr(IllusionerEntity.class, Sound.ILLUSIONER_M);
        qr(IronGolemEntity.class, Sound.IRON_GOLEM_M);
        qr(LlamaEntity.class, Sound.LLAMA_M);
        qr(MagmaCubeEntity.class, Sound.MAGMA_CUBE_M);
        qr(MooshroomEntity.class, Sound.MOOSHROOM_M);
        qr(OcelotEntity.class, Sound.OCELOT_M);
        qr(PandaEntity.class, Sound.PANDA_M);
        qr(ParrotEntity.class, Sound.PARROT_M);
        qr(PhantomEntity.class, Sound.PHANTOM_M);
        qr(PigEntity.class, Sound.PIG_M);
        qr(PiglinEntity.class, Sound.PIGLIN_M);
        qr(PiglinBruteEntity.class, Sound.PIGLIN_BRUTE_M);
        qr(PillagerEntity.class, Sound.PILLAGER_M);
        qr(PlayerEntity.class, Sound.PLAYER_M);
        qr(PolarBearEntity.class, Sound.POLAR_BEAR_M);
        qr(PufferfishEntity.class, Sound.PUFFERFISH_M);
        qr(RabbitEntity.class, Sound.RABBIT_M);
        qr(RavagerEntity.class, Sound.RAVAGER_M);
        qr(SheepEntity.class, Sound.SHEEP_M);
        qr(ShulkerEntity.class, Sound.SHULKER_M);
        qr(SilverfishEntity.class, Sound.SILVERFISH_M);
        qr(SkeletonEntity.class, Sound.SKELETON_M);
        qr(SlimeEntity.class, Sound.SLIME_M);
        qr(SnowGolemEntity.class, Sound.SNOW_GOLEM_M);
        qr(SpiderEntity.class, Sound.SPIDER_M);
        qr(SquidEntity.class, Sound.SQUID_M);
        qr(StrayEntity.class, Sound.STRAY_M);
        qr(StriderEntity.class, Sound.STRIDER_M);
        qr(TurtleEntity.class, Sound.TURTLE_M);
        qr(VexEntity.class, Sound.VEX_M);
        qr(VillagerEntity.class, Sound.VILLAGER_M);
        qr(VindicatorEntity.class, Sound.VINDICATOR_M);
        qr(WanderingTraderEntity.class, Sound.WANDERING_TRADER_M);
        qr(WitchEntity.class, Sound.WITCH_M);
        qr(WitherEntity.class, Sound.WITHER_M);
        qr(WitherSkeletonEntity.class, Sound.WITHER_SKELETON_M);
        qr(WolfEntity.class, Sound.WOLF_M);
        qr(ZoglinEntity.class, Sound.ZOGLIN_M);
        qr(ZombieEntity.class, Sound.ZOMBIE_M);
        qr(ZombieVillagerEntity.class, Sound.ZOMBIE_VILLAGER_M);
    }

    private static void qr(Class<? extends Entity> clz, Sound s) {
        SpottingManager.addStrategy(clz, new SoundStrategy(s));
    }
}
