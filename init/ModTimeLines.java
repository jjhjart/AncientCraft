package com.jjhjart.ancientcraft.init;


import com.jjhjart.ancientcraft.api.dimension.TimeLine;
import com.jjhjart.ancientcraft.world.dimension.MedievalDimension;
import com.jjhjart.ancientcraft.world.dimension.RomanDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiFunction;



import static com.jjhjart.ancientcraft.NewVoid.MOD_ID;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModTimeLines {

    public static final TimeLine PRESENT = TimeLine.getNew(0, null).setRegistryName(MOD_ID, "present");
    public static final TimeLine ROMAN_TIMES = TimeLine.getNew(1, new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return RomanDimension::new;
        }
    }.setRegistryName(new ResourceLocation(MOD_ID, "roman_times"))).setRegistryName(MOD_ID, "roman_times");

    public static final TimeLine MEDIEVAL_TIMES = TimeLine.getNew(1, new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return MedievalDimension::new;
        }
    }.setRegistryName(new ResourceLocation(MOD_ID, "medieval_times"))).setRegistryName(MOD_ID, "medieval_times");

    @SubscribeEvent
    public static void registerTimeLines(RegistryEvent.Register<TimeLine> event) {
        event.getRegistry().registerAll(
                PRESENT,
                ROMAN_TIMES,
                MEDIEVAL_TIMES
        );
    }

    @SubscribeEvent
    public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().registerAll(
                ROMAN_TIMES.getModDimension()
        );
    }

    public static void registerDimension (RegisterDimensionsEvent event) {
        if (DimensionType.byName(ROMAN_TIMES.getRegistryName()) == null) DimensionManager.registerDimension(ROMAN_TIMES.getRegistryName(), ROMAN_TIMES.getModDimension(), null, true);
        if (DimensionType.byName(MEDIEVAL_TIMES.getRegistryName()) == null) DimensionManager.registerDimension(MEDIEVAL_TIMES.getRegistryName(), MEDIEVAL_TIMES.getModDimension(), null, true);
    }
}

