package myid.chiqors.wasteland;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import myid.chiqors.wasteland.city.CityGenerator;
import myid.chiqors.wasteland.config.CityLootConfig;
import myid.chiqors.wasteland.config.EntitySpawnConfig;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.config.RuinConfig;
import myid.chiqors.wasteland.items.ItemRegistry;
import myid.chiqors.wasteland.ruin.RuinVillageGenerator;
import myid.chiqors.wasteland.utils.Message;
import myid.chiqors.wasteland.world.WastelandWorldData;
import myid.chiqors.wasteland.world.WorldTypeWasteland;
import myid.chiqors.wasteland.world.biome.BiomeGenWastelandBase;
import myid.chiqors.wasteland.world.gen.WastelandGeneratorInfo;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@Mod(modid = "WLM", name = "The Wasteland Mod", version = "1.4.2", useMetadata = true)
public class Wasteland {
  public static WorldType wastelandWorldType;
  
  public static RuinVillageGenerator villageGenerator;
  
  public static CityGenerator cityGenerator;
  
  public static SimpleNetworkWrapper NETWORK;
  
  public static int lastID = 0;
  
  @Instance("WLM")
  public static Wasteland instance;
  
  public static WastelandEventHandler eventHandler;
  
  public static WastelandWorldData worldData = new WastelandWorldData();
  
  public static ItemRegistry items;
  
  @EventHandler
  public static void preInit(FMLPreInitializationEvent event) {
    NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("WLM".toLowerCase());
    NETWORK.registerMessage(Message.HandlerClient.class, Message.class, 0, Side.CLIENT);
    NETWORK.registerMessage(Message.HandlerServer.class, Message.class, 0, Side.SERVER);
    Configuration config = new Configuration(new File("config/Wasteland/TerrainGen.cfg"));
    Configuration ruinConfig = new Configuration(new File("config/Wasteland/ChestLoot.cfg"));
    Configuration cityConfig = new Configuration(new File("config/Wasteland/CityLoot.cfg"));
    ModConfig.load(config);
    RuinConfig.load(ruinConfig);
    CityLootConfig.load(cityConfig);
    items = new ItemRegistry();
    if (event.getSide().isClient()) {
      MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
    } 
    villageGenerator = new RuinVillageGenerator();
    cityGenerator = new CityGenerator();
    eventHandler = new WastelandEventHandler();
    MinecraftForge.EVENT_BUS.register(eventHandler);
    eventHandler.initialize(villageGenerator, cityGenerator, worldData);
  }
  
  @EventHandler
  public static void init(FMLInitializationEvent event) {
    wastelandWorldType = (WorldType)new WorldTypeWasteland("wasteland");
    WastelandGeneratorInfo.createDefault();
  }
  
  @EventHandler
  public static void postInit(FMLPostInitializationEvent event) {
    Configuration spawnConfig = new Configuration(new File("config/Wasteland/CreatureSpawns.cfg"));
    EntitySpawnConfig.load(spawnConfig);
    BiomeGenWastelandBase.load();
    //GameRegistry.registerWorldGenerator((new PostModWorldGenerator()).toIWorldGenerator(), 7);
  }

}
