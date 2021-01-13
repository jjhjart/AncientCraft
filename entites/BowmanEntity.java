package com.jjhjart.ancientcraft.entites;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Random;

public class BowmanEntity extends AbstractBritianRangedEntity {

    protected final Random rand = new Random();

    public BowmanEntity(EntityType<? extends AbstractBritianRangedEntity> type, World worldIn) {
        super(type, worldIn);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));

        if ((double) this.rand.nextFloat() > 0.35D) {
            int i2 = this.rand.nextInt(20);
            if (i2 < 12) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
            } else {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
            }
        }

    }

}
