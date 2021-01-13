package com.jjhjart.ancientcraft.util;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.client.render.*;
import com.jjhjart.ancientcraft.client.render.KnightRenderer;
import com.jjhjart.ancientcraft.client.render.layer.HousecarlRenderer;
import com.jjhjart.ancientcraft.init.ModBlocks;
import com.jjhjart.ancientcraft.init.ModEntityTypes;
import com.jjhjart.ancientcraft.items.ModSpawnEggItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NewVoid.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.END_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PINE_SAPLING.get(), RenderType.getCutout());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LEGIONARY.get(), GuardRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PILUM.get(), SpearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GLADIATOR.get(), LegionaryRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ARCHER.get(), ArcherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KNIGHT.get(), KnightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HOUSECARL.get(), HousecarlRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BOWMAN.get(), BowmanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BOAR.get(), BoarRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EGYPT.get(), EgyptRenderer::new);


    }




    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItems.initSpawneggs();
    }
}
