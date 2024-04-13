

package com.seventythousand.wasteland;

import com.seventythousand.wasteland.utils.Message;
import com.seventythousand.wasteland.utils.Vector;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GetBiomesCommand extends CommandBase {
  public static final int minSize = 200;

  public String getCommandName() {
    return "biomes";
  }

  public String getCommandUsage(ICommandSender iCommandSender) {
    return "/biomes <range> (min range is " + 200 + ")";
  }

  public void processCommand(ICommandSender iCommandSender, String[] var) {
    if (iCommandSender instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)iCommandSender;
      World world = player.worldObj;
      if (var.length > 0 && player instanceof EntityPlayerMP) {
        int range = Math.max(Integer.parseInt(var[0]), 200);
        processCommandServer((EntityPlayerMP)player, range, new Vector((int)player.posX, (int)player.posY, (int)player.posZ));
      }
    }
  }

  public static void processCommandServer(EntityPlayerMP player, int range, Vector pos) {
    sendBiomes(range, pos, player, player.worldObj);
  }

  public static void processCommandServer(String playerName, int range, Vector pos) {
    World world = MinecraftServer.getServer().getEntityWorld();
    EntityPlayer player = world.getPlayerEntityByName(playerName);
    if (player instanceof EntityPlayerMP) {
      sendBiomes(range, pos, (EntityPlayerMP)player, world);
    } else {
      System.out.println("Not multiplayer");
    }
  }

  public static void sendBiomes(int range, Vector position, EntityPlayerMP player, World world) {
    int minSize = 200;
    int interval = range / minSize;
    int[][] biomeMap = new int[minSize][minSize];
    Random rand = new Random();
    Wasteland.NETWORK.sendToAll((IMessage) Message.createChatMessage("Creating biomes map (please wait)..."));
    Wasteland.NETWORK.sendToAll((IMessage)Message.createProgressMessage(0, 0));
    List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
    float count = 0.0F;
    for (int j = 0; j < minSize; j++) {
      for (int i = 0; i < minSize; i++) {
        BiomeGenBase currentBiome = world.getBiomeGenForCoords(position.X - minSize * interval / 2 + i * interval, position.Z - minSize * interval / 2 + j * interval);
        if (!biomes.contains(currentBiome))
          biomes.add(currentBiome);
        biomeMap[j][i] = getBiomeColour(currentBiome) + -16777216;
      }
      if ((count / minSize) > 0.01D) {
        Wasteland.NETWORK.sendToAll((IMessage)Message.createProgressMessage(j, minSize));
        count = 0.0F;
      }
      count++;
    }
    Wasteland.NETWORK.sendToAll((IMessage)Message.createProgressMessage(1, 1));
    Wasteland.NETWORK.sendToAll((IMessage)Message.createChatMessage("...done"));
    Wasteland.NETWORK.sendTo((IMessage)Message.createBiomesMessage(biomeMap, biomes, minSize), player);
  }

  private static int getBiomeColour(BiomeGenBase biomeGenBase) {
    if (biomeGenBase != null)
      return biomeGenBase.color & 0xFFFFFF;
    return 0;
  }
}
