package com.jjhjart.ancientcraft.entites;

import com.jjhjart.ancientcraft.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractRomanRangedEntity extends AbstractRomanEntity implements IRangedAttackMob {
    private final RangedBowAttackGoal<AbstractSkeletonEntity> aiArrowAttack = new RangedBowAttackGoal(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal aiAttackOnCollide = new MeleeAttackGoal(this, 1.2D, false) {
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
            AbstractRomanRangedEntity.this.setAggroed(false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            super.startExecuting();
            AbstractRomanRangedEntity.this.setAggroed(true);
        }
    };
    public AbstractRomanRangedEntity(EntityType<? extends AbstractRomanRangedEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCombatTask();
    }

/*/
    //private final RangedBowGoalMod<AbstractHumanRangedEntity> aiArrowAttack = new RangedBowGoalMod(this, 1.0D, 20, 15.0F);
    @SuppressWarnings("unused")
    //private final RangedCrossbowGoalMod<AbstractFriendlyRangedEntity> aiBoltAttack = new RangedCrossbowGoalMod(this, 1, 2);
    private final MeleeAttackGoal aiAttackOnCollide = new MeleeAttackGoal(this, 1.2D, false) {
        public void resetTask() {
            super.resetTask();
            AbstractHumanRangedEntity.this.setAggroed(false);
        }

        public void startExecuting() {
            super.startExecuting();
            AbstractHumanRangedEntity.this.setAggroed(true);
        }
    };
    /*/

    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F));
        //this.goalSelector.addGoal(1, new RangedCrossbowGoalMod<>(this, 1.0D, 8.0F));
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


    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d); // Max health is a bit more than a regular bat which is 6
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0d); // Possibly drain blood instead of dealing damage? or give possible rabies?
        //this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0d); //
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.GALEA.get()));
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.LORICA_SEGMENTATA.get()));
        this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.BALTEUS.get()));
        this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.CALIGE.get()));
        if (this.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            //this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
            this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
        }

        return spawnDataIn;
    }
    public void setCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.goalSelector.removeGoal(this.aiAttackOnCollide);
            this.goalSelector.removeGoal(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItem(ProjectileHelper.getHandWith(this, Items.BOW));
            if (itemstack.getItem() instanceof net.minecraft.item.BowItem) {
                int i = 20;
                if (this.world.getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.aiArrowAttack.setAttackCooldown(i);
                this.goalSelector.addGoal(4, this.aiArrowAttack);
            } else {
                this.goalSelector.addGoal(4, this.aiAttackOnCollide);
            }

        }
    }


    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        ItemStack itemstack = this.findAmmo(this.getHeldItem(ProjectileHelper.getHandWith(this, Items.BOW)));
        AbstractArrowEntity abstractarrowentity = this.fireArrow(itemstack, distanceFactor);
        if (this.getHeldItemMainhand().getItem() instanceof net.minecraft.item.BowItem)
            abstractarrowentity = ((net.minecraft.item.BowItem) this.getHeldItemMainhand().getItem()).customeArrow(abstractarrowentity);
        double d0 = target.getPosX() - this.getPosX();
        double d1 = target.getPosYHeight(0.3333333333333333D) - abstractarrowentity.getPosY();
        double d2 = target.getPosZ() - this.getPosZ();
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(abstractarrowentity);
    }

    /**
     * Fires an arrow
     */
    public AbstractArrowEntity fireArrow(ItemStack arrowStack, float distanceFactor) {
        return ProjectileHelper.fireArrow(this, arrowStack, distanceFactor);
    }

    public boolean func_230280_a_(ShootableItem p_230280_1_) {
        return p_230280_1_ == Items.BOW;
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

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

}

