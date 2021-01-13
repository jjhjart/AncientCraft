package com.jjhjart.ancientcraft.entites;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * A Wild Boar entity.
 * Literally just a pig (with a different texture).
 * TODO: Will have more stuff added to it soon. (Will charge at player
 *
 * @author Cadiboo
 */
public class BoarEntity extends MonsterEntity {

    @SuppressWarnings("unchecked")
    public BoarEntity(final EntityType<? extends BoarEntity> entityType, final World world) {
        super(entityType, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.1D, 32.0F));

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, MonsterEntity.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LegionaryEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractRomanEntity.class, true, false));

    }
    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        final double baseSpeed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
        final double baseHealth = this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        // Multiply base health and base speed by one and a half
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * 1.5D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(baseHealth * 1.5D);

    }

    //@Override
    public BoarEntity createChild(final AgeableEntity parent) {
        // Use getType to support overrides in subclasses
        return (BoarEntity) getType().create(this.world);
    }
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
