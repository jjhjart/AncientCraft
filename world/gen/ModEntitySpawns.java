package com.jjhjart.ancientcraft.world.gen;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = NewVoid.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.NETHER) { }

            else if (biome.getCategory() == Biome.Category.THEEND) { }
            else if (biome.getCategory() == Biome.Category.OCEAN) {

            }

            else {
                //if (biome.getCategory() == Biome.Category.OCEAN) {

                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.ARCHER.get(), 1, 3, 5));
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.GLADIATOR.get(), 1, 3, 5));
                //biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.LION.get(), 1, 3, 5));
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.BOAR.get(), 1, 3, 5));
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.LEGIONARY.get(), 1, 3, 5));
                //}
            }
        }
    }
}
