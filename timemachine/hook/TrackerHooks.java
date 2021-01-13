package com.jjhjart.ancientcraft.timemachine.hook;

import com.jjhjart.ancientcraft.init.ModBlocks;
import com.jjhjart.ancientcraft.init.ModTimeMachines;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.api.timemachine.upgrade.TimeMachineHook;
import com.jjhjart.ancientcraft.blocks.TimeMachineRecallerBlock;
import com.jjhjart.ancientcraft.blocks.tileentity.TimeMachineRecallerTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class TrackerHooks {

    public static final TimeMachineHook<?>[] HOOKS = {new TeleporterHook()};

    public static class TeleporterHook implements TimeMachineHook.TeleporterTasks {

        @Override
        public void run(TimeMachine tm, Entity entity, World worldIn, World worldOut, BlockPos controllerPos, Direction side, boolean shouldBuild) {
            tm.removeHooks().teleporterTasks(entity, worldIn, worldOut, controllerPos, side, shouldBuild);
            if (shouldBuild) {
                Set<BlockPos> upgrades = tm.getUpgradePos(ModTimeMachines.Upgrades.TRACKER);
                for (BlockPos pos: upgrades) {
                    for (Direction direction: Direction.values()) {
                        BlockPos recaller = pos.offset(direction);
                        if (worldOut.getBlockState(recaller).getBlock() == ModBlocks.COPPER_BLOCK.get()) {
                            worldOut.setBlockState(recaller, worldOut.getBlockState(recaller).with(TimeMachineRecallerBlock.CONFIGURED, true));
                            TileEntity tile = worldOut.getTileEntity(recaller);
                            if (tile instanceof TimeMachineRecallerTileEntity) {
                                ((TimeMachineRecallerTileEntity) tile).setControllerPos(controllerPos);
                                ((TimeMachineRecallerTileEntity) tile).setSide(side);
                                ((TimeMachineRecallerTileEntity) tile).setDest(worldIn.getDimension().getType());
                            }
                        }
                    }
                }
            }
        }
    }
}
