package com.jjhjart.ancientcraft.util;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.init.ModDimensions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NewVoid.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {

    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        if(DimensionType.byName(NewVoid.ROMAN_DIM_TYPE) == null) {
            DimensionManager.registerDimension(NewVoid.ROMAN_DIM_TYPE, ModDimensions.ROMAN_DIM.get(), null, true);

        }
        if(DimensionType.byName(NewVoid.MEDIEVAL_DIM_TYPE) == null) {
            DimensionManager.registerDimension(NewVoid.MEDIEVAL_DIM_TYPE, ModDimensions.MEDIEVAL_DIM.get(), null, true);

        }
        NewVoid.LOGGER.info("Dimensions Registered");
    }
}
