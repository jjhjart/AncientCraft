package com.jjhjart.ancientcraft.client.model;

import com.jjhjart.ancientcraft.entites.AbstractBritianEntity;
import com.jjhjart.ancientcraft.entites.AbstractRomanEntity;
import com.jjhjart.ancientcraft.init.ModItems;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanKnightModel<T extends AbstractBritianEntity> extends HumanBritianModel<T> {
    public HumanKnightModel(float p_i48915_1_, float p_i48915_2_, int p_i48915_3_, int p_i48915_4_) {
        super(p_i48915_1_, p_i48915_2_, p_i48915_3_, p_i48915_4_);
        this.bipedRightArm = new ModelRenderer(this, 32, 48);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i48915_1_);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i48915_2_, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 16, 48);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i48915_1_);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + p_i48915_2_, 0.0F);
    }

    public HumanKnightModel(float p_i49398_1_, boolean p_i49398_2_) {
        super(p_i49398_1_, 0.0F, 64, p_i49398_2_ ? 32 : 64);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack itemstack = entityIn.getHeldItem(Hand.MAIN_HAND);
        if (itemstack.getItem() == ModItems.PILUM.get() && entityIn.isAggressive()) {
            if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
                this.rightArmPose = ArmPose.THROW_SPEAR;
            } else {
                this.leftArmPose = ArmPose.THROW_SPEAR;
            }
        }
        ItemStack itemstack2 = entityIn.getHeldItem(Hand.MAIN_HAND);
        if (itemstack2.getItem() instanceof net.minecraft.item.BowItem && entityIn.isAggressive()) {
            if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
        }

        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        //Items.SHIELD


    }

    //PlayerModel
    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (this.leftArmPose == ArmPose.THROW_SPEAR) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.3555555555555F - (float)Math.PI;
            this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.3555555555555F - (float)Math.PI;//0.35
            this.bipedRightArm.rotateAngleY = 0.0F;
        }
    }

}

