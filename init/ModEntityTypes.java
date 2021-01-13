package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.entites.*;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {


    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, NewVoid.MOD_ID);

    // Entity Types

    public static final RegistryObject<EntityType<LegionaryEntity>> LEGIONARY = ENTITY_TYPES.register("legionary",
            () -> EntityType.Builder.create(LegionaryEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "legionary").toString()));

    public static final RegistryObject<EntityType<KnightEntity>> KNIGHT = ENTITY_TYPES.register("knight",
            () -> EntityType.Builder.create(KnightEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "knight").toString()));

    public static final RegistryObject<EntityType<HousecarlEntity>> HOUSECARL = ENTITY_TYPES.register("housecarl",
            () -> EntityType.Builder.create(HousecarlEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "housecarl").toString()));

    public static final RegistryObject<EntityType<BowmanEntity>> BOWMAN = ENTITY_TYPES.register("bowman",
            () -> EntityType.Builder.create(BowmanEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "bowman").toString()));

    public static final RegistryObject<EntityType<SpearEntity>> PILUM = ENTITY_TYPES.register("pilum",
            () -> EntityType.Builder.<SpearEntity>create(SpearEntity::new, EntityClassification.MISC)
                .size(0.5F, 0.5F)
                .build(new ResourceLocation(NewVoid.MOD_ID, "pilum").toString()));

    public static final RegistryObject<EntityType<GladiatorEntity>> GLADIATOR = ENTITY_TYPES.register("gladiator",
            () -> EntityType.Builder.create(GladiatorEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "gladiator").toString()));

    public static final RegistryObject<EntityType<ArcherEntity>> ARCHER = ENTITY_TYPES.register("archer",
            () -> EntityType.Builder.create(ArcherEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "archer").toString()));

    public static final RegistryObject<EntityType<BoarEntity>> BOAR = ENTITY_TYPES.register("boar",
            () -> EntityType.Builder.create(BoarEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(NewVoid.MOD_ID, "boar").toString()));

    //public static final RegistryObject<EntityType<EgyptEntity>> EGYPT = ENTITY_TYPES.register("egypt",
    //        () -> EntityType.Builder.create(EgyptEntity::new, EntityClassification.CREATURE)
    //                .size(0.6f, 1.95f)
    //                .build(new ResourceLocation(NewVoid.MOD_ID, "egypt").toString()));

}
