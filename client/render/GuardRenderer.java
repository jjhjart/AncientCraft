package com.jjhjart.ancientcraft.client.render;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.client.model.GuardModel;
import com.jjhjart.ancientcraft.client.model.RomeModel;
import com.jjhjart.ancientcraft.entites.LegionaryEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuardRenderer extends AbstractRomeRenderer<LegionaryEntity, RomeModel<LegionaryEntity>> {
    private static final ResourceLocation LOCATION = new ResourceLocation(NewVoid.MOD_ID, "textures/entity/guard.png");


    //DrownedRenderer
    public GuardRenderer(EntityRendererManager renderManagerIn) {

        super(renderManagerIn, new GuardModel<>(0.0F, 0.0F, 64, 64), new GuardModel<>(0.5F, true), new GuardModel<>(1.0F, true));
        //this.addLayer(new HeldItemLayer<>(this));
        //this.addLayer(new BipedArmorLayer<>(this, new GuardModel<>(0.5F, true), new GuardModel<>(1.0F, true)));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(LegionaryEntity entity) {
        return LOCATION;
    }

    protected void applyRotations(LegionaryEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = entityLiving.getSwimAnimation(partialTicks);
        if (f > 0.0F) {
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(MathHelper.lerp(f, entityLiving.rotationPitch, -10.0F - entityLiving.rotationPitch)));
        }

    }
}
