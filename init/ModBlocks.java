package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.blocks.*;
import com.jjhjart.ancientcraft.blocks.tileentity.TMCooldownTileEntity;
import com.jjhjart.ancientcraft.blocks.tileentity.TemporalCauldronTileEntity;
import com.jjhjart.ancientcraft.blocks.tileentity.TimeMachineRecallerTileEntity;
import com.jjhjart.ancientcraft.world.feature.EndTree;
import com.jjhjart.ancientcraft.world.feature.PineTree;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.jjhjart.ancientcraft.api.timemachine.block.TimeMachineControlPanelBlock;
import com.jjhjart.ancientcraft.api.timemachine.block.TimeMachineCoreBlock;
import com.jjhjart.ancientcraft.api.timemachine.block.TimeMachineUpgradeBlock;
//import com.jjhjart.ancientcraft.blocks.GunpowderWireBlock;
//import com.jjhjart.ancientcraft.blocks.TemporalCauldronBlock;
//import com.jjhjart.ancientcraft.blocks.TemporalExplosionBlock;
//import com.jjhjart.ancientcraft.blocks.TimeMachineRecallerBlock;
//import com.jjhjart.ancientcraft.blocks.tileentity.TMCooldownTileEntity;
//import com.jjhjart.ancientcraft.blocks.tileentity.TemporalCauldronTileEntity;
//import com.jjhjart.ancientcraft.blocks.tileentity.TimeMachineRecallerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import java.util.HashSet;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NewVoid.MOD_ID);

    //Blocks
    public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block", CopperBlock::new);
    public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore", CopperOre::new);
    public static final RegistryObject<Block> OVEN = BLOCKS.register("oven", Oven::new);

    //public static final RegistryObject<Block> DIRT_BLOCK = BLOCKS.register("dirt", DirtBlock::new);

    public static final RegistryObject<Block> END_PLANKS = BLOCKS.register("end_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> END_LOG = BLOCKS.register("end_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> END_LEAVES = BLOCKS.register("end_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> END_SAPLING = BLOCKS.register("end_sapling", () -> new ModSaplingBlock(() -> new EndTree(), Block.Properties.from(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> PINE_PLANKS = BLOCKS.register("pine_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PINE_LOG = BLOCKS.register("pine_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PINE_LEAVES = BLOCKS.register("pine_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> PINE_SAPLING = BLOCKS.register("pine_sapling", () -> new ModSaplingBlock(() -> new PineTree(), Block.Properties.from(Blocks.OAK_SAPLING)));

    //public static final RegistryObject<Block> TIME_CRYSTAL_ORE = BLOCKS.register("timecrystalore", () -> new Block((Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(5f).lightValue(5 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(3))));
    //public static final RegistryObject<Block> TIME_MACHINE_BASIC_BLOCK = BLOCKS.register("timemachinebasicblock", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3f).lightValue(0 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    //public static final RegistryObject<Block> TIME_MACHINE_CORE = BLOCKS.register("timemachinecore", () -> new TimeMachineCoreBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4f).lightValue(5 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(3)));
    //public static final RegistryObject<Block> TIME_MACHINE_CONTROL_PANEL = BLOCKS.register("timemachinecontrolpanel", () -> new TimeMachineControlPanelBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3f).lightValue(0 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    //public static final RegistryObject<Block> HEAVY_BLOCK = BLOCKS.register("heavyblock", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(7f).lightValue(0 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(3)));
    //public static final RegistryObject<Block> REINFORCED_HEAVY_BLOCK = BLOCKS.register("reinforcedheavyblock", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(10f).lightValue(10).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(3)));
    //public static final RegistryObject<Block> TEMPORAL_EXPLOSION = BLOCKS.register("temporalexplosion", TemporalExplosionBlock::new);
    //public static final RegistryObject<Block> TEMPORAL_CAULDRON = BLOCKS.register("temporalcauldron", TemporalCauldronBlock::new);
    //public static final RegistryObject<Block> GUNPOWDER_WIRE = BLOCKS.register("gunpowderwire", GunpowderWireBlock::new);
    //public static final RegistryObject<Block> TIME_MACHINE_TRACKER = BLOCKS.register("timemachinetracker", () -> new TimeMachineUpgradeBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3f).lightValue(0 / 16).variableOpacity().harvestTool(ToolType.PICKAXE).harvestLevel(2), ModTimeMachines.Upgrades.TRACKER));
    //public static final RegistryObject<Block> TIME_MACHINE_RECALLER = BLOCKS.register("timemachinerecaller", TimeMachineRecallerBlock::new);



    @OnlyIn(Dist.CLIENT)
    public static void registerBlockColor(ColorHandlerEvent.Block event) {
        //event.getBlockColors().register((state, world, pos, num) -> GunpowderWireBlock.colorMultiplier(state.get(GunpowderWireBlock.BURNED)), ModBlocks.GUNPOWDER_WIRE.get());
    }
    public static class TileEntities {

        private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, NewVoid.MOD_ID);

        public static final RegistryObject<TileEntityType<TemporalCauldronTileEntity>> TEMPORAL_CAULDRON = TILE_ENTITIES.register("temporalcauldron", () -> TileEntityType.Builder.create(TemporalCauldronTileEntity::new, ModBlocks.COPPER_BLOCK.get()).build(null));
        public static final RegistryObject<TileEntityType<TMCooldownTileEntity>> TM_COOLDOWN = TILE_ENTITIES.register("tmcooldown", () -> TileEntityType.Builder.create(TMCooldownTileEntity::new, getAllCoreBlocks()).build(null));
        public static final RegistryObject<TileEntityType<TimeMachineRecallerTileEntity>> TM_RECALLER = TILE_ENTITIES.register("tmrecaller", () -> TileEntityType.Builder.create(TimeMachineRecallerTileEntity::new, ModBlocks.COPPER_BLOCK.get()).build(null));

        private static Block[] getAllCoreBlocks() {
            HashSet<Block> blocks = new HashSet<Block>();
            for (Block block : ForgeRegistries.BLOCKS.getValues()) {
                if (block instanceof TimeMachineCoreBlock) blocks.add(block);
            }
            return blocks.toArray(new Block[]{});
        }
    }
}





