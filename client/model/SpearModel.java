package com.jjhjart.ancientcraft.client.model;

import com.jjhjart.ancientcraft.NewVoid;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Rotations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpearModel extends Model {
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(NewVoid.MOD_ID,"textures/entity/pilum.png");
    private final ModelRenderer modelRenderer = new ModelRenderer(32, 32, 0, 6);

    public SpearModel() {
        //TridentModel
        //DrownedEntity
        /**
        super(RenderType::getEntitySolid);
        this.modelRenderer.addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, 0.0F);
        ModelRenderer modelrenderer = new ModelRenderer(32, 32, 4, 0);
        modelrenderer.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F);
        this.modelRenderer.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = new ModelRenderer(32, 32, 4, 3);
        modelrenderer1.addBox(-1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
        this.modelRenderer.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = new ModelRenderer(32, 32, 0, 0);
        modelrenderer2.addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.modelRenderer.addChild(modelrenderer2);
        ModelRenderer modelrenderer3 = new ModelRenderer(32, 32, 4, 3);
        modelrenderer3.mirror = true;
        modelrenderer3.addBox(0.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
        this.modelRenderer.addChild(modelrenderer3);
*/

        super(RenderType::getEntitySolid);
        ModelRenderer model1 = new ModelRenderer(32, 32, 0, 0);
        model1.setRotationPoint(8.0F, 24.0F, -8.0F);
        model1.addBox(-8.5F, -18.0F, 7.5F, 1.0F, 18.0F, 1.0F, 0.0F, false);
        this.modelRenderer.addChild(model1);
        ModelRenderer model2 = new ModelRenderer(32, 32, 5, 0);
        model2.setRotationPoint(8.0F, 24.0F, -8.0F);
        model2.addBox(-8.0F, -29.0F, 8.0F, 0.0F, 11.0F, 0.0F, 0.2F, false);
        this.modelRenderer.addChild(model2);
        ModelRenderer model3 = new ModelRenderer(32, 32, 5, 0);
        model3.setRotationPoint(8.0F, 24.0F, -8.0F);
        model3.addBox(-8.5F, -28.0341F, 8.0F, 1.0F, 1.0F, 0.0F, 0.2F, false);
        this.modelRenderer.addChild(model3);
        ModelRenderer model4 = new ModelRenderer(32, 32, 5, 0);
        model4.setRotationPoint(8.0F, 24.0F, -8.0F);
        model4.addBox(-8.0F, -30.0F, 8.0F, 0.0F, 2.0F, 0.0F, 0.2F, false);
        this.modelRenderer.addChild(model4);
        ModelRenderer model5 = new ModelRenderer(32, 32, 5, 0);
        model5.setRotationPoint(8.0F, 24.0F, -8.0F);
        model5.addBox(-7.7F, -29.0341F, 8.0F, 0.0F, 3.0F, 0.0F, 0.2F, false);
        this.modelRenderer.addChild(model5);
        ModelRenderer model6 = new ModelRenderer(32, 32, 5, 0);
        model6.setRotationPoint(8.0F, 24.0F, -8.0F);
        model6.addBox(-8.3F, -29.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.2F, false);
        this.modelRenderer.addChild(model6);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
