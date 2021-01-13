package com.jjhjart.ancientcraft.world.dimension;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class RomanBiomeProviderSettings implements IBiomeProviderSettings {

    private final long seed;
    private final WorldType worldType;
    private RomanGenSettings generatorSettings = new RomanGenSettings();

    public RomanBiomeProviderSettings(WorldInfo info) {
        this.seed = info.getSeed();
        this.worldType = info.getGenerator();
    }

    public RomanBiomeProviderSettings setGeneratorSettings(RomanGenSettings settings) {
        this.generatorSettings = settings;
        return this;
    }

    public long getSeed() {
        return this.seed;
    }

    public WorldType getWorldType() {
        return this.worldType;
    }

    public RomanGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}

