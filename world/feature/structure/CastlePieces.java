package com.jjhjart.ancientcraft.world.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.init.ModFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class CastlePieces {

    private static final ResourceLocation PART_1 = new ResourceLocation(NewVoid.MOD_ID, "castle_1");
    private static final ResourceLocation PART_2 = new ResourceLocation(NewVoid.MOD_ID, "castle_2");
    private static final ResourceLocation PART_3 = new ResourceLocation(NewVoid.MOD_ID, "castle_3");
    private static final ResourceLocation PART_4 = new ResourceLocation(NewVoid.MOD_ID, "castle_4");
    private static final ResourceLocation PART_5 = new ResourceLocation(NewVoid.MOD_ID, "castle_5_y");
    //private static final ResourceLocation PART_5 = new ResourceLocation(NewVoid.MOD_ID, "xcoli5");
    //private static final ResourceLocation PART_6 = new ResourceLocation(NewVoid.MOD_ID, "xcoli6");
    //private static final ResourceLocation PART_7 = new ResourceLocation(NewVoid.MOD_ID, "xcoli7");
    //private static final ResourceLocation PART_2 = new ResourceLocation(NewVoid.MOD_ID, "colosseum");

    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(PART_1, new BlockPos(0, 1, 0), PART_2, new BlockPos(0, 1, 0), PART_3, new BlockPos(0, 1, 0), PART_4, new BlockPos(0, 1, 0), PART_5, new BlockPos(0, 1, 0));

    public static void start(TemplateManager manager, BlockPos pos, Rotation rot, List<StructurePiece> pieces, Random rand) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffset = new BlockPos(1, -1, 1).rotate(rot);
        BlockPos blockPos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new CastlePieces.Piece(manager, PART_1, blockPos, rot));

        BlockPos rotationOffset2 = new BlockPos(1, -1, 32).rotate(rot);
        BlockPos blockPos2 = rotationOffset2.add(x, pos.getY(), z);
        pieces.add(new CastlePieces.Piece(manager, PART_2, blockPos2, rot));

        BlockPos rotationOffset3 = new BlockPos(33, -1, 1).rotate(rot);
        BlockPos blockPos3 = rotationOffset3.add(x, pos.getY(), z);
        pieces.add(new CastlePieces.Piece(manager, PART_3, blockPos3, rot));

        BlockPos rotationOffset4 = new BlockPos(33, -1, 33).rotate(rot);
        BlockPos blockPos4 = rotationOffset4.add(x, pos.getY(), z);
        pieces.add(new CastlePieces.Piece(manager, PART_4, blockPos4, rot));

        BlockPos rotationOffset5 = new BlockPos(1, 31, 1).rotate(rot);
        BlockPos blockPos5 = rotationOffset5.add(x, pos.getY(), z);
        pieces.add(new CastlePieces.Piece(manager, PART_5, blockPos5, rot));

    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos,
                     Rotation rotationIn) {
            super(ModFeatures.CASTLE_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockpos = CastlePieces.OFFSET.get(resourceLocation);
            this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(ModFeatures.CASTLE_PIECE, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
                                        MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
                TileEntity tileentity = worldIn.getTileEntity(pos);
                if (tileentity instanceof ChestTileEntity) {
                    // here you can set any loot tables for the chests
                }
            }
        }

        // create
        @Override
        public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> generator, Random randomIn,
                                      MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE);
            BlockPos blockpos = CastlePieces.OFFSET.get(this.resourceLocation);
            this.templatePosition.add(Template.transformedBlockPos(placementsettings,
                    new BlockPos(0 - blockpos.getX(), 0, 0 - blockpos.getZ())));

            return super.func_225577_a_(worldIn, generator, randomIn, structureBoundingBoxIn, chunkPos);
        }
    }
}
