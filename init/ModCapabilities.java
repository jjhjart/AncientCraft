package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.api.dimension.Corruption;
import com.jjhjart.ancientcraft.world.capability.CorruptionCapabilityProvider;
import com.jjhjart.ancientcraft.world.dimension.TimeLine;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {

    public static void register() {
        CapabilityManager.INSTANCE.register(Corruption.class, new Capability.IStorage<Corruption>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<Corruption> capability, Corruption instance, Direction side) {
                return IntNBT.valueOf(instance.getCorruptionLevel());
            }

            @Override
            public void readNBT(Capability<Corruption> capability, Corruption instance, Direction side, INBT nbt) {
                ((com.jjhjart.ancientcraft.world.dimension.Corruption) instance).setCorruptionLevel(((IntNBT)nbt).getInt());
            }
        }, com.jjhjart.ancientcraft.world.dimension.Corruption::new);
    }

    @SubscribeEvent
    public static void attachToWorld(AttachCapabilitiesEvent<World> event) {
        if (!TimeLine.isValidTimeLine(event.getObject())) return;
        event.addCapability(new ResourceLocation(NewVoid.MOD_ID, "corruption"), new CorruptionCapabilityProvider(event.getObject()));
    }
}
