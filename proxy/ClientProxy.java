package com.jjhjart.ancientcraft.proxy;

import com.jjhjart.ancientcraft.init.ModBlocks;
import com.jjhjart.ancientcraft.init.ModRegistries;
import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.api.timemachine.exception.IncompatibleTimeMachineHooksException;
import com.jjhjart.ancientcraft.client.SoundManager;
import com.jjhjart.ancientcraft.client.gui.TimeMachineScreen;
import com.jjhjart.ancientcraft.networking.OpenTmGuiPKT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class ClientProxy extends CommonProxy {

    //private EngineerBookScreen generatedEngineerBook;

    @Override
    public void displayTMGuiScreen(PlayerEntity player, TimeMachine tm, BlockPos pos, Direction side, UUID... additionalEntities) {
        Minecraft.getInstance().deferTask(()->Minecraft.getInstance().displayGuiScreen(new TimeMachineScreen(player, tm, pos, side, additionalEntities)));
    }

    //@Override
    //public void displayEngineerBookGuiScreen(PlayerEntity player) {
    //    if (generatedEngineerBook == null) generatedEngineerBook = new EngineerBookScreen(ModRegistries.TIME_MACHINES.getValues());
    //    Minecraft.getInstance().deferTask(()->Minecraft.getInstance().displayGuiScreen(generatedEngineerBook));
    //}

    @Override
    public void handleOpenTMGUI(OpenTmGuiPKT message, NetworkEvent.Context ctx) {
        PlayerEntity player = Minecraft.getInstance().player;
        try {
            NewVoid.PROXY.displayTMGuiScreen(player, message.tm.hook(player.world, message.pos, message.side), message.pos, message.side, message.additionalEntities.toArray(new UUID[]{}));
        } catch (IncompatibleTimeMachineHooksException e) {
            throw new RuntimeException("Time Machine GUI opened with invalid upgrade configuration");
        }
    }

    @Override
    public void modConstructor(NewVoid instance) {
        super.modConstructor(instance);
        // Register ColorHandlerEvent#Block
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBlocks::registerBlockColor);
        // Register PlaySoundEvent
        MinecraftForge.EVENT_BUS.addListener(SoundManager::onPlaySound);
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        //RenderTypeLookup.setRenderLayer(ModBlocks.GUNPOWDER_WIRE.get(), RenderType.getCutout());
    }
}
