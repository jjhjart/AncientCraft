package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;


import com.jjhjart.ancientcraft.items.CreativeTimeMachineItem;
import com.jjhjart.ancientcraft.items.*;
import com.jjhjart.ancientcraft.util.enums.CopperArmorMaterial;
import com.jjhjart.ancientcraft.util.enums.KnightArmorMaterial;
import com.jjhjart.ancientcraft.util.enums.RomeArmorMaterial;
import com.jjhjart.ancientcraft.util.enums.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NewVoid.MOD_ID);

    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<Item> HALBERD_HEAD = ITEMS.register("halberd_head", () -> new Item(new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<Item> POLL_PIECE = ITEMS.register("poll_piece", () -> new Item(new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<Item> STAFF = ITEMS.register("staff", () -> new Item(new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<Item> HALBERD_POLL = ITEMS.register("halberd_poll", () -> new Item(new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<RawHog> RAW_HOG = ITEMS.register("raw_hog", RawHog::new);
    //public static final RegistryObject<CarrotCake> CARROT_CAKE = ITEMS.register("carrot_cake", CarrotCake::new);
    public static final RegistryObject<CookedHog> COOKED_HOG = ITEMS.register("cooked_hog", CookedHog::new);
    public static final RegistryObject<CookedEgg> COOKED_EGG = ITEMS.register("cooked_egg", CookedEgg::new);
    public static final RegistryObject<Cheese> CHEESE = ITEMS.register("cheese", Cheese::new);
    public static final RegistryObject<PineNeedle> PINE_NEEDLE = ITEMS.register("pine_needle", PineNeedle::new);


    public static final RegistryObject<ModSpawnEggItems> GLADIATOR_SPAWN_EGG = ITEMS.register("gladiator_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.GLADIATOR, 0xA30C0C, 0x9D920C, new Item.Properties().group(NewVoid.TAB)));

    //public static final RegistryObject<ModSpawnEggItems> EGYPT_SPAWN_EGG = ITEMS.register("egypt_spawn_egg",
    //        () -> new ModSpawnEggItems(ModEntityTypes.EGYPT, 0x335BFF, 0xC5D132, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ModSpawnEggItems> BOAR_SPAWN_EGG = ITEMS.register("boar_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.BOAR, 0x4D2506, 0x4D2506, new Item.Properties().group(NewVoid.TAB)));


    public static final RegistryObject<ModSpawnEggItems> ARCHER_SPAWN_EGG = ITEMS.register("archer_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.ARCHER, 0xA30C0C, 0x9D920C, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ModSpawnEggItems> KNIGHT_SPAWN_EGG = ITEMS.register("knight_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.KNIGHT, 0x657677, 0x3E4949, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ModSpawnEggItems> HOUSECARL_SPAWN_EGG = ITEMS.register("housecarl_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.HOUSECARL, 0x657677, 0x3E4949, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ModSpawnEggItems> BOWMAN_SPAWN_EGG = ITEMS.register("bowman_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.BOWMAN, 0x657677, 0x3E4949, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<SwordItem> GLADIUS = ITEMS.register("gladius",
            () -> new SwordItem(ModItemTier.COPPER, 2, -2.4F, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<SwordItem> RITTERSCHWERT = ITEMS.register("ritterschwert",
            () -> new SwordItem(ModItemTier.COPPER, 2, -2.4F, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<SpearItem> PILUM = ITEMS.register("pilum",
            () -> new SpearItem((new Item.Properties())
                    .maxDamage(250)
                    .group(NewVoid.TAB)));

    public static final RegistryObject<ModSpawnEggItems> LEGIONARY_SPAWN_EGG = ITEMS.register("legionary_spawn_egg",
            () -> new ModSpawnEggItems(ModEntityTypes.LEGIONARY, 0xA30C0C, 0x9D920C, new Item.Properties().group(NewVoid.TAB)));


    public static final RegistryObject<ModdedShieldItem> SCUTUM = ITEMS.register("scutum",
            () -> new ModdedShieldItem((new Item.Properties())
            .maxDamage(336)
            .group(NewVoid.TAB)));

    public static final RegistryObject<ModdedShieldItem> SCUTCHEON = ITEMS.register("scutcheon",
            () -> new ModdedShieldItem((new Item.Properties())
                    .maxDamage(336)
                    .group(NewVoid.TAB)));


    public static final RegistryObject<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModItemTier.COPPER, 0, -2.8F, new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<ShovelItem> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModItemTier.COPPER, 0.5F, -3.0F, new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<AxeItem> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModItemTier.COPPER, 0, -3.1F, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<AxeItem> HALBERD = ITEMS.register("halberd",
            () -> new AxeItem(ModItemTier.COPPER, 11.5f, -3.2F, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<HoeItem> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModItemTier.COPPER, -1.0F, new Item.Properties().group(NewVoid.TAB)));

    // Armor

    public static final RegistryObject<ArmorItem> COPPER_HELMET = ITEMS.register("copper_helmet", () ->
            new ArmorItem(CopperArmorMaterial.COPPER, EquipmentSlotType.HEAD, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () ->
            new ArmorItem(CopperArmorMaterial.COPPER, EquipmentSlotType.CHEST, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () ->
            new ArmorItem(CopperArmorMaterial.COPPER, EquipmentSlotType.LEGS, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> COPPER_BOOTS = ITEMS.register("copper_boots", () ->
            new ArmorItem(CopperArmorMaterial.COPPER, EquipmentSlotType.FEET, new Item.Properties().group(NewVoid.TAB)));



    public static final RegistryObject<ArmorItem> GALEA = ITEMS.register("galea", () ->
            new ArmorItem(RomeArmorMaterial.ROME, EquipmentSlotType.HEAD, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> LORICA_SEGMENTATA = ITEMS.register("lorica_segmentata", () ->
            new ArmorItem(RomeArmorMaterial.ROME, EquipmentSlotType.CHEST, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> BALTEUS = ITEMS.register("balteus", () ->
            new ArmorItem(RomeArmorMaterial.ROME, EquipmentSlotType.LEGS, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> CALIGE = ITEMS.register("calige", () ->
            new ArmorItem(RomeArmorMaterial.ROME, EquipmentSlotType.FEET, new Item.Properties().group(NewVoid.TAB)));




    public static final RegistryObject<ArmorItem> CLOSE_HELM = ITEMS.register("close_helm", () ->
            new ArmorItem(KnightArmorMaterial.KNIGHT, EquipmentSlotType.HEAD, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> BREAST_PLATE = ITEMS.register("breast_plate", () ->
            new ArmorItem(KnightArmorMaterial.KNIGHT, EquipmentSlotType.CHEST, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> CUISSE = ITEMS.register("cuisse", () ->
            new ArmorItem(KnightArmorMaterial.KNIGHT, EquipmentSlotType.LEGS, new Item.Properties().group(NewVoid.TAB)));

    public static final RegistryObject<ArmorItem> SABATONS = ITEMS.register("sabatons", () ->
            new ArmorItem(KnightArmorMaterial.KNIGHT, EquipmentSlotType.FEET, new Item.Properties().group(NewVoid.TAB)));

    //public static final RegistryObject<ArmorItem> EGYPT_HELMET = ITEMS.register("egypt_helmet", () ->
    //        new ArmorItem(EgyptArmorMaterial.EGYPT, EquipmentSlotType.HEAD, new Item.Properties().group(NewVoid.TAB)));
    public static final RegistryObject<Item> TIME_MACHINE = ITEMS.register("time_machine", CreativeTimeMachineItem::new);

}
