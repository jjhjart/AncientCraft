package com.jjhjart.ancientcraft.entites;

import javax.annotation.Nullable;
import com.jjhjart.ancientcraft.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class LegionaryEntity extends ZombieEntity implements IRangedAttackMob {
    private boolean swimmingUp;
    protected final SwimmerPathNavigator waterNavigator;
    protected final GroundPathNavigator groundNavigator;

    public LegionaryEntity(EntityType<? extends LegionaryEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.moveController = new LegionaryEntity.MoveHelperController(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
        this.groundNavigator = new GroundPathNavigator(this, worldIn);
    }

    protected void applyEntityAI() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, MonsterEntity.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 5, false, false, (p_234199_0_) -> { return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof AbstractRomanEntity) && !(p_234199_0_ instanceof AbstractRomanRangedEntity) && !(p_234199_0_ instanceof LegionaryEntity); }));
        this.goalSelector.addGoal(2, new LegionaryEntity.TridentAttackGoal(this, 1.0D, 40, 10.0F));
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.GALEA.get()));
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.LORICA_SEGMENTATA.get()));
        this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.BALTEUS.get()));
        this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.CALIGE.get()));
        if (this.rand.nextFloat() < 0.03F) {
            this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.GLADIUS.get()));
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
    private static boolean func_223333_a(IWorld p_223333_0_, BlockPos p_223333_1_) {
        return p_223333_1_.getY() < p_223333_0_.getSeaLevel() - 5;
    }

    protected boolean canBreakDoors() {
        return false;
    }



    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //if ((double)this.rand.nextFloat() > 0.9D) {
        this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.SCUTUM.get()));
        int i = this.rand.nextInt(16);
        if (i < 10) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.PILUM.get()));
        } else {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.GLADIUS.get()));
            }
        }



    protected boolean shouldExchangeEquipment(ItemStack candidate, ItemStack existing, EquipmentSlotType slotTypeIn) {
        if (existing.getItem() == ModItems.GLADIUS.get()) {
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


    public boolean isNotColliding(IWorldReader worldIn) {
        return worldIn.checkNoEntityCollision(this);
    }

    public boolean shouldAttack(@Nullable LivingEntity p_204714_1_) {
        return true;
    }

    public boolean isPushedByWater() {
        return true;
    }

    private boolean func_204715_dF() {
        if (this.swimmingUp) {
            return true;
        } else {
            LivingEntity livingentity = this.getAttackTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    public void travel(Vec3d p_213352_1_) {
        if (this.isServerWorld() && this.isInWater() && this.func_204715_dF()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
        } else {
            super.travel(p_213352_1_);
        }

    }


    protected boolean isCloseToPathTarget() {
        Path path = this.getNavigator().getPath();
        if (path != null) {
            BlockPos blockpos = path.func_224770_k();
            if (blockpos != null) {
                double d0 = this.getDistanceSq((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
                if (d0 < 4.0D) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        SpearEntity spearentity = new SpearEntity(this.world, this, new ItemStack(ModItems.PILUM.get()));
        double d0 = target.getPosX() - this.getPosX();
        double d1 = target.getPosYHeight(0.3333333333333333D) - spearentity.getPosY();
        double d2 = target.getPosZ() - this.getPosZ();
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        spearentity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(spearentity);
    }

    static class MoveHelperController extends MovementController {
        private final LegionaryEntity guard;

        public MoveHelperController(LegionaryEntity p_i48909_1_) {
            super(p_i48909_1_);
            this.guard = p_i48909_1_;
        }

        public void tick() {
            LivingEntity livingentity = this.guard.getAttackTarget();
            if (this.guard.func_204715_dF() && this.guard.isInWater()) {
                if (livingentity != null && livingentity.getPosY() > this.guard.getPosY() || this.guard.swimmingUp) {
                    this.guard.setMotion(this.guard.getMotion().add(0.0D, 0.002D, 0.0D));
                }

                if (this.action != MovementController.Action.MOVE_TO || this.guard.getNavigator().noPath()) {
                    this.guard.setAIMoveSpeed(0.0F);
                    return;
                }

                double d0 = this.posX - this.guard.getPosX();
                double d1 = this.posY - this.guard.getPosY();
                double d2 = this.posZ - this.guard.getPosZ();
                double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.guard.rotationYaw = this.limitAngle(this.guard.rotationYaw, f, 90.0F);
                this.guard.renderYawOffset = this.guard.rotationYaw;
                float f1 = (float)(this.speed * this.guard.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                float f2 = MathHelper.lerp(0.125F, this.guard.getAIMoveSpeed(), f1);
                this.guard.setAIMoveSpeed(f2);
                this.guard.setMotion(this.guard.getMotion().add((double)f2 * d0 * 0.005D, (double)f2 * d1 * 0.1D, (double)f2 * d2 * 0.005D));
            } else {
                if (!this.guard.onGround) {
                    this.guard.setMotion(this.guard.getMotion().add(0.0D, -0.008D, 0.0D));
                }

                super.tick();
            }

        }
    }




    static class TridentAttackGoal extends RangedAttackGoal {
        private final LegionaryEntity field_204728_a;

        public TridentAttackGoal(IRangedAttackMob p_i48907_1_, double p_i48907_2_, int p_i48907_4_, float p_i48907_5_) {
            super(p_i48907_1_, p_i48907_2_, p_i48907_4_, p_i48907_5_);
            this.field_204728_a = (LegionaryEntity)p_i48907_1_;
        }



        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return super.shouldExecute() && this.field_204728_a.getHeldItemMainhand().getItem() == ModItems.PILUM.get();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            super.startExecuting();
            this.field_204728_a.setAggroed(true);
            this.field_204728_a.setActiveHand(Hand.MAIN_HAND);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
            this.field_204728_a.resetActiveHand();
            this.field_204728_a.setAggroed(false);
        }
    }
    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
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

    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }
}