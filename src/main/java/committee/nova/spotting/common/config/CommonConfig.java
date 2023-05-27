package committee.nova.spotting.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.DoubleValue defaultTracingRange;
    public static final ForgeConfigSpec.IntValue defaultSpottingCoolDown;
    public static final ForgeConfigSpec.IntValue defaultGlowingTime;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Spotting General Settings").push("general");
        defaultTracingRange = builder.comment("Tracing range of spotting.")
                .defineInRange("tracing_range", 120.0, 1.0, 50000.0);
        defaultSpottingCoolDown = builder.comment("CD(tick) of spotting, only applied after a successful spotting.")
                .defineInRange("spotting_cd", 30, 5, 72000);
        defaultGlowingTime = builder.comment("Time(tick) of glowing after being spotted.")
                .defineInRange("glowing_time", 300, 20, 72000);
        builder.pop();
        CONFIG = builder.build();
    }
}
