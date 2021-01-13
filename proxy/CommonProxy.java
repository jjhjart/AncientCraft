package com.jjhjart.ancientcraft.proxy;

import com.jjhjart.ancientcraft.*;
import com.jjhjart.ancientcraft.ModConfig;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.init.*;
import com.jjhjart.ancientcraft.networking.OpenTmGuiPKT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.UUID;

import static com.jjhjart.ancientcraft.ModConfig.clientSpec;
import static com.jjhjart.ancientcraft.ModConfig.commonSpec;

public class CommonProxy implements IProxy {

    @Override
    public void displayTMGuiScreen(PlayerEntity player, TimeMachine tm, BlockPos pos, Direction side, UUID... additionalEntities) {
        ModPacketHandler.CHANNEL.sendTo(new OpenTmGuiPKT(tm, pos, side, additionalEntities), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public void modConstructor(NewVoid instance) {
        // Register FMLCommonSetupEvent
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(instance::commonSetup);
        // Register FMLClientSetupEvent
        FMLJavaModLoadingContext.get().getModEventBus().addListener(NewVoid.PROXY::clientSetup);
        // Register RegisterDimensionsEvent
        MinecraftForge.EVENT_BUS.addListener(ModTimeLines::registerDimension);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(instance);

        // Register the DeferredRegisters


        // Register the client config
        ModLoadingContext.get().getActiveContainer().addConfig(new net.minecraftforge.fml.config.ModConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, clientSpec, ModLoadingContext.get().getActiveContainer()));
        // Register the common config
        ModLoadingContext.get().getActiveContainer().addConfig(new net.minecraftforge.fml.config.ModConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, commonSpec, ModLoadingContext.get().getActiveContainer()));
    }
}
