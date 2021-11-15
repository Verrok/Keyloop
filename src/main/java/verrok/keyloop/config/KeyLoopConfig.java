package verrok.keyloop.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KeyLoopConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> textRenderEnabled;

    static {
        BUILDER.push("KeyLoop config");

        textRenderEnabled = BUILDER.comment("Defines will be additional info rendered on top left side of screen").define("textRenderEnabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
