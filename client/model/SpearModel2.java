package com.jjhjart.ancientcraft.client.model;

import com.jjhjart.ancientcraft.entites.SpearEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SpearModel2<T extends SpearEntity> extends EntityModel<T> {
    private final ModelRenderer trident;
    private final ModelRenderer rot;
    private final ModelRenderer rot2;
    private final ModelRenderer rot3;
    private final ModelRenderer rot4;
    private final ModelRenderer inf;

    public SpearModel2() {
        textureWidth = 32;
        textureHeight = 32;

        trident = new ModelRenderer(this);
        trident.setRotationPoint(8.0F, 24.0F, -8.0F);
        trident.setTextureOffset(0, 0).addBox(-8.5F, -18.0F, 7.5F, 1.0F, 18.0F, 1.0F, 0.0F, false);
        trident.setTextureOffset(0, 2).addBox(-8.25F, -19.0F, 7.75F, 0.0F, 1.0F, 0.0F, 0.0F, false);
        trident.setTextureOffset(5, 0).addBox(-8.2F, -32.0F, 7.8F, 0.0F, 14.0F, 0.0F, 0.0F, false);

        rot = new ModelRenderer(this);
        rot.setRotationPoint(0.0F, -36.0F, 0.0F);
        trident.addChild(rot);
        rot.setTextureOffset(5, 0).addBox(-8.2507F, 4.1955F, 7.8F, 0.0F, 2.0F, 0.0F, 0.0F, false);

        rot2 = new ModelRenderer(this);
        rot2.setRotationPoint(0.0F, -36.0F, 0.0F);
        trident.addChild(rot2);
        rot2.setTextureOffset(5, 0).addBox(-8.15F, 4.1955F, 7.8F, 0.0F, 2.0F, 0.0F, 0.0F, false);

        rot3 = new ModelRenderer(this);
        rot3.setRotationPoint(0.0F, -36.0F, 0.0F);
        trident.addChild(rot3);
        rot3.setTextureOffset(5, 0).addBox(-8.0F, 6.0F, 7.8F, 0.0F, 2.0F, 0.0F, 0.0F, false);

        rot4 = new ModelRenderer(this);
        rot4.setRotationPoint(0.0F, -36.0F, 0.0F);
        trident.addChild(rot4);
        rot4.setTextureOffset(5, 0).addBox(-8.4F, 6.0F, 7.8F, 0.0F, 2.0F, 0.0F, 0.0F, false);

        inf = new ModelRenderer(this);
        inf.setRotationPoint(-8.0F, -8.0F, 8.0F);
        trident.addChild(inf);
        inf.setTextureOffset(5, 4).addBox(-0.5F, -23.0F, -0.2F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        inf.setTextureOffset(5, 0).addBox(-0.75F, -22.135F, -0.2F, 1.0F, 0.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        trident.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

