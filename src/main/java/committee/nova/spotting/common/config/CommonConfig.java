package committee.nova.spotting.common.config;

import committee.nova.spotting.Spotting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.DoubleValue defaultTracingRange;
    public static final ForgeConfigSpec.IntValue defaultSpottingCoolDown;
    public static final ForgeConfigSpec.IntValue defaultGlowingTime;
    private static final ForgeConfigSpec.IntValue internalVer;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Spotting General Settings").push("general");
        defaultTracingRange = builder.comment("Tracing range of spotting.")
                .defineInRange("tracing_range", 120.0, 1.0, 50000.0);
        defaultSpottingCoolDown = builder.comment("CD(second) of spotting, only applied after a successful spotting.")
                .defineInRange("spotting_cd", 2, 1, 3600);
        defaultGlowingTime = builder.comment("Time(tick) of glowing after being spotted.")
                .defineInRange("glowing_time", 15, 1, 3600);
        internalVer = builder
                .comment("DO NOT change the value if you don't know what it is!!!")
                .comment("If the value is smaller than Spotting#internalVersion,")
                .comment("all config specs will be set to default next time the server starts.")
                .defineInRange("internalVer", -1, -1, Integer.MAX_VALUE);
        builder.pop();
        CONFIG = builder.build();
    }

    public static void refreshIfNeeded(@Nonnull MinecraftServer server) {
        if (internalVer.get() >= Spotting.internalVersion) return;
        defaultTracingRange.set(120.0);
        defaultSpottingCoolDown.set(2);
        defaultGlowingTime.set(15);
        internalVer.set(Spotting.internalVersion);
        CONFIG.save();
        final Component msg = Component.translatable("msg.spotting.cfg.refreshed");
        server.getPlayerList().broadcastSystemMessage(msg, false);
    }
}
