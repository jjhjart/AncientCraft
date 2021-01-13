package com.jjhjart.ancientcraft.client.model;

import com.jjhjart.ancientcraft.entites.LegionaryEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RomeModel<T extends LegionaryEntity> extends AbstractRomeModel<T> {
    public RomeModel(float modelSize, boolean p_i1168_2_) {
        this(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }

    protected RomeModel(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
        super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
    }

    public boolean func_212850_a_(T p_212850_1_) {
        return p_212850_1_.isAggressive();
    }
}
