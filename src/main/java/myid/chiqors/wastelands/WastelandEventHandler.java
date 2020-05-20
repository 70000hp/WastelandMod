package myid.chiqors.wastelands;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import myid.chiqors.wastelands.city.CityGenerator;
import myid.chiqors.wastelands.ruin.RuinVillageGenerator;
import myid.chiqors.wastelands.utils.Vector;
import myid.chiqors.wastelands.world.WastelandWorldData;
import myid.chiqors.wastelands.world.WorldChunkManagerWasteland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WastelandEventHandler 
{
	RuinVillageGenerator villageGeneratorHook;
	CityGenerator cityGeneratorHook;
	WastelandWorldData worldSaveData;
	boolean newSpawn;
	int spawnHeight;
	Vector spawnLoc;
	
	@SubscribeEvent
	public void loadData(WorldEvent.Load event)
	{
		ServerCommandManager manager = ((ServerCommandManager)MinecraftServer.getServer().getCommandManager());
		GetBiomesCommand command = new GetBiomesCommand();
		
		if (!manager.getCommands().containsKey(command.getCommandName()))
		{
			manager.registerCommand(command);
		}
		
		if (!event.world.isRemote && (event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName()))
		{
			this.worldSaveData.setFile("saves/" + Minecraft.getMinecraft().getIntegratedServer().getFolderName() + 
					"/data/WastelandMod.dat");
			Vector spawn = new Vector(event.world.getWorldInfo().getSpawnX(), event.world.getWorldInfo().getSpawnY()+10, event.world.getWorldInfo().getSpawnZ());
			
			if(!this.worldSaveData.checkIfExists())
			{
				this.worldSaveData.createFile();
				this.villageGeneratorHook.resetData();
				this.cityGeneratorHook.resetData();
				newSpawn = true;
				//RuinVillageGenerator.spawnBunker(spawn, event.world);
			}
			else
			{
				List<Vector> villageLocation = this.worldSaveData.loadVillageData();
				List<Vector> cityLocation = this.worldSaveData.loadCityData();
				this.villageGeneratorHook.loadData(villageLocation, villageLocation.size());
				this.cityGeneratorHook.loadData(cityLocation, cityLocation.size());
				this.spawnLoc = this.worldSaveData.loadSpawnLoc();
			}
			
			System.out.println("Event 2");
			
			if (newSpawn && ModConfig.spawnBunker)
			{
				this.spawnHeight = getMinWorldHeight(spawn, 3, event.world) - 7;
				spawn.Y = this.spawnHeight;
				RuinVillageGenerator.spawnBunker(spawn, event.world);
				this.spawnLoc = new Vector(spawn.X, spawn.Y + 1, spawn.Z);
				this.worldSaveData.saveSpawnLoc(this.spawnLoc);
			}
			
		}
	}
	
	private int getMinWorldHeight(Vector spawn, int rad, World world) 
	{
		int min = RuinVillageGenerator.getWorldHeight(world, spawn.X, spawn.Z);
		min = (min == 0) ? 100 : min;
		int height;
		for (int j = 0; j < (2 * rad) + 1; j++)
		{
			for (int i = 0; i < (2 * rad) + 1; i++)
			{
				height = RuinVillageGenerator.getWorldHeight(world, spawn.X - rad + i, spawn.Z - rad + i);
				//System.out.print(String.valueOf(height));
				if (height != 0)
				{
					min = (height < min) ? height : min;
				}
			}
		}
		return min;
	}

	@SubscribeEvent
	public void saveData(WorldEvent.Save event)
	{
		if (!event.world.isRemote)
		{
			this.villageGeneratorHook.saveData(this.worldSaveData);
			this.cityGeneratorHook.saveData(this.worldSaveData);
			this.newSpawn = false;
		}
	}
	
	@SubscribeEvent
	public void changeStartSpawn(EntityJoinWorldEvent event)
	{
		if (ModConfig.spawnBunker && (event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName()))
		{
			if (event.entity instanceof EntityPlayer)
			{
				Vector pos = new Vector((int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ);
				//Vector spawn = new Vector(event.world.getWorldInfo().getSpawnX(), event.world.getWorldInfo().getSpawnY(), event.world.getWorldInfo().getSpawnZ());
				
				EntityPlayer player = (EntityPlayer)event.entity;
				if (isNewPlayer(player) && Vector.VtoVlengthXZ(pos, this.spawnLoc) < 16)
				{
					//System.out.println(String.valueOf(player.isSpawnForced(0)));
					//System.out.println(String.valueOf(event.entity.posX) + " " + String.valueOf(event.entity.posZ));
					player.setPosition(this.spawnLoc.X, this.spawnLoc.Y, this.spawnLoc.Z);
					ChunkCoordinates spawnPos = new ChunkCoordinates(this.spawnLoc.X - 2, this.spawnLoc.Y, this.spawnLoc.Z + 1);
					player.setSpawnChunk(spawnPos, false);
					this.worldSaveData.savePlayerName(player.getDisplayName());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void disableSleep(PlayerInteractEvent event)
	{
		if(event.action == event.action.RIGHT_CLICK_BLOCK && ModConfig.disableSleep)
		{
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if (block instanceof BlockBed)
			{
				//System.out.println("trying to sleep");
				if (!event.world.isRemote)
				{
					ChunkCoordinates spawnPos = new ChunkCoordinates(event.x, event.y, event.z);
					//System.out.println("X:" + String.valueOf(spawnPos.posX) + " Y:" + String.valueOf(spawnPos.posY) + " Z:" + String.valueOf(spawnPos.posZ));
		            event.entityPlayer.setSpawnChunk(spawnPos, false);
		            event.entityPlayer.addChatMessage(new ChatComponentText("Spawn point set..."));
		            event.setCanceled(true);
				}
			}
		}
	}
	
	private boolean isNewPlayer(EntityPlayer player)
	{
		List<String> loadedPlayers = this.worldSaveData.getPlayerNames();
		if (loadedPlayers != null)
		{
			return !loadedPlayers.contains(player.getDisplayName());
		}
		else
		{
			return true;
		}
	}
	
	public void initialize(RuinVillageGenerator villageGen, CityGenerator cityGen, WastelandWorldData data)
	{
		this.villageGeneratorHook = villageGen;
		this.cityGeneratorHook = cityGen;
		this.worldSaveData = data;
		this.newSpawn = false;
	}
}
