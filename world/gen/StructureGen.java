package com.jjhjart.ancientcraft.world.gen;

import com.jjhjart.ancientcraft.init.ModBiomes;
import com.jjhjart.ancientcraft.init.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class StructureGen {
    public static void generateStructures() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome != ModBiomes.ROME_MOUNTAINS.get()) {
                if (biome == ModBiomes.ROME.get()) {
                    biome.addStructure(ModFeatures.CAMP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                }
            }
            if (biome == ModBiomes.BRITAIN.get()) {
                Random rand = new Random();
                int i = rand.nextInt(4);
                if (i < 5) {
                    biome.addStructure(ModFeatures.CASTLE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                }
            }

            biome.addFeature(Decoration.SURFACE_STRUCTURES,
                    ModFeatures.CAMP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

            biome.addFeature(Decoration.SURFACE_STRUCTURES,
                    ModFeatures.CASTLE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }
}