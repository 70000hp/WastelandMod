

package myid.chiqors.wasteland.utils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import myid.chiqors.wasteland.GetBiomesCommand;
import myid.chiqors.wasteland.client.ClientProxy;
import myid.chiqors.wasteland.gui.ProgressGui;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.biome.BiomeGenBase;

public class Message implements IMessage {
  private byte[] data;
  
  public Message() {}
  
  public Message(byte[] data) {
    this.data = data;
  }
  
  public static Message createBiomesMessage(int[][] biomeMap, List<BiomeGenBase> biomes, int size) {
    int numBiomes = biomes.size();
    int dataSize = size * size * 4;
    byte[] dataOut = new byte[dataSize + numBiomes * 4 + 5];
    dataOut[0] = 1;
    dataOut[1] = (byte)((size & 0xFF000000) >> 24);
    dataOut[2] = (byte)((size & 0xFF0000) >> 16);
    dataOut[3] = (byte)((size & 0xFF00) >> 8);
    dataOut[4] = (byte)(size & 0xFF);
    int count = 5;
    int i;
    for (i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        dataOut[count] = (byte)((biomeMap[i][j] & 0xFF000000) >> 24);
        dataOut[count + 1] = (byte)((biomeMap[i][j] & 0xFF0000) >> 16);
        dataOut[count + 2] = (byte)((biomeMap[i][j] & 0xFF00) >> 8);
        dataOut[count + 3] = (byte)(biomeMap[i][j] & 0xFF);
        count += 4;
      } 
    } 
    for (i = 0; i < numBiomes; i++) {
      int id = ((BiomeGenBase)biomes.get(i)).biomeID;
      dataOut[count] = (byte)((id & 0xFF000000) >> 24);
      dataOut[count + 1] = (byte)((id & 0xFF0000) >> 16);
      dataOut[count + 2] = (byte)((id & 0xFF00) >> 8);
      dataOut[count + 3] = (byte)(id & 0xFF);
      count += 4;
    } 
    return new Message(dataOut);
  }
  
  public static Message createChatMessage(String string) {
    byte[] dataOut = new byte[string.length() + 1];
    dataOut[0] = 2;
    for (int i = 1; i < string.length() + 1; i++)
      dataOut[i] = (byte)string.charAt(i - 1); 
    return new Message(dataOut);
  }
  
  public static Message createPlayerInfoMessage(EntityPlayer player) {
    String playerName = player.getDisplayName();
    Vector pos = new Vector((int)player.posX, (int)player.posY, (int)player.posZ);
    byte[] dataOut = new byte[12 + playerName.length() + 1];
    dataOut[0] = 3;
    int count = 1;
    int i;
    for (i = 0; i < 3; i++) {
      int p = (i == 0) ? pos.X : ((i == 1) ? pos.Y : pos.Z);
      dataOut[count] = (byte)((p & 0xFF000000) >> 24);
      dataOut[count + 1] = (byte)((p & 0xFF0000) >> 16);
      dataOut[count + 2] = (byte)((p & 0xFF00) >> 8);
      dataOut[count + 3] = (byte)(p & 0xFF);
      count += 4;
    } 
    for (i = 0; i < playerName.length(); i++) {
      dataOut[count] = (byte)playerName.charAt(i);
      count++;
    } 
    return new Message(dataOut);
  }
  
  public static Message createProgressMessage(int progress, int total) {
    byte[] dataOut = new byte[9];
    dataOut[0] = 4;
    dataOut[1] = (byte)((progress & 0xFF000000) >> 24);
    dataOut[2] = (byte)((progress & 0xFF0000) >> 16);
    dataOut[3] = (byte)((progress & 0xFF00) >> 8);
    dataOut[4] = (byte)(progress & 0xFF);
    dataOut[5] = (byte)((total & 0xFF000000) >> 24);
    dataOut[6] = (byte)((total & 0xFF0000) >> 16);
    dataOut[7] = (byte)((total & 0xFF00) >> 8);
    dataOut[8] = (byte)(total & 0xFF);
    return new Message(dataOut);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.data = new byte[buf.readableBytes()];
    buf.readBytes(this.data);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBytes(this.data);
  }
  
  public static class HandlerServer implements IMessageHandler<Message, IMessage> {
    public IMessage onMessage(Message message, MessageContext ctx) {
      byte[] data = message.data;
      if (data[0] == 3) {
        Vector pos = new Vector(Message.getInt(data, 1), Message.getInt(data, 5), Message.getInt(data, 9));
        int range = Message.getInt(data, 13);
        int c = 17;
        byte[] nameBytes = new byte[data.length - c];
        for (int i = 0; i < nameBytes.length; i++)
          nameBytes[i] = data[c + i]; 
        String name = new String(nameBytes);
        GetBiomesCommand.processCommandServer(name, range, pos);
      } 
      return null;
    }
  }
  
  public static class HandlerClient implements IMessageHandler<Message, IMessage> {
    public IMessage onMessage(Message message, MessageContext ctx) {
      byte[] data = message.data;
      if (data[0] == 1) {
        int size = Message.getInt(data, 1);
        int[][] biomeData = new int[size][size];
        int count = 5;
        for (int i = 0; i < size; i++) {
          for (int k = 0; k < size; k++) {
            biomeData[i][k] = Message.getInt(data, count);
            count += 4;
          } 
        } 
        List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
        int numBiomes = (data.length - count) / 4;
        for (int j = 0; j < numBiomes; j++) {
          int id = Message.getInt(data, count);
          biomes.add(BiomeGenBase.getBiome(id));
          count += 4;
        } 
        ClientProxy.displayBiomeMap(biomeData, 200, biomes);
      } else if (data[0] == 2) {
        char[] newMessage = new char[data.length - 1];
        for (int i = 1; i < data.length; i++)
          newMessage[i - 1] = (char)data[i]; 
        (Minecraft.getMinecraft()).thePlayer.addChatComponentMessage((IChatComponent)new ChatComponentText(Message.getString(newMessage, 0, newMessage.length)));
      } else if (data[0] == 4) {
        int progress = Message.getInt(data, 1);
        int total = Message.getInt(data, 5);
        if (progress == 0) {
          ProgressGui.progress = 0.0F;
          ProgressGui.visible = true;
        } else if (progress == total) {
          ProgressGui.progress = 1.0F;
          ProgressGui.visible = false;
        } else {
          ProgressGui.progress = progress / total;
        } 
      } 
      return null;
    }
  }
  
  public static int getInt(byte[] data, int index) {
    return data[index] << 24 & 0xFF000000 | data[index + 1] << 16 & 0xFF0000 | data[index + 2] << 8 & 0xFF00 | data[index + 3] << 0 & 0xFF;
  }
  
  public static String getString(char[] data, int index, int count) {
    return String.valueOf(data, index, count);
  }
  
  private static final class MessageType {
    public static final int BIOMES = 1;
    
    public static final int CHAT_MESSAGE = 2;
    
    public static final int PLAYER = 3;
    
    public static final int GENERATION_POGRESS = 4;
  }
}
