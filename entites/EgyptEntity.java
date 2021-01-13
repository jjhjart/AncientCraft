package com.jjhjart.ancientcraft.entites;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EgyptEntity extends AbstractRomanEntity {

    protected final Random rand = new Random();

    public EgyptEntity(EntityType<? extends AbstractRomanEntity> type, World worldIn) {
        super(type, worldIn);
        // TODO Auto-generated constructor stub
    }
    //protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
    //    //if ((double)this.rand.nextFloat() > 0.9D) {
    //    //int i = this.rand.nextInt(16);
    //    //if (i < 10) {
    //    this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.GLADIUS.get()));
    //    //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.SCUTUM.get()));
    //    //} else {
    //    //this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.FISHING_ROD));
    //    //}
    //}


    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        //this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.EGYPT_HELMET.get()));
        //this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.LORICA_SEGMENTATA.get()));
        //this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.BELTEA.get()));
        //this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.CALIGE.get()));
        if (this.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
            this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
        }

        return spawnDataIn;
    }

    //protected boolean shouldExchangeEquipment(ItemStack candidate, ItemStack existing, EquipmentSlotType slotTypeIn) {
    //    if (existing.getItem() == Items.NAUTILUS_SHELL) {
    //        return false;
    //    } else if (existing.getItem() == ModItems.PILUM.get()) {
    //        if (candidate.getItem() == ModItems.PILUM.get()) {
    //            return candidate.getDamage() < existing.getDamage();
    //        } else {
    //            return false;
    //        }
    //    } else {
    //        return candidate.getItem() == ModItems.PILUM.get() ? true : super.shouldExchangeEquipment(candidate, existing, slotTypeIn);
    //    }
    //}
}