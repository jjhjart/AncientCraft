package com.jjhjart.ancientcraft.util;

import com.jjhjart.ancientcraft.init.ModConfig;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import com.jjhjart.ancientcraft.api.timemachine.upgrade.TimeMachineUpgrade;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class TimeMachineUtils {

    // Time Machine checking stuff

    public static Check check(TimeMachine tm, World world, PlayerEntity player, BlockPos pos, Direction side) {
        for(Check check: Check.values()) {
            switch (check) {
                case BUILT:
                    if (!tm.isBuilt(world, pos, side)) return check;
                    break;
                case COOLED_DOWN:
                    if (!tm.isCooledDown(world, pos, side)) return check;
                    break;
                case PALYER_INSIDE:
                    if (!tm.isPlayerInside(world, pos, side, player)) return check;
                    break;
                case OVERLOADED:
                    if (tm.isOverloaded(world, pos, side)) return check;
                    break;
            }
        }
        return null;
    }

    public static boolean serverCheck(MinecraftServer server, TimeMachine tm, World world, ServerPlayerEntity player, BlockPos pos, Direction side) {
        Check error = check(tm, world, player, pos, side);
        if (error == null) {
            return true;
        } else if (ModConfig.COMMON.enableCheaterReports.get()){
            Arrays.stream(server.getPlayerList().getOppedPlayers().getKeys())
                    .map(op -> server.getPlayerList().getPlayerByUsername(op))
                    .forEach(op -> {
                        if (op != null)
                        op.sendStatusMessage(error.getCheaterReport(player), false);
                    });
            return false;
        } else return false;
    }

    public enum Check {
        BUILT("timetravelmod.error.built.client", "timetravelmod.error.built.server"),
        COOLED_DOWN("timetravelmod.error.cooled_down.client", "timetravelmod.error.cooled_down.server"),
        PALYER_INSIDE("timetravelmod.error.player_inside.client", "timetravelmod.error.player_inside.server"),
        OVERLOADED("timetravelmod.error.overloaded.client", "timetravelmod.error.overloaded.server"),
        UNREACHABLE_DIM("", "timetravelmod.error.unreachable_dim"),
        ENTITIES_ESCAPED("timetravelmod.error.entitiesescaped", "");

        private final String clientError;
        private final String cheatError;

        Check(String clientError, String cheatError) {
            this.clientError = clientError;
            this.cheatError = cheatError;
        }

        public TranslationTextComponent getClientError() {
            return new TranslationTextComponent(this.clientError);
        }

        public TranslationTextComponent getCheaterReport(ServerPlayerEntity cheater) {
            return new TranslationTextComponent("timetravelmod.cheater_report", cheater.getDisplayName(), new TranslationTextComponent(this.cheatError), getBanButton(cheater));
        }

        private static TranslationTextComponent getBanButton(ServerPlayerEntity player) {
            TranslationTextComponent textComponent = new TranslationTextComponent("timetravelmod.ban");
            textComponent.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ban "+player.getName().getUnformattedComponentText())).setColor(TextFormatting.RED));
            return textComponent;
        }
    }

    // Time Machine uncompatibility listing stuff

    public static TranslationTextComponent concatUncompatibilities(ArrayList<TimeMachineUpgrade> upgrades) {
        if (upgrades.size() != 1) {
            String separator = upgrades.size() > 2 ? "timetravelmod.generic.comma" : "timetravelmod.generic.and";
            return new TranslationTextComponent(separator, upgrades.remove(0).getName(), concatUncompatibilities(upgrades));
        } else return upgrades.remove(0).getName();
    }
}
