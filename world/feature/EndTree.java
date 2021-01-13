package com.jjhjart.ancientcraft.world.feature;

import com.jjhjart.ancientcraft.init.ModBlocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

import java.util.Random;


public class EndTree extends Tree {
    public static final TreeFeatureConfig END_TREE_CONFIG = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.END_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.END_LEAVES.get().getDefaultState()),
            new BlobFoliagePlacer(3, 0))).baseHeight(5).heightRandA(3).foliageHeight(3).ignoreVines()
                    .setSapling((IPlantable)ModBlocks.END_SAPLING.get()).build();


    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.NORMAL_TREE.withConfiguration(END_TREE_CONFIG);
    }
}
