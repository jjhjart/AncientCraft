package com.jjhjart.ancientcraft.items;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.init.ModTimeMachines;
import com.jjhjart.ancientcraft.api.timemachine.TimeMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CreativeTimeMachineItem extends Item {

    private TimeMachine timeMachine = ModTimeMachines.CREATIVE;

    public CreativeTimeMachineItem() {
        super(new Properties()
                .maxStackSize(1)
                .group(NewVoid.TAB)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) this.timeMachine.run(worldIn, playerIn, playerIn.getPosition(), Direction.NORTH);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
