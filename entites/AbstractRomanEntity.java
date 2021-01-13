package com.jjhjart.ancientcraft.entites;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractRomanEntity extends ZombieEntity {

    public AbstractRomanEntity(EntityType<? extends AbstractRomanEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 5, false, false, (p_234199_0_) -> {
                    return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof AbstractRomanEntity) && !(p_234199_0_ instanceof AbstractRomanRangedEntity) && !(p_234199_0_ instanceof LegionaryEntity);
                }));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        //this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.GALEA.get()));
        //this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.LORICA_SEGMENTATA.get()));
        //this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.BELTEA.get()));
        //this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.CALIGE.get()));
        if (this.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
            this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
        }

        return spawnDataIn;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d); // Max health is a bit more than a regular bat which is 6
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0d); // Possibly drain blood instead of dealing damage? or give possible rabies?
        //this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0d); //
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PLAYER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_COW_STEP;
    }


    public int decreaseAirSupply(int air) {
        return air;
    }
    /**
     * Returns the volume for the sounds this mob makes.
     */
    public float getSoundVolume() {
        return 0.4F;
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }
}
