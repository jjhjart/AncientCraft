package com.jjhjart.ancientcraft.world.feature;

import com.google.common.collect.ImmutableList;
import com.jjhjart.ancientcraft.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;

public class Pine_Tree_Config {
    public static final TreeFeatureConfig PINE_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PINE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.PINE_LEAVES.get().getDefaultState()), new SpruceFoliagePlacer(2, 1))).baseHeight(6).heightRandA(3).trunkHeight(1).trunkHeightRandom(1).trunkTopOffsetRandom(2).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) ModBlocks.PINE_SAPLING.get()).build();

    public static final HugeTreeFeatureConfig MEGA_PINE_TREE_CONFIG = (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PINE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.PINE_LEAVES.get().getDefaultState()))).baseHeight(13).func_227283_b_(15).func_227284_c_(13).func_227282_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState())))).setSapling((net.minecraftforge.common.IPlantable)ModBlocks.PINE_SAPLING.get()).build();
    public static final HugeTreeFeatureConfig MEGA_PINE2_TREE_CONFIG = (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PINE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.PINE_LEAVES.get().getDefaultState()))).baseHeight(13).func_227283_b_(15).func_227284_c_(3).func_227282_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState())))).setSapling((net.minecraftforge.common.IPlantable)ModBlocks.PINE_SAPLING.get()).build();
}
