package com.jjhjart.ancientcraft.entites;

import com.jjhjart.ancientcraft.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class KnightEntity extends AbstractBritianEntity {

    protected final Random rand = new Random();

    public KnightEntity(EntityType<? extends AbstractBritianEntity> type, World worldIn) {
        super(type, worldIn);
        // TODO Auto-generated constructor stub
    }

    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 5, false, false, (p_234199_0_) -> {
                    return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof AbstractBritianEntity) && !(p_234199_0_ instanceof AbstractBritianRangedEntity);
                }));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.CLOSE_HELM.get()));
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.BREAST_PLATE.get()));
        this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.CUISSE.get()));
        this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.SABATONS.get()));
        if (this.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
            this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
        }

        return spawnDataIn;
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //if ((double)this.rand.nextFloat() > 0.9D) {
        //int i = this.rand.nextInt(16);
        //if (i < 10) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.RITTERSCHWERT.get()));
        this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.SCUTCHEON.get()));
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