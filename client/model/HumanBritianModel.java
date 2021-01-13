package com.jjhjart.ancientcraft.client.model;

import com.jjhjart.ancientcraft.entites.AbstractBritianEntity;
import com.jjhjart.ancientcraft.entites.AbstractRomanEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanBritianModel<T extends AbstractBritianEntity> extends AbstractHumanBritianModel<T> {
    public HumanBritianModel(float modelSize, boolean p_i1168_2_) {
        this(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }

    protected HumanBritianModel(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
        super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
    }

    public boolean func_212850_a_(T p_212850_1_) {
        return p_212850_1_.isAggressive();
    }
}
