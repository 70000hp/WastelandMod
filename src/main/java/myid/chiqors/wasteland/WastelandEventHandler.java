

package myid.chiqors.wasteland;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import myid.chiqors.wasteland.city.CityGenerator;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.gui.ProgressGui;
import myid.chiqors.wasteland.items.ItemRegistry;
import myid.chiqors.wasteland.ruin.RuinVillageGenerator;
import myid.chiqors.wasteland.utils.Vector;
import myid.chiqors.wasteland.world.WastelandWorldData;
import myid.chiqors.wasteland.world.WorldChunkManagerWasteland;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WastelandEventHandler {
  RuinVillageGenerator villageGeneratorHook;
  
  CityGenerator cityGeneratorHook;
  
  WastelandWorldData worldSaveData;
  
  boolean newSpawn;
  
  int spawnHeight;
  
  Vector spawnLoc;
  
  @SubscribeEvent
  public void loadData(WorldEvent.Load event) {
    if (!event.world.isClient) {
      ServerCommandManager manager = (ServerCommandManager)MinecraftServer.getServer().getCommandManager();
      GetBiomesCommand command = new GetBiomesCommand();
      if (!manager.getCommands().containsKey(command.getCommandName()))
        manager.registerCommand((ICommand)command); 
    } 
    if (!event.world.isClient && event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName()) {
      if (MinecraftServer.getServer().isSinglePlayer()) {
        this.worldSaveData.setFile("saves/" + MinecraftServer.getServer().getFolderName() + "/data/WastelandMod.dat");
      } else {
        this.worldSaveData.setFile(MinecraftServer.getServer().getFolderName() + "/data/WastelandMod.dat");
      } 
      Vector spawn = new Vector(event.world.getWorldInfo().getSpawnX(), event.world.getWorldInfo().getSpawnY() + 10, event.world.getWorldInfo().getSpawnZ());
      if (!this.worldSaveData.checkIfExists()) {
        this.worldSaveData.createFile();
        this.villageGeneratorHook.resetData();
        this.cityGeneratorHook.resetData();
        this.newSpawn = true;
      } else {
        List<Vector> villageLocation = this.worldSaveData.loadVillageData();
        List<Vector> cityLocation = this.worldSaveData.loadCityData();
        this.villageGeneratorHook.loadData(villageLocation, villageLocation.size());
        this.cityGeneratorHook.loadData(cityLocation, cityLocation.size());
        this.spawnLoc = this.worldSaveData.loadSpawnLoc();
      } 
      if (this.newSpawn && ModConfig.spawnBunker) {
        this.spawnHeight = getMinWorldHeight(spawn, 3, event.world) - 7;
        spawn.Y = this.spawnHeight;
        RuinVillageGenerator.spawnBunker(spawn, event.world);
        this.spawnLoc = new Vector(spawn.X, spawn.Y + 1, spawn.Z);
        this.worldSaveData.saveSpawnLoc(this.spawnLoc);
      } 
    } 
  }
  
  private int getMinWorldHeight(Vector spawn, int rad, World world) {
    int min = RuinVillageGenerator.getWorldHeight(world, spawn.X, spawn.Z);
    min = (min == 0) ? 100 : min;
    for (int j = 0; j < 2 * rad + 1; j++) {
      for (int i = 0; i < 2 * rad + 1; i++) {
        int height = RuinVillageGenerator.getWorldHeight(world, spawn.X - rad + i, spawn.Z - rad + i);
        if (height != 0)
          min = (height < min) ? height : min; 
      } 
    } 
    return min;
  }
  
  @SubscribeEvent
  public void saveData(WorldEvent.Save event) {
    if (!event.world.isClient && event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName()) {
      this.villageGeneratorHook.saveData(this.worldSaveData);
      this.cityGeneratorHook.saveData(this.worldSaveData);
      this.newSpawn = false;
    } 
  }
  
  @SubscribeEvent
  public void changeStartSpawn(EntityJoinWorldEvent event) {
    if (ModConfig.spawnBunker && event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName())
      if (event.entity instanceof EntityPlayer && !event.world.isClient) {
        Vector pos = new Vector((int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ);
        EntityPlayer player = (EntityPlayer)event.entity;
        if (isNewPlayer(player) && Vector.VtoVlengthXZ(pos, this.spawnLoc) < 16.0D) {
          player.setPosition(this.spawnLoc.X, this.spawnLoc.Y, this.spawnLoc.Z);
          ChunkCoordinates spawnPos = new ChunkCoordinates(this.spawnLoc.X - 2, this.spawnLoc.Y, this.spawnLoc.Z + 1);
          player.setSpawnChunk(spawnPos, false);
          this.worldSaveData.savePlayerName(player.getDisplayName());
        } 
      }  
    if (event.entity instanceof EntityPlayer && event.world.isClient)
      ProgressGui.visible = false; 
  }
  
  @SubscribeEvent
  public void disableSleep(PlayerInteractEvent event) {
    if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && ModConfig.disableSleep) {
      Block block = event.world.getBlock(event.x, event.y, event.z);
      if (block instanceof net.minecraft.block.BlockBed)
        if (!event.world.isClient) {
          ChunkCoordinates spawnPos = new ChunkCoordinates(event.x, event.y, event.z);
          event.entityPlayer.setSpawnChunk(spawnPos, false);
          event.entityPlayer.addChatMessage((IChatComponent)new ChatComponentText("Spawn point set..."));
          event.setCanceled(true);
        }  
    } 
  }
  
  private boolean isNewPlayer(EntityPlayer player) {
    List<String> loadedPlayers = this.worldSaveData.getPlayerNames();
    if (loadedPlayers != null)
      return !loadedPlayers.contains(player.getDisplayName()); 
    return true;
  }
  
  public void initialize(RuinVillageGenerator villageGen, CityGenerator cityGen, WastelandWorldData data) {
    this.villageGeneratorHook = villageGen;
    this.cityGeneratorHook = cityGen;
    this.worldSaveData = data;
    this.newSpawn = false;
  }
  
  @SubscribeEvent
  public void playerIntract(PlayerInteractEvent event) {
    if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
      ItemStack item = event.entityPlayer.getCurrentEquippedItem();
      if (item != null && item.getItem().equals(Items.glass_bottle)) {
        double y1 = -1.0D * Math.sin(event.entityPlayer.rotationPitch * Math.PI / 180.0D);
        double z1 = Math.cos(event.entityPlayer.rotationPitch * Math.PI / 180.0D) * Math.cos(event.entityPlayer.rotationYaw * Math.PI / 180.0D);
        double x1 = -1.0D * Math.cos(event.entityPlayer.rotationPitch * Math.PI / 180.0D) * Math.sin(event.entityPlayer.rotationYaw * Math.PI / 180.0D);
        double i;
        for (i = 0.0D; i < 20.0D; i += 0.1D) {
          Block block = event.world.getBlock((int)(event.entityPlayer.posX + x1 * i), (int)(event.entityPlayer.posY + event.entityPlayer.eyeHeight + y1 * i), (int)(event.entityPlayer.posZ + z1 * i));
          if (block != null && block.getMaterial() == Material.water) {
            if (block instanceof myid.chiqors.wasteland.items.BlockRadFluid) {
              event.setCanceled(true);
              return;
            } 
            return;
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onItemUse(PlayerUseItemEvent event) {
    System.out.println(event.item.toString());
  }
  
  @SubscribeEvent
  public void onBucketFill(FillBucketEvent event) {
    ItemStack result = fillCustomBucket(event.world, event.target);
    if (result == null)
      return; 
    event.result = result;
    event.setResult(Event.Result.ALLOW);
  }
  
  private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
    Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
    if (block instanceof myid.chiqors.wasteland.items.BlockRadFluid) {
      world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
      return new ItemStack((Item)ItemRegistry.radiationWasteBucket);
    } 
    return null;
  }
}
