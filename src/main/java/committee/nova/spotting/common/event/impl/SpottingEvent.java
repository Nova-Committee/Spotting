package committee.nova.spotting.common.event.impl;

import committee.nova.spotting.common.config.CommonConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class SpottingEvent extends PlayerEvent {
    public SpottingEvent(PlayerEntity spotter) {
        super(spotter);
    }

    public PlayerEntity getSpotter() {
        return getPlayer();
    }

    public static class TracingRange extends SpottingEvent {
        private double tracingRange = CommonConfig.defaultTracingRange.get();

        public TracingRange(PlayerEntity spotter) {
            super(spotter);
        }

        public void setTracingRange(double tracingRange) {
            this.tracingRange = tracingRange;
        }

        public double getTracingRange() {
            return tracingRange;
        }
    }

    public static class SpotterEvent extends SpottingEvent {
        private final Entity spottee;

        public SpotterEvent(PlayerEntity spotter, Entity spottee) {
            super(spotter);
            this.spottee = spottee;
        }

        public Entity getSpottee() {
            return spottee;
        }
    }

    @Cancelable
    public static class Pre extends SpotterEvent {
        private int spottingCd = CommonConfig.defaultSpottingCoolDown.get();
        private int highlightTime = CommonConfig.defaultGlowingTime.get();

        public Pre(PlayerEntity spotter, Entity spottee) {
            super(spotter, spottee);
        }

        public void setSpottingCd(int spottingCd) {
            this.spottingCd = spottingCd;
        }

        public void setHighlightTime(int glowingTime) {
            this.highlightTime = glowingTime;
        }

        public int getSpottingCd() {
            return spottingCd;
        }

        public int getHighlightTime() {
            return highlightTime;
        }
    }

    public static class Post extends SpotterEvent {
        public Post(PlayerEntity spotter, Entity spottee) {
            super(spotter, spottee);
        }
    }
}
