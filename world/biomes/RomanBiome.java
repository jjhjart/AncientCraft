package com.jjhjart.ancientcraft.world.biomes;

import com.jjhjart.ancientcraft.init.ModEntityTypes;
import com.jjhjart.ancientcraft.world.feature.Pine_Tree_Config;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.placement.*;

public class RomanBiome extends Biome {
    public RomanBiome(Builder biomeBuilder) {
        super(biomeBuilder);
        //super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(),Blocks.STONE.getDefaultState())).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.4F).waterColor(0x2EBFB3).waterFogColor(329011).parent((String)null));
        this.addStructure(Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        //this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(Pine_Tree_Config.PINE_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(Pine_Tree_Config.PINE_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 2, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 10, 2, 10));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 2, 3));
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntityTypes.ARCHER.get(), 20, 2, 10));
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntityTypes.GLADIATOR.get(), 20, 2, 10));
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntityTypes.LEGIONARY.get(), 20, 2, 10));
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntityTypes.LION.get(), 20, 2, 10));
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        DefaultBiomeFeatures.addOakTreesFlowersGrass(this);
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return 0x96A30C;
    }

    @Override
    public int getSkyColor() {
        return 0x26D6D2;
    }
}
