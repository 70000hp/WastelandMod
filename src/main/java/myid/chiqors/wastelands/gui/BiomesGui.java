package myid.chiqors.wastelands.gui;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomesGui extends GuiScreen
{
	public static final int GUI_ID = 0;
	private int[][] map;
	private int mapSize = 0;
	private List<BiomeGenBase> biomesList;
	private int shiftLeftPercent;
	
	public BiomesGui(int[][] map, int size, List<BiomeGenBase> biomes)
	{
		super();
		this.map = map;
		this.mapSize = size;
		this.biomesList = biomes;
		this.shiftLeftPercent = 10;
		//System.out.println("opening gui");
	}
	
	@Override
	public void initGui()
	{
		
	}
	
	@Override
	public void drawScreen(int i, int j, float f)
	{
		if (this.map != null || this.mapSize > 0)
		{
			int pixelSize = (int)(0.85 * height/mapSize);
			drawDefaultBackground();
			drawBiomes(pixelSize);
			drawBiomeNames(this.biomesList, pixelSize);
		}
		super.drawScreen(i, j, f);
	}
	
	private void drawBiomeNames(List<BiomeGenBase> biomes, int pixelWidth)
	{
		int shiftX = (int)((this.shiftLeftPercent/100.0) * this.width);
		FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
		int width = ((pixelWidth & 1) == 0) ? pixelWidth : (int)(pixelWidth/2.0);
		
		int x = (int)(0.77 * this.width); //+ (int)((width*this.mapSize)/2.0) + 5;
		int y = (int)(0.1 * this.height); //- (int)((width*this.mapSize)/2.0);
		
		for (int i = 0; i < biomes.size(); i++)
		{
			this.drawString(font, biomes.get(i).biomeName, x - shiftX, y + (i*(font.FONT_HEIGHT + 2)), 
					(biomes.get(i).color & 0x00FFFFFF) + 0xFF000000);
		}
	}

	private void drawBiomes(int pixelWidth)
	{
		int centerX;
		int centerY;
		int shiftX = (int)((this.shiftLeftPercent/100.0) * this.width);
		
		if ((this.mapSize & 1) == 0)
		{
			centerX = this.width / 2;
			centerY = this.height / 2;
		}
		else
		{
			centerX = (this.width - pixelWidth) / 2;
			centerY = (this.height - pixelWidth) / 2;
		}
		
		for (int j = 0; j < mapSize; j++)
		{
			for (int i = 0; i < mapSize; i++)
			{
				drawRect(centerX - (this.mapSize/2)*pixelWidth + (i*pixelWidth) - shiftX,
						centerY - (this.mapSize/2)*pixelWidth + (j*pixelWidth),
						centerX - (this.mapSize/2)*pixelWidth + ((i+1)*pixelWidth) - shiftX,
						centerY - (this.mapSize/2)*pixelWidth + ((j+1)*pixelWidth), 
						this.map[j][i]);
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	@Override
	public void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
	}
	
	@Override
	public void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
	}
	
	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}
	
	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
	}
	
	@Override
	public void actionPerformed(GuiButton b)
	{
		super.actionPerformed(b);
	}
}
