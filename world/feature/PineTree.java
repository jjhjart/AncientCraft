package com.jjhjart.ancientcraft.world.feature;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class PineTree extends Tree {
    /**
     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
     */
    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.NORMAL_TREE.withConfiguration(Pine_Tree_Config.PINE_TREE_CONFIG);
    }

    /**
     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of the huge variant of this tree
     */
    @Nullable
    protected ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
        return Feature.MEGA_SPRUCE_TREE.withConfiguration(p_225547_1_.nextBoolean() ? Pine_Tree_Config.MEGA_PINE_TREE_CONFIG : Pine_Tree_Config.MEGA_PINE2_TREE_CONFIG);
    }
}
