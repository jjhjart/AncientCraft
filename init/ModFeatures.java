package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.world.feature.structure.CastlePieces;
import com.jjhjart.ancientcraft.world.feature.structure.CastleStructure;
import com.jjhjart.ancientcraft.world.feature.structure.CampPieces;
import com.jjhjart.ancientcraft.world.feature.structure.CampStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = NewVoid.MOD_ID)
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<Feature<?>>(
            ForgeRegistries.FEATURES, NewVoid.MOD_ID);

    public static IStructurePieceType CAMP_PIECES = CampPieces.Piece::new;

    public static IStructurePieceType CASTLE_PIECE = CastlePieces.Piece::new;

    public static final RegistryObject<CampStructure> CAMP = FEATURES.register("camp", () -> new CampStructure(NoFeatureConfig::deserialize));

    public static final RegistryObject<CastleStructure> CASTLE = FEATURES.register("castle", () -> new CastleStructure(NoFeatureConfig::deserialize));
    @SubscribeEvent

    public static void registerStructurePieces(RegistryEvent.Register<Feature<?>> event) {
        Registry.register(Registry.STRUCTURE_PIECE, "CAMP".toLowerCase(Locale.ROOT), CAMP_PIECES);
        Registry.register(Registry.STRUCTURE_PIECE, "CASTLE".toLowerCase(Locale.ROOT), CASTLE_PIECE);
    }
}
