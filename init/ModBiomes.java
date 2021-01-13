package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.world.biomes.BritainBiome;
import com.jjhjart.ancientcraft.world.biomes.RomanBiome;
import com.jjhjart.ancientcraft.world.biomes.RomanMountainBiome;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, NewVoid.MOD_ID);

    public static final RegistryObject<Biome> ROME = BIOMES.register("rome",
            () -> new RomanBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(),Blocks.STONE.getDefaultState())).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(6.0F).downfall(0.4F).waterColor(0x2EBFB3).waterFogColor(329011).parent((String)null)));

    public static final RegistryObject<Biome> ROME_MOUNTAINS = BIOMES.register("rome_mountains",
            () -> new RomanMountainBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.EXTREME_HILLS).depth(1.0F).scale(0.5F).temperature(0.6F).downfall(0.3F).waterColor(4159204).waterFogColor(329011).parent((String)null)));

    public static final RegistryObject<Biome> BRITAIN = BIOMES.register("britain",
            () -> new BritainBiome(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(),Blocks.STONE.getDefaultState())).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.4F).downfall(0.2F).waterColor(0x09269C).waterFogColor(329011).parent((String)null)));





    public static void registerBiomes() {
        registerBiome(ROME.get(), Type.PLAINS, Type.OVERWORLD);
        registerBiome(BRITAIN.get(), Type.PLAINS, Type.OVERWORLD); 
        registerBiome(ROME_MOUNTAINS.get(), Type.MOUNTAIN, Type.OVERWORLD);
    }

    private static void registerBiome(Biome biome, Type... types) {
        // the line below will make it spawn in the overworld
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

    @SuppressWarnings("deprecation")
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }
}

