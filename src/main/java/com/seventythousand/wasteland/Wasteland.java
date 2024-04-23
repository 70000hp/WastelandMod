package com.seventythousand.wasteland;

import com.seventythousand.wasteland.city.CityGenerator;
import com.seventythousand.wasteland.config.CityLootConfig;
import com.seventythousand.wasteland.config.EntitySpawnConfig;
import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.config.RuinConfig;
import com.seventythousand.wasteland.items.ItemRegistry;
import com.seventythousand.wasteland.ruin.RuinVillageGenerator;
import com.seventythousand.wasteland.utils.Message;
import com.seventythousand.wasteland.world.WastelandWorldData;
import com.seventythousand.wasteland.world.WorldTypeWasteland;
import com.seventythousand.wasteland.world.biome.BiomeGenWastelandBase;
import com.seventythousand.wasteland.world.gen.WastelandGeneratorInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@Mod(modid = "WLM", name = "The Wasteland Mod", version = Info.VERSION, useMetadata = true, dependencies = "required-after:hbm")
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

    config.load();
    ModConfig.loadConfig(config);
    config.save();

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
      Configuration ruinConfig = new Configuration(new File("config/Wasteland/ChestLoot.cfg"));
      Configuration cityConfig = new Configuration(new File("config/Wasteland/CityLoot.cfg"));

      RuinConfig.load(ruinConfig);
      CityLootConfig.load(cityConfig);

    Configuration spawnConfig = new Configuration(new File("config/Wasteland/CreatureSpawns.cfg"));
    EntitySpawnConfig.load(spawnConfig);
    BiomeGenWastelandBase.load();

    //GameRegistry.registerWorldGenerator((new PostModWorldGenerator()).toIWorldGenerator(), 7);
  }

}
