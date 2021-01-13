package com.jjhjart.ancientcraft.client;

import com.jjhjart.ancientcraft.ModConfig;
import com.jjhjart.ancientcraft.init.ModSounds;
import com.jjhjart.ancientcraft.world.dimension.RomanDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;

public class SoundManager {

    private static final ISound OLDWEST_MUSIC = SimpleSound.music(ModSounds.OLDWEST_MUSIC.get());

    public static void onPlaySound(PlaySoundEvent event) {
        if (ModConfig.CLIENT.enableTimeLineMusic.get()) {
            if (Minecraft.getInstance().player != null &&
                    Minecraft.getInstance().player.world.getDimension() instanceof RomanDimension &&
                    event.getSound().getCategory() == SoundCategory.MUSIC) {
                if (Minecraft.getInstance().getSoundHandler().isPlaying(OLDWEST_MUSIC)) {
                    event.setResultSound(null);
                } else {
                    event.setResultSound(OLDWEST_MUSIC);
                }
            }
        }
    }
}
