package com.jjhjart.ancientcraft.world.dimension;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.jjhjart.ancientcraft.init.ModBiomes;


import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class RomanBiomeProvider extends BiomeProvider {

    private static final Set<Biome> biomeList = ImmutableSet.of(ModBiomes.ROME.get(), ModBiomes.ROME_MOUNTAINS.get());
    private Random rand;
    private final double biomeSize = 16.0D;
    private VoronoiGenerator biomeNoise;

    public RomanBiomeProvider(RomanBiomeProviderSettings settings) {
        super(biomeList);
        rand = new Random();
        this.biomeNoise = new VoronoiGenerator();
        this.biomeNoise.setSeed((int) settings.getSeed());
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return getBiome(new LinkedList<Biome>(biomeList),
                biomeNoise.getValue((double) x / biomeSize, (double) y / biomeSize, (double) z / biomeSize));
    }

    public Biome getBiome(List<Biome> biomeList, double noiseVal) {
        for (int i = biomeList.size(); i >= 0; i--) {
            if (noiseVal > (2.0f / biomeList.size()) * i - 1)
                return biomeList.get(i);
        }
        return biomeList.get(biomeList.size() - 1);
    }
}
