package com.jjhjart.ancientcraft.entites;

import com.jjhjart.ancientcraft.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Random;

public class GladiatorEntity extends AbstractRomanEntity {

    protected final Random rand = new Random();

    public GladiatorEntity(EntityType<? extends AbstractRomanEntity> type, World worldIn) {
        super(type, worldIn);
        // TODO Auto-generated constructor stub
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //if ((double)this.rand.nextFloat() > 0.9D) {
        //int i = this.rand.nextInt(16);
        //if (i < 10) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.GLADIUS.get()));
        //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.SCUTUM.get()));
        //} else {
        //this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.FISHING_ROD));
        //}
    }



    protected boolean shouldExchangeEquipment(ItemStack candidate, ItemStack existing, EquipmentSlotType slotTypeIn) {
        if (existing.getItem() == Items.NAUTILUS_SHELL) {
            return false;
        } else if (existing.getItem() == ModItems.PILUM.get()) {
            if (candidate.getItem() == ModItems.PILUM.get()) {
                return candidate.getDamage() < existing.getDamage();
            } else {
                return false;
            }
        } else {
            return candidate.getItem() == ModItems.PILUM.get() ? true : super.shouldExchangeEquipment(candidate, existing, slotTypeIn);
        }
    }
}