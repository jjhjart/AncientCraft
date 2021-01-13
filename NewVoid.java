package com.jjhjart.ancientcraft;
import com.jjhjart.ancientcraft.init.*;
import com.jjhjart.ancientcraft.items.ModSpawnEggItems;
import com.jjhjart.ancientcraft.proxy.ClientProxy;
import com.jjhjart.ancientcraft.proxy.CommonProxy;
import com.jjhjart.ancientcraft.proxy.IProxy;
import com.jjhjart.ancientcraft.world.gen.StructureGen;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod("ancientcraft")
@Mod.EventBusSubscriber(modid = NewVoid.MOD_ID, bus = Bus.MOD)
public class NewVoid {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ancientcraft";
    public static final IProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static NewVoid instance;
    // public static final WorldType EXAMPLE_WORLDTYPE = new ExampleWorldType();
    public static final ResourceLocation ROMAN_DIM_TYPE = new ResourceLocation(MOD_ID, "roman");
    public static final ResourceLocation MEDIEVAL_DIM_TYPE = new ResourceLocation(MOD_ID, "medieval");

    public NewVoid() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        //ParticleInit.PARTICLE_TYPES.register(modEventBus);
        //SoundInit.SOUNDS.register(modEventBus);
        //PotionInit.POTIONS.register(modEventBus);
        //PotionInit.POTION_EFFECTS.register(modEventBus);
        //EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        //RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        //ModFluids.FLUIDS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ////ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        //ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModDimensions.MOD_DIMENSIONS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        //PortalBlockInit.BLOCKS.register(modEventBus);
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
        //PROXY.modConstructor(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(/*/TutorialItemGroup.instance/*/NewVoid.TAB);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(block.getRegistryName());
                    registry.register(blockItem);
                });

        LOGGER.debug("Registered BlockItems!");
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        ModBiomes.registerBiomes();
    }

    private void setup(final FMLCommonSetupEvent event) {// K9#8016

        NewVoid.LOGGER.info("AncientCraft is in common setup state.");
        ModPacketHandler.init();
        ModCapabilities.register();
        ModTriggers.register();
        //DeferredWorkQueue.runLater(ModOreGen::generateOre);
        DeferredWorkQueue.runLater(StructureGen::generateStructures);
        /*
         * DeferredWorkQueue.runLater(() -> { for (Biome biome : ForgeRegistries.BIOMES)
         * { if (biome instanceof ExampleBiome) {
         * biome.getSpawns(EntityClassification.MONSTER) .add(new
         * Biome.SpawnListEntry(EntityType.ZOMBIE, 1000, 1, 4)); } } });
         */
    }

    /*
     * public static void registerPlacementType(EntityType type,
     * EntitySpawnPlacementRegistry.PlacementType placementType) {
     * EntitySpawnPlacementRegistry.register(type, placementType,
     * Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
     * MonsterEntity::canMonsterSpawnInLight); }
     */

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
        // This doesnt work anymore
        // TutorialOreGen.generateOre();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItems.initSpawneggs();
    }

//    public static class TutorialItemGroup extends ItemGroup {
//        public static final ItemGroup instance = new TutorialItemGroup(ItemGroup.GROUPS.length, "vmTab");
//
//        private TutorialItemGroup(int index, String label) {
//            super(index, label);
//        }

        //@Override
      //  public ItemStack createIcon() {
//            return new ItemStack(ModBlocks.PINE_PLANKS.get());
//        }
    //}
    public static final ItemGroup TAB = new ItemGroup("ancientcraftTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.LORICA_SEGMENTATA.get());
        }
    };
}