package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.sound.init.Sound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;

public class SoundStrategies {
    public static void init() {
        qr(Axolotl.class, Sound.AXOLOTL);
        qr(Bat.class, Sound.BAT);
        qr(Bee.class, Sound.BEE);
        qr(Blaze.class, Sound.BLAZE);
        qr(Cat.class, Sound.CAT);
        qr(CaveSpider.class, Sound.CAVE_SPIDER);
        SpottingManager.addStrategy(Creeper.class, new SoundStrategy(0, (s, m) -> ((Creeper) s).isPowered() ?
                Sound.CHARGED_CREEPER.get(m) : Sound.CREEPER.get(m)));
        SpottingManager.addStrategy(Chicken.class, new SoundStrategy(0, (c, m) -> ((Chicken) c).isChickenJockey ?
                Sound.CHICKEN_JOCKEY.get(m) : Sound.CHICKEN.get(m)));
        qr(Cod.class, Sound.COD);
        qr(Cow.class, Sound.COW);
        qr(Dolphin.class, Sound.DOLPHIN);
        qr(Donkey.class, Sound.DONKEY);
        qr(Drowned.class, Sound.DROWNED);
        qr(ElderGuardian.class, Sound.ELDER_GUARDIAN);
        qr(EnderDragon.class, Sound.ENDER_DRAGON);
        qr(EnderMan.class, Sound.ENDERMAN);
        qr(Endermite.class, Sound.ENDERMITE);
        qr(Evoker.class, Sound.EVOKER);
        qr(AbstractFish.class, Sound.FISH);
        qr(Fox.class, Sound.FOX);
        qr(Ghast.class, Sound.GHAST);
        qr(Giant.class, Sound.GIANT);
        qr(Goat.class, Sound.GOAT);
        qr(Guardian.class, Sound.GUARDIAN);
        qr(Hoglin.class, Sound.HOGLIN);
        qr(Horse.class, Sound.HORSE);
        qr(AbstractHorse.class, Sound.HORSE);
        qr(Husk.class, Sound.HUSK);
        qr(Illusioner.class, Sound.ILLUSIONER);
        qr(IronGolem.class, Sound.IRON_GOLEM);
        qr(Llama.class, Sound.LLAMA);
        qr(MagmaCube.class, Sound.MAGMA_CUBE);
        qr(MushroomCow.class, Sound.MOOSHROOM);
        qr(Mule.class, Sound.MULE);
        qr(Ocelot.class, Sound.OCELOT);
        qr(Panda.class, Sound.PANDA);
        qr(Parrot.class, Sound.PARROT);
        qr(Phantom.class, Sound.PHANTOM);
        qr(Pig.class, Sound.PIG);
        qr(Piglin.class, Sound.PIGLIN);
        qr(PiglinBrute.class, Sound.PIGLIN_BRUTE);
        qr(Pillager.class, Sound.PILLAGER);
        qr(Player.class, Sound.PLAYER);
        qr(PolarBear.class, Sound.POLAR_BEAR);
        qr(Pufferfish.class, Sound.PUFFERFISH);
        qr(Rabbit.class, Sound.RABBIT);
        qr(Ravager.class, Sound.RAVAGER);
        qr(Salmon.class, Sound.SALMON);
        qr(Sheep.class, Sound.SHEEP);
        qr(Shulker.class, Sound.SHULKER);
        qr(Silverfish.class, Sound.SILVERFISH);
        qr(Skeleton.class, Sound.SKELETON);
        qr(SkeletonHorse.class, Sound.SKELETON_HORSE);
        qr(Slime.class, Sound.SLIME);
        qr(SnowGolem.class, Sound.SNOW_GOLEM);
        qr(Spider.class, Sound.SPIDER);
        qr(Squid.class, Sound.SQUID);
        qr(Stray.class, Sound.STRAY);
        qr(Strider.class, Sound.STRIDER);
        qr(TropicalFish.class, Sound.TROPICAL_FISH);
        qr(Turtle.class, Sound.TURTLE);
        qr(Vex.class, Sound.VEX);
        qr(Villager.class, Sound.VILLAGER);
        qr(Vindicator.class, Sound.VINDICATOR);
        qr(WanderingTrader.class, Sound.WANDERING_TRADER);
        qr(Witch.class, Sound.WITCH);
        qr(WitherBoss.class, Sound.WITHER);
        qr(WitherSkeleton.class, Sound.WITHER_SKELETON);
        qr(Wolf.class, Sound.WOLF);
        qr(Zoglin.class, Sound.ZOGLIN);
        qr(Zombie.class, Sound.ZOMBIE);
        qr(ZombieHorse.class, Sound.ZOMBIE_HORSE);
        qr(ZombieVillager.class, Sound.ZOMBIE_VILLAGER);
        qr(ZombifiedPiglin.class, Sound.ZOMBIFIED_PIGLIN);
    }

    private static void qr(Class<? extends Entity> clz, Sound s) {
        SpottingManager.addStrategy(clz, new SoundStrategy(s));
    }
}
