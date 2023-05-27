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
import net.minecraft.entity.passive.fish.*;
import net.minecraft.entity.passive.horse.*;
import net.minecraft.entity.player.PlayerEntity;

public class SoundStrategies {
    public static void init() {
        qr(BatEntity.class, Sound.BAT);
        qr(BeeEntity.class, Sound.BEE);
        qr(BlazeEntity.class, Sound.BLAZE);
        qr(CatEntity.class, Sound.CAT);
        qr(CaveSpiderEntity.class, Sound.CAVE_SPIDER);
        SpottingManager.addStrategy(CreeperEntity.class, new SoundStrategy(0, (s, m) -> ((CreeperEntity) s).isCharged() ?
                Sound.CHARGED_CREEPER.get(m) : Sound.CREEPER.get(m)));
        SpottingManager.addStrategy(ChickenEntity.class, new SoundStrategy(0, (c, m) -> ((ChickenEntity) c).chickenJockey ?
                Sound.CHICKEN_JOCKEY.get(m) : Sound.CHICKEN.get(m)));
        qr(CodEntity.class, Sound.COD);
        qr(CowEntity.class, Sound.COW);
        qr(DolphinEntity.class, Sound.DOLPHIN);
        qr(DonkeyEntity.class, Sound.DONKEY);
        qr(DrownedEntity.class, Sound.DROWNED);
        qr(ElderGuardianEntity.class, Sound.ELDER_GUARDIAN);
        qr(EnderDragonEntity.class, Sound.ENDER_DRAGON);
        qr(EndermanEntity.class, Sound.ENDERMAN);
        qr(EndermiteEntity.class, Sound.ENDERMITE);
        qr(EvokerEntity.class, Sound.EVOKER);
        qr(AbstractFishEntity.class, Sound.FISH);
        qr(FoxEntity.class, Sound.FOX);
        qr(GhastEntity.class, Sound.GHAST);
        qr(GiantEntity.class, Sound.GIANT);
        qr(GuardianEntity.class, Sound.GUARDIAN);
        qr(HoglinEntity.class, Sound.HOGLIN);
        qr(HorseEntity.class, Sound.HORSE);
        qr(AbstractHorseEntity.class, Sound.HORSE);
        qr(HuskEntity.class, Sound.HUSK);
        qr(IllusionerEntity.class, Sound.ILLUSIONER);
        qr(IronGolemEntity.class, Sound.IRON_GOLEM);
        qr(LlamaEntity.class, Sound.LLAMA);
        qr(MagmaCubeEntity.class, Sound.MAGMA_CUBE);
        qr(MooshroomEntity.class, Sound.MOOSHROOM);
        qr(MuleEntity.class, Sound.MULE);
        qr(OcelotEntity.class, Sound.OCELOT);
        qr(PandaEntity.class, Sound.PANDA);
        qr(ParrotEntity.class, Sound.PARROT);
        qr(PhantomEntity.class, Sound.PHANTOM);
        qr(PigEntity.class, Sound.PIG);
        qr(PiglinEntity.class, Sound.PIGLIN);
        qr(PiglinBruteEntity.class, Sound.PIGLIN_BRUTE);
        qr(PillagerEntity.class, Sound.PILLAGER);
        qr(PlayerEntity.class, Sound.PLAYER);
        qr(PolarBearEntity.class, Sound.POLAR_BEAR);
        qr(PufferfishEntity.class, Sound.PUFFERFISH);
        qr(RabbitEntity.class, Sound.RABBIT);
        qr(RavagerEntity.class, Sound.RAVAGER);
        qr(SalmonEntity.class, Sound.SALMON);
        qr(SheepEntity.class, Sound.SHEEP);
        qr(ShulkerEntity.class, Sound.SHULKER);
        qr(SilverfishEntity.class, Sound.SILVERFISH);
        qr(SkeletonEntity.class, Sound.SKELETON);
        qr(SkeletonHorseEntity.class, Sound.SKELETON_HORSE);
        qr(SlimeEntity.class, Sound.SLIME);
        qr(SnowGolemEntity.class, Sound.SNOW_GOLEM);
        qr(SpiderEntity.class, Sound.SPIDER);
        qr(SquidEntity.class, Sound.SQUID);
        qr(StrayEntity.class, Sound.STRAY);
        qr(StriderEntity.class, Sound.STRIDER);
        qr(TropicalFishEntity.class, Sound.TROPICAL_FISH);
        qr(TurtleEntity.class, Sound.TURTLE);
        qr(VexEntity.class, Sound.VEX);
        qr(VillagerEntity.class, Sound.VILLAGER);
        qr(VindicatorEntity.class, Sound.VINDICATOR);
        qr(WanderingTraderEntity.class, Sound.WANDERING_TRADER);
        qr(WitchEntity.class, Sound.WITCH);
        qr(WitherEntity.class, Sound.WITHER);
        qr(WitherSkeletonEntity.class, Sound.WITHER_SKELETON);
        qr(WolfEntity.class, Sound.WOLF);
        qr(ZoglinEntity.class, Sound.ZOGLIN);
        qr(ZombieEntity.class, Sound.ZOMBIE);
        qr(ZombieHorseEntity.class, Sound.ZOMBIE_HORSE);
        qr(ZombieVillagerEntity.class, Sound.ZOMBIE_VILLAGER);
        qr(ZombifiedPiglinEntity.class, Sound.ZOMBIFIED_PIGLIN);
    }

    private static void qr(Class<? extends Entity> clz, Sound s) {
        SpottingManager.addStrategy(clz, new SoundStrategy(s));
    }
}
