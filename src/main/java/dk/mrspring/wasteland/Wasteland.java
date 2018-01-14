package dk.mrspring.wasteland;

import java.io.File;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.city.CityGenerator;
import dk.mrspring.wasteland.entity.EntityDayZombie;
import dk.mrspring.wasteland.entity.RenderDayZombie;
import dk.mrspring.wasteland.ruin.Ruin;
import dk.mrspring.wasteland.ruin.RuinVillageGenerator;
import dk.mrspring.wasteland.world.WastelandWorldData;
import dk.mrspring.wasteland.world.WorldTypeWasteland;
import dk.mrspring.wasteland.world.biome.BiomeGenWastelandBase;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandBigTree;

@Mod(modid=ModHelper.ModInfo.modid, name=ModHelper.ModInfo.name, version=ModHelper.ModInfo.version, useMetadata = true)
public class Wasteland
{
	public static WorldType wastelandWorldType;
	public static RuinVillageGenerator villageGenerator;
	public static CityGenerator cityGenerator;
	
	public static int lastID = 0;
	
	@Instance(value = ModHelper.ModInfo.modid)
	public static Wasteland instance;
	
	public static WastelandEventHandler eventHandler = new WastelandEventHandler();
	public static WastelandWorldData worldData = new WastelandWorldData();
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		
		MinecraftForge.EVENT_BUS.register(eventHandler);
		
		Configuration config = new Configuration(new File("config/Wasteland/TerrainGen.cfg"));
		Configuration ruinConfig = new Configuration(new File("config/Wasteland/ChestLoot.cfg"));
		
		ModConfig.load(config);
		RuinConfig.load(ruinConfig);
		
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDayZombie.class, "Day Zombie", id);
		EntityRegistry.registerModEntity(EntityDayZombie.class, "Day Zombie", id, Wasteland.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityDayZombie.class, new RenderDayZombie());
		
		BiomeGenWastelandBase.load();
		
		//EntityRegistry.addSpawn(EntityDayZombie.class, 2, 1, 3, EnumCreatureType.monster, WastelandBiomes.apocalypse, WastelandBiomes.forest);
		
		villageGenerator = new RuinVillageGenerator();
		cityGenerator = new CityGenerator();
		eventHandler.initialize(villageGenerator, cityGenerator, worldData);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		wastelandWorldType = new WorldTypeWasteland("wasteland");
		WorldTypeWasteland.genInfo.createDefault();

	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
