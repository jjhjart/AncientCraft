package com.jjhjart.ancientcraft.blocks;

import com.jjhjart.ancientcraft.init.ModBlocks;
import com.jjhjart.ancientcraft.init.ModTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TemporalExplosionBlock extends Block {

    public static DamageSource damage = new DamageSource("temporalerror").setDamageIsAbsolute();

    public TemporalExplosionBlock() {
        super(Properties.create(Material.PORTAL)
                .sound(SoundType.METAL)
                .hardnessAndResistance(-1)
                .lightValue(0)
                .variableOpacity()
        );
    }

    @Override
    public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity) {
            if (((PlayerEntity)entity).isCreative()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onEntityCollision(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity entity) {
        entity.attackEntityFrom(damage, 1000000);
        if (entity instanceof ServerPlayerEntity) ModTriggers.TEMPORAL_EXPLOSION.trigger((ServerPlayerEntity) entity);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_196268_1_, IBlockReader p_196268_2_, BlockPos p_196268_3_, ISelectionContext context) {
        return VoxelShapes.create(VoxelShapes.fullCube().getBoundingBox().shrink(0.1));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (ItemStack.areItemStacksEqual(playerIn.inventory.getCurrentItem(), new ItemStack(ModBlocks.COPPER_BLOCK.get(), playerIn.inventory.getCurrentItem().getCount()))) {
            if(!playerIn.isCreative()) playerIn.inventory.getCurrentItem().grow(-1);
            worldIn.setBlockState(pos, ModBlocks.COPPER_BLOCK.get().getDefaultState());
            worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 3.0F, 1);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
