package dk.mrspring.wasteland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import dk.mrspring.wasteland.gui.BiomesGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GetBiomesCommand extends CommandBase 
{
	private int minSize = 200;
	
	public GetBiomesCommand ()
	{
		super();
	}
	
	@Override
	public String getCommandName() 
	{
		return "biomes";
	}

	@Override
	public String getCommandUsage(ICommandSender iCommandSender) 
	{
		return "/biomes <range> (min range is "+ String.valueOf(this.minSize)+")";
	}

	@Override
	public void processCommand(ICommandSender iCommandSender, String[] var) 
	{
		if (iCommandSender instanceof EntityPlayer) 
		{
			 EntityPlayer player = (EntityPlayer)iCommandSender;
	         World world = player.worldObj;
	         
	         if (var.length > 0)
	         {
		         int range = Math.max(Integer.parseInt(var[0]),minSize);
		         int interval = (int)(range / minSize);
		         
		         //System.out.println(String.valueOf(size));
		         int[][] biomeMap = new int[minSize][minSize];
		         Random rand = new Random();
		         player.addChatMessage(new ChatComponentText("Creating map..."));
		         List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
		         BiomeGenBase currentBiome;
		         float count = 0;
		         for (int j = 0; j < minSize; j++)
		         {
		        	 for (int i = 0; i < minSize; i++)
			         {
		        		 currentBiome = world.getBiomeGenForCoords((int)(player.posX - (minSize*interval/2) + i*interval),
		        				 (int)(player.posZ - (minSize*interval/2) + j*interval));
		        		 
		        		 if (!biomes.contains(currentBiome))
		        		 {
		        			 biomes.add(currentBiome);
		        		 }
		        		 biomeMap[j][i] = getBiomeColour(currentBiome) + 0xFF000000;
			         }
		        	 if (count/minSize > 0.1)
		        	 {
		        		 player.addChatMessage(new ChatComponentText(String.valueOf((int)(100 * j/minSize)) + "%"));
		        		 count = 0;
		        	 }
		        	 count++;
		         }
		         
		         FMLClientHandler.instance().getClient().displayGuiScreen(new BiomesGui(biomeMap, 200, biomes));
	         }
		}
		
	}

	private int getBiomeColour(BiomeGenBase biomeGenBase)
	{
		if (biomeGenBase != null)
		{
			return (biomeGenBase.color & 0x00FFFFFF);
		}
		else
		{
			return 0x00000000;
		}
	}
	

}
