package com.jjhjart.ancientcraft.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class RomanGenSettings extends GenerationSettings {

    public int getBiomeSize() {
        return 7;
    }
    public int getRiverSize() {
        return 4;
    }
    public int getBiomeId() {
        return -1;
    }

    @Override
    public int getBedrockFloorHeight() {
        return 0;
    }
}
