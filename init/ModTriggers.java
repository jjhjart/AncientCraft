package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.triggers.CustomTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;

import static com.jjhjart.ancientcraft.NewVoid.MOD_ID;

public class ModTriggers {
    public static final CustomTrigger ACCESS_TIME_MACHINE = new CustomTrigger(new ResourceLocation(MOD_ID, "access_time_machine"));
    public static final CustomTrigger BETTER_THAN_MENDING = new CustomTrigger(new ResourceLocation(MOD_ID, "better_than_mending"));
    public static final CustomTrigger TEMPORAL_EXPLOSION = new CustomTrigger(new ResourceLocation(MOD_ID, "temporal_explosion"));

    public static final CustomTrigger[] TRIGGERS = new CustomTrigger[]{
            ACCESS_TIME_MACHINE,
            BETTER_THAN_MENDING,
            TEMPORAL_EXPLOSION
    };

    public static void register() {
        for (CustomTrigger trigger : TRIGGERS) CriteriaTriggers.register(trigger);
    }
}
