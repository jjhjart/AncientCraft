package com.jjhjart.ancientcraft.client.render.layer;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.entites.BoarEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;

/**
 * Copy of {@link SaddleLayer} with tweaks to make it work for WildBoarEntity.
 *
 * @author Cadiboo
 */
public class BoarSaddleLayer extends LayerRenderer<BoarEntity, PigModel<BoarEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(NewVoid.MOD_ID, "textures/entity/boar_saddle.png");
    private final PigModel<BoarEntity> pigModel = new PigModel<>(0.5F);

    public BoarSaddleLayer(IEntityRenderer<BoarEntity, PigModel<BoarEntity>> p_i50927_1_) {
        super(p_i50927_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, BoarEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        //if (entitylivingbaseIn.getSaddled()) {
            //this.getEntityModel().copyModelAttributesTo(this.pigModel);
            //this.pigModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            //this.pigModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            //IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
            //this.pigModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        //}
    }
}
