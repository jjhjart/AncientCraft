package com.jjhjart.ancientcraft.client.gui;

import com.jjhjart.ancientcraft.init.ModPacketHandler;
import com.jjhjart.ancientcraft.init.ModRegistries;
import com.jjhjart.ancientcraft.init.ModTimeLines;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.api.timemachine.exception.IncompatibleTimeMachineHooksException;
import com.jjhjart.ancientcraft.networking.DimensionTpPKT;
import com.jjhjart.ancientcraft.world.dimension.TimeLine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.RegisterDimensionsEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class TimeMachineScreen extends Screen {

    private final UUID[] additionalEntities;
    private PlayerEntity player;
    private TimeMachine tm;
    private BlockPos pos;
    private Direction side;

    public TimeMachineScreen(PlayerEntity player, TimeMachine tm, BlockPos pos, Direction side, UUID... additionalEntities){
        super(new StringTextComponent("TITLE PLACEHOLDER"));
        this.player = player;
        try {
            this.tm = tm.hook(player.world, pos, side);
        } catch (IncompatibleTimeMachineHooksException e) {
            throw new RuntimeException("Time Machine GUI opened with invalid upgrade configuration");
        }
        this.pos = pos;
        this.side = side;
        this.additionalEntities = additionalEntities;
    }

    @Override
    public void init() {
        ArrayList<com.jjhjart.ancientcraft.api.dimension.TimeLine> tls = new ArrayList<>(ModRegistries.TIME_LINES.getValues());
        tls.sort(Comparator.comparingInt(com.jjhjart.ancientcraft.api.dimension.TimeLine::getMinTier));
        int buttoncount = tls.size();
        for(int id = 0; id < tls.size(); id++) {
            addButton(new TimeLineButton(this.width / 2 -100, (this.height / (buttoncount+1))*(id+1), (TimeLine) tls.get(id), this));
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


    protected class TimeLineButton extends Button {

        TimeLine tl;
        TimeMachineScreen screen;

        TimeLineButton(int x, int y, TimeLine tl, TimeMachineScreen screen) {
            super(x, y, 200, 20, I18n.format(String.format("gui.tm.%s.%s", tl.getRegistryName().getNamespace(), tl.getRegistryName().getPath())), TimeMachineScreen::clickHandler);
            this.screen = screen;
            this.tl = tl;
            this.active = tl.getMinTier() <= tm.getTier();
        }
    }

    private static void clickHandler(Button button) {
        TimeLineButton b = (TimeLineButton) button;
        Minecraft.getInstance().displayGuiScreen(null);
        if (b.tl.getDimension() != b.screen.player.dimension && TimeLine.isValidTimeLine(b.screen.player.world)) {
            ModTimeLines.registerDimension(new RegisterDimensionsEvent(Collections.EMPTY_MAP));
            ModPacketHandler.CHANNEL.sendToServer(new DimensionTpPKT(b.tl, b.screen.tm, b.screen.pos, b.screen.side, b.screen.additionalEntities));
        } else {
            b.screen.player.sendMessage(new TranslationTextComponent("You are already in that dimension!"));
        }
    }
}