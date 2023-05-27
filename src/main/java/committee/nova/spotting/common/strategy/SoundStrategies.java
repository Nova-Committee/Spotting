package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.sound.init.SoundIndex;
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
        qr(BatEntity.class, SoundIndex.BAT);
        qr(BeeEntity.class, SoundIndex.BEE);
        qr(BlazeEntity.class, SoundIndex.BLAZE);
        qr(CatEntity.class, SoundIndex.CAT);
        qr(CaveSpiderEntity.class, SoundIndex.CAVE_SPIDER);
        SpottingManager.addStrategy(CreeperEntity.class, new SoundStrategy(0, (s, m) -> ((CreeperEntity) s).isCharged() ?
                SoundIndex.CHARGED_CREEPER.get(m).getSoundEvent() : SoundIndex.CREEPER.get(m).getSoundEvent()));
        SpottingManager.addStrategy(ChickenEntity.class, new SoundStrategy(0, (c, m) -> ((ChickenEntity) c).chickenJockey ?
                SoundIndex.CHICKEN_JOCKEY.get(m).getSoundEvent() : SoundIndex.CHICKEN.get(m).getSoundEvent()));
        qr(CowEntity.class, SoundIndex.COW);
        qr(DolphinEntity.class, SoundIndex.DOLPHIN);
        qr(DrownedEntity.class, SoundIndex.DROWNED);
        qr(ElderGuardianEntity.class, SoundIndex.ELDER_GUARDIAN);
        qr(EnderDragonEntity.class, SoundIndex.ENDER_DRAGON);
        qr(EndermanEntity.class, SoundIndex.ENDERMAN);
        qr(EndermiteEntity.class, SoundIndex.ENDERMITE);
        qr(EvokerEntity.class, SoundIndex.EVOKER);
        qr(AbstractFishEntity.class, SoundIndex.FISH);
        qr(GhastEntity.class, SoundIndex.GHAST);
        qr(GiantEntity.class, SoundIndex.GIANT);
        qr(GuardianEntity.class, SoundIndex.GUARDIAN);
        qr(HoglinEntity.class, SoundIndex.HOGLIN);
        qr(AbstractHorseEntity.class, SoundIndex.HORSE);
        qr(HuskEntity.class, SoundIndex.HUSK);
        qr(IllusionerEntity.class, SoundIndex.ILLUSIONER);
        qr(IronGolemEntity.class, SoundIndex.IRON_GOLEM);
        qr(LlamaEntity.class, SoundIndex.LLAMA);
        qr(MagmaCubeEntity.class, SoundIndex.MAGMA_CUBE);
        qr(MooshroomEntity.class, SoundIndex.MOOSHROOM);
        qr(OcelotEntity.class, SoundIndex.OCELOT);
        qr(PandaEntity.class, SoundIndex.PANDA);
        qr(ParrotEntity.class, SoundIndex.PARROT);
        qr(PhantomEntity.class, SoundIndex.PHANTOM);
        qr(PigEntity.class, SoundIndex.PIG);
        qr(PiglinEntity.class, SoundIndex.PIGLIN);
        qr(PiglinBruteEntity.class, SoundIndex.PIGLIN_BRUTE);
        qr(PillagerEntity.class, SoundIndex.PILLAGER);
        qr(PlayerEntity.class, SoundIndex.PLAYER);
        qr(PolarBearEntity.class, SoundIndex.POLAR_BEAR);
        qr(PufferfishEntity.class, SoundIndex.PUFFERFISH);
        qr(RabbitEntity.class, SoundIndex.RABBIT);
        qr(RavagerEntity.class, SoundIndex.RAVAGER);
        qr(SheepEntity.class, SoundIndex.SHEEP);
        qr(ShulkerEntity.class, SoundIndex.SHULKER);
        qr(SilverfishEntity.class, SoundIndex.SILVERFISH);
        qr(SkeletonEntity.class, SoundIndex.SKELETON);
        qr(SlimeEntity.class, SoundIndex.SLIME);
        qr(SnowGolemEntity.class, SoundIndex.SNOW_GOLEM);
        qr(SpiderEntity.class, SoundIndex.SPIDER);
        qr(SquidEntity.class, SoundIndex.SQUID);
        qr(StrayEntity.class, SoundIndex.STRAY);
        qr(StriderEntity.class, SoundIndex.STRIDER);
        qr(TurtleEntity.class, SoundIndex.TURTLE);
        qr(VexEntity.class, SoundIndex.VEX);
        qr(VillagerEntity.class, SoundIndex.VILLAGER);
        qr(VindicatorEntity.class, SoundIndex.VINDICATOR);
        qr(WanderingTraderEntity.class, SoundIndex.WANDERING_TRADER);
        qr(WitchEntity.class, SoundIndex.WITCH);
        qr(WitherEntity.class, SoundIndex.WITHER);
        qr(WitherSkeletonEntity.class, SoundIndex.WITHER_SKELETON);
        qr(WolfEntity.class, SoundIndex.WOLF);
        qr(ZoglinEntity.class, SoundIndex.ZOGLIN);
        qr(ZombieEntity.class, SoundIndex.ZOMBIE);
        qr(ZombieVillagerEntity.class, SoundIndex.ZOMBIE_VILLAGER);
    }

    private static void qr(Class<? extends Entity> clz, SoundIndex s) {
        SpottingManager.addStrategy(clz, new SoundStrategy(s));
    }
}
