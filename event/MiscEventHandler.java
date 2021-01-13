package com.jjhjart.ancientcraft.event;

import com.jjhjart.ancientcraft.ModConfig;
import com.jjhjart.ancientcraft.NewVoid;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "ancientcraft")
public class MiscEventHandler {
    @SubscribeEvent
    public static void worldStart(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModConfig.COMMON.enableUpdatePromos.get()) {
            if (event.getPlayer() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                VersionChecker.CheckResult result = VersionChecker.getResult(ModList.get().getModContainerById(NewVoid.MOD_ID).get().getModInfo());
                if (result.status == VersionChecker.Status.OUTDATED) {
                    player.sendMessage(new TranslationTextComponent("chat.ttm.outdated"));
                }
            }
        }
    }
}
