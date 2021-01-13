package com.jjhjart.ancientcraft.client.render;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.entites.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;

public class BoarRenderer extends MobRenderer<BoarEntity, PigModel<BoarEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(NewVoid.MOD_ID, "textures/entity/hog.png");


    public BoarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PigModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(BoarEntity entity) {
        return TEXTURE;
    }
}
