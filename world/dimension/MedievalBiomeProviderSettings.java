package com.jjhjart.ancientcraft.world.dimension;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class MedievalBiomeProviderSettings implements IBiomeProviderSettings {

    private final long seed;
    private final WorldType worldType;
    private MedievalGenSettings generatorSettings = new MedievalGenSettings();

    public MedievalBiomeProviderSettings(WorldInfo info) {
        this.seed = info.getSeed();
        this.worldType = info.getGenerator();
    }

    public MedievalBiomeProviderSettings setGeneratorSettings(MedievalGenSettings settings) {
        this.generatorSettings = settings;
        return this;
    }

    public long getSeed() {
        return this.seed;
    }

    public WorldType getWorldType() {
        return this.worldType;
    }

    public MedievalGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}

