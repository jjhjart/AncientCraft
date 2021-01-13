package com.jjhjart.ancientcraft.client.render;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.client.model.AbstractHumanModel;
import com.jjhjart.ancientcraft.entites.AbstractBritianEntity;
import com.jjhjart.ancientcraft.entites.AbstractRomanEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class AbstractHumanRendererB extends BipedRenderer<AbstractBritianEntity, AbstractHumanModel<AbstractBritianEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(NewVoid.MOD_ID,
            "textures/entity/knight.png");

    @SuppressWarnings("rawtypes")
    public AbstractHumanRendererB(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AbstractHumanModel<>(), 0.5f);
        this.addLayer(new BipedArmorLayer<>(this, new AbstractHumanModel<>(0.5F, true), new AbstractHumanModel<>(1f, true)));
    }

    public ResourceLocation getEntityTexture(AbstractBritianEntity entity) {
        return TEXTURE;
    };

    public void renderRightArm(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn,
                               AbstractClientPlayerEntity playerIn) {
        this.renderItem(matrixStackIn, bufferIn, combinedLightIn, playerIn, (this.entityModel).bipedRightArm,
                (this.entityModel).bipedRightArmwear);
    }

    private void renderItem(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn,
                            AbstractClientPlayerEntity playerIn, ModelRenderer bipedRightArm, ModelRenderer bipedRightArmwear) {

    }

    public void renderLeftArm(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn,
                              AbstractClientPlayerEntity playerIn) {
        this.renderItem(matrixStackIn, bufferIn, combinedLightIn, playerIn, (this.entityModel).bipedLeftArm,
                (this.entityModel).bipedLeftArmwear);
    }
}
