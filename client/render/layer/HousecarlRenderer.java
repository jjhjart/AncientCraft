package com.jjhjart.ancientcraft.client.render.layer;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.client.model.HumanKnightModel;
import com.jjhjart.ancientcraft.client.render.AbstractHumanBritianRenderer;
import com.jjhjart.ancientcraft.entites.AbstractRomanEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class HousecarlRenderer extends AbstractHumanBritianRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(NewVoid.MOD_ID, "textures/entity/knight.png");

    public HousecarlRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HumanKnightModel<>(0.0F, 0.0F, 64, 64), new HumanKnightModel<>(0.5F, true), new HumanKnightModel(1.0F, true));
        // TODO Auto-generated constructor stub
        //this.addLayer(new HeldItemLayer<>(this));
    }

    public ResourceLocation getEntityTexture(AbstractRomanEntity entity) {
        return TEXTURE;
    }
}
