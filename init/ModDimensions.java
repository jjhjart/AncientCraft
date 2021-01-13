package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.world.dimension.RomanModDimension;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, NewVoid.MOD_ID);

    public static final RegistryObject<ModDimension> ROMAN_DIM = MOD_DIMENSIONS.register("roman_dim", () -> new RomanModDimension());
    public static final RegistryObject<ModDimension> MEDIEVAL_DIM = MOD_DIMENSIONS.register("medieval_dim", () -> new RomanModDimension());
}
