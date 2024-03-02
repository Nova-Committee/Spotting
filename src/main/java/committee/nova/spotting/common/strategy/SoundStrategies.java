package committee.nova.spotting.common.strategy;

import committee.nova.spotting.common.manager.SpottingManager;
import committee.nova.spotting.common.sound.init.VanillaSound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;

public class SoundStrategies {
    public static void init() {
        qr(Allay.class, VanillaSound.ALLAY);
        qr(Axolotl.class, VanillaSound.AXOLOTL);
        qr(Bat.class, VanillaSound.BAT);
        qr(Bee.class, VanillaSound.BEE);
        qr(Blaze.class, VanillaSound.BLAZE);
        qr(Cat.class, VanillaSound.CAT);
        qr(Camel.class, VanillaSound.CAMEL);
        qr(CaveSpider.class, VanillaSound.CAVE_SPIDER);
        SpottingManager.addStrategy(Creeper.class, new SoundStrategy(0, e -> ((Creeper) e).isPowered() ?
                VanillaSound.CHARGED_CREEPER : VanillaSound.CREEPER));
        SpottingManager.addStrategy(Chicken.class, new SoundStrategy(0, e -> ((Chicken) e).isChickenJockey ?
                VanillaSound.CHICKEN_JOCKEY : VanillaSound.CHICKEN));
        qr(Cod.class, VanillaSound.COD);
        qr(Cow.class, VanillaSound.COW);
        qr(Dolphin.class, VanillaSound.DOLPHIN);
        qr(Donkey.class, VanillaSound.DONKEY);
        qr(Drowned.class, VanillaSound.DROWNED);
        qr(ElderGuardian.class, VanillaSound.ELDER_GUARDIAN);
        qr(EnderDragon.class, VanillaSound.ENDER_DRAGON);
        qr(EnderMan.class, VanillaSound.ENDERMAN);
        qr(Endermite.class, VanillaSound.ENDERMITE);
        qr(Evoker.class, VanillaSound.EVOKER);
        qr(AbstractFish.class, VanillaSound.FISH);
        qr(Fox.class, VanillaSound.FOX);
        qr(Frog.class, VanillaSound.FROG);
        qr(Ghast.class, VanillaSound.GHAST);
        qr(Giant.class, VanillaSound.GIANT);
        qr(Goat.class, VanillaSound.GOAT);
        qr(Guardian.class, VanillaSound.GUARDIAN);
        qr(Hoglin.class, VanillaSound.HOGLIN);
        qr(Horse.class, VanillaSound.HORSE);
        qr(AbstractHorse.class, VanillaSound.HORSE);
        qr(Husk.class, VanillaSound.HUSK);
        qr(Illusioner.class, VanillaSound.ILLUSIONER);
        qr(IronGolem.class, VanillaSound.IRON_GOLEM);
        qr(Llama.class, VanillaSound.LLAMA);
        qr(MagmaCube.class, VanillaSound.MAGMA_CUBE);
        qr(MushroomCow.class, VanillaSound.MOOSHROOM);
        qr(Mule.class, VanillaSound.MULE);
        qr(Ocelot.class, VanillaSound.OCELOT);
        qr(Panda.class, VanillaSound.PANDA);
        qr(Parrot.class, VanillaSound.PARROT);
        qr(Phantom.class, VanillaSound.PHANTOM);
        qr(Pig.class, VanillaSound.PIG);
        qr(Piglin.class, VanillaSound.PIGLIN);
        qr(PiglinBrute.class, VanillaSound.PIGLIN_BRUTE);
        qr(Pillager.class, VanillaSound.PILLAGER);
        qr(Player.class, VanillaSound.PLAYER);
        qr(PolarBear.class, VanillaSound.POLAR_BEAR);
        qr(Pufferfish.class, VanillaSound.PUFFERFISH);
        qr(Rabbit.class, VanillaSound.RABBIT);
        qr(Ravager.class, VanillaSound.RAVAGER);
        qr(Salmon.class, VanillaSound.SALMON);
        qr(Sheep.class, VanillaSound.SHEEP);
        qr(Shulker.class, VanillaSound.SHULKER);
        qr(Silverfish.class, VanillaSound.SILVERFISH);
        qr(Skeleton.class, VanillaSound.SKELETON);
        qr(SkeletonHorse.class, VanillaSound.SKELETON_HORSE);
        qr(Slime.class, VanillaSound.SLIME);
        qr(SnowGolem.class, VanillaSound.SNOW_GOLEM);
        qr(Spider.class, VanillaSound.SPIDER);
        qr(Squid.class, VanillaSound.SQUID);
        qr(Stray.class, VanillaSound.STRAY);
        qr(Strider.class, VanillaSound.STRIDER);
        qr(Tadpole.class, VanillaSound.TADPOLE);
        qr(TropicalFish.class, VanillaSound.TROPICAL_FISH);
        qr(Turtle.class, VanillaSound.TURTLE);
        qr(Vex.class, VanillaSound.VEX);
        qr(Villager.class, VanillaSound.VILLAGER);
        qr(Vindicator.class, VanillaSound.VINDICATOR);
        qr(WanderingTrader.class, VanillaSound.WANDERING_TRADER);
        qr(Warden.class, VanillaSound.WARDEN);
        qr(Witch.class, VanillaSound.WITCH);
        qr(WitherBoss.class, VanillaSound.WITHER);
        qr(WitherSkeleton.class, VanillaSound.WITHER_SKELETON);
        qr(Wolf.class, VanillaSound.WOLF);
        qr(Zoglin.class, VanillaSound.ZOGLIN);
        qr(Zombie.class, VanillaSound.ZOMBIE);
        qr(ZombieHorse.class, VanillaSound.ZOMBIE_HORSE);
        qr(ZombieVillager.class, VanillaSound.ZOMBIE_VILLAGER);
        qr(ZombifiedPiglin.class, VanillaSound.ZOMBIFIED_PIGLIN);
    }

    private static void qr(Class<? extends Entity> clz, VanillaSound s) {
        SpottingManager.addStrategy(clz, new SoundStrategy(s));
    }
}
