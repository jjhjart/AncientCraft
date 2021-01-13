package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.api.dimension.TimeLine;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.api.timemachine.block.TimeMachineUpgradeBlock;
import com.jjhjart.ancientcraft.api.timemachine.upgrade.TimeMachineUpgrade;
import com.jjhjart.ancientcraft.timemachine.CreativeTimeMachine;

import com.jjhjart.ancientcraft.api.dimension.TimeLine;
import com.jjhjart.ancientcraft.api.timemachine.block.TimeMachineUpgradeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    public static IForgeRegistry<TimeMachine> TIME_MACHINES;
    public static IForgeRegistry<TimeLine> TIME_LINES;
    public static IForgeRegistry<TimeMachineUpgrade> UPGRADES;
    public static final ResourceLocation CONTROLLERTOTM = new ResourceLocation("ancientcraft:blocktotm");
    public static final ResourceLocation UPGRADETOBLOCK = new ResourceLocation("ancientcraft:upgradetoblock");

    @SubscribeEvent
    public static void addRegistries(RegistryEvent.NewRegistry event) {
        TIME_LINES = new RegistryBuilder<TimeLine>()
                .setType(TimeLine.class)
                .setName(new ResourceLocation("ancientcraft:timelines"))
                .create();

        TIME_MACHINES = new RegistryBuilder<TimeMachine>()
                .setType(TimeMachine.class)
                .setName(new ResourceLocation("ancientcraft:timemachines"))
                .addCallback(new TimeMachinesCallbacks())
                .legacyName("ancientcraft:timemachines")
                .create();

        UPGRADES = new RegistryBuilder<TimeMachineUpgrade>()
                .setType(TimeMachineUpgrade.class)
                .setName(new ResourceLocation("ancientcraft:tmupgrades"))
                .addCallback(new TimeMachineUpgradesCallbacks())
                .create();
    }

    public static class TimeMachinesCallbacks implements IForgeRegistry.CreateCallback<TimeMachine>, IForgeRegistry.AddCallback<TimeMachine> {

        private HashMap<BlockState, ResourceLocation> blockStateResourceLocationHashMap;

        @Override
        public void onCreate(IForgeRegistryInternal<TimeMachine> owner, RegistryManager stage) {
            blockStateResourceLocationHashMap = new HashMap<>();
            owner.setSlaveMap(CONTROLLERTOTM, blockStateResourceLocationHashMap.clone());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onAdd(IForgeRegistryInternal owner, RegistryManager stage, int id, TimeMachine obj, @Nullable TimeMachine oldObj) {
            blockStateResourceLocationHashMap = (HashMap<BlockState, ResourceLocation>)owner.getSlaveMap(CONTROLLERTOTM, HashMap.class);
            if (obj instanceof CreativeTimeMachine) return; // Special rule for the creative Time Machine
            if (!blockStateResourceLocationHashMap.containsValue(obj.getRegistryName())) {
                for(BlockState block:obj.getControllerBlocks()) {
                    if (!blockStateResourceLocationHashMap.containsKey(block)) {
                        blockStateResourceLocationHashMap.put(block, obj.getRegistryName());
                    } else {
                        throw new RuntimeException(obj.getRegistryName()+" tryed to register with controller block "+block.toString()+", but it is already registered to "+ GameRegistry.findRegistry(Block.class).getValue(blockStateResourceLocationHashMap.get(block)).toString());
                    }
                }
            }
        }
    }

    public static class TimeMachineUpgradesCallbacks implements IForgeRegistry.CreateCallback<TimeMachineUpgrade> {

        @Override
        public void onCreate(IForgeRegistryInternal<TimeMachineUpgrade> owner, RegistryManager stage) {
            HashMap<TimeMachineUpgrade, TimeMachineUpgradeBlock[]> upgradetoblockhm = new HashMap<>();
            owner.setSlaveMap(UPGRADETOBLOCK, upgradetoblockhm);
        }
    }
}
