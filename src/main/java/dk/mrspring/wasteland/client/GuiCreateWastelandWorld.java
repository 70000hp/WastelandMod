package dk.mrspring.wasteland.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.ruin.Ruin;
import dk.mrspring.wasteland.world.gen.WastelandGeneratorInfo;

public class GuiCreateWastelandWorld extends GuiScreen
{
	private static RenderItem itemRenderer = new RenderItem();
	private final GuiCreateWorld createWorldGui;
	private String title;
	private String ruin;
	private String rarity;
	private GuiButton increaseRarity;
	private GuiButton decreaseRarity;
	private WastelandGeneratorInfo generatorInfo;
	private GuiCreateWastelandWorld.Details ruinListSlotGui;
	
	public GuiCreateWastelandWorld(GuiCreateWorld par1, String par2, WastelandGeneratorInfo info)
	{
		this.createWorldGui = par1;
		this.generatorInfo = info;
	}
	
	public void initGui()
	{
		this.buttonList.clear();
		this.title = StatCollector.translateToLocal("createworld.customize.wasteland.title");
		this.ruin = StatCollector.translateToLocal("createworld.customize.wasteland.ruin");
		this.rarity = StatCollector.translateToLocal("createworld.customize.wasteland.rarity");
		this.ruinListSlotGui = new GuiCreateWastelandWorld.Details();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.translateToLocal("gui.done")));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.translateToLocal("gui.cancel")));
		this.buttonList.add(new GuiButton(4, this.width / 2 + 5, this.height - 52, 150, 20, StatCollector.translateToLocal("createworld.customize.wasteland.default")));
		
		this.buttonList.add(this.increaseRarity = new GuiButton(2, this.width / 2 -  79, this.height - 52, 74, 20, StatCollector.translateToLocal("createworld.customize.wasteland.rarity.increase")));
		this.buttonList.add(this.decreaseRarity = new GuiButton(3, this.width / 2 - 155, this.height - 52, 74, 20, StatCollector.translateToLocal("createworld.customize.wasteland.rarity.decrease")));
		
		this.generatorInfo = new WastelandGeneratorInfo();
		if(this.createWorldGui.field_146334_a == "")
			this.generatorInfo.createDefault();
		else
			this.generatorInfo.setComplete(this.createWorldGui.field_146334_a);
		this.func_146375_g();
	}
	
	public void func_146375_g()
	{
		boolean bool = this.isOptionClicked();
		this.increaseRarity.enabled = bool;
		this.decreaseRarity.enabled = bool;
	}
	
	private boolean isOptionClicked()
	{
		if(this.ruinListSlotGui.selectedSlot != -1 && !(this.ruinListSlotGui.selectedSlot > this.generatorInfo.getRarities().length))
			return true;
		else return false;
	}
	
	protected void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			this.createWorldGui.field_146334_a = this.generatorInfo.getFinal();
			this.mc.displayGuiScreen(this.createWorldGui);
		}
		else if(button.id == 1)
			this.mc.displayGuiScreen(this.createWorldGui);
		else if(button.id == 2)
			if(this.ruinListSlotGui.selectedSlot != -1 && !(this.ruinListSlotGui.selectedSlot > this.generatorInfo.getRarities().length))
				this.generatorInfo.setRarity(this.ruinListSlotGui.selectedSlot, this.generatorInfo.getRarity(this.ruinListSlotGui.selectedSlot) + 1);
			else ;
		else if(button.id == 3)
			if(this.ruinListSlotGui.selectedSlot != -1 && !(this.ruinListSlotGui.selectedSlot > this.generatorInfo.getRarities().length))
				this.generatorInfo.setRarity(this.ruinListSlotGui.selectedSlot, this.generatorInfo.getRarity(this.ruinListSlotGui.selectedSlot) - 1);
			else ;
		else if(button.id == 4)
			this.generatorInfo.createDefault();
		
		this.func_146375_g();
	}
	
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		this.ruinListSlotGui.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
		int i = this.width / 2 - 92 - 16;
		this.drawString(this.fontRendererObj, this.ruin, i, 32, 16777215);
		this.drawString(this.fontRendererObj, this.rarity, i + 2 + 213 - this.fontRendererObj.getStringWidth(this.rarity), 32, 16777215);
		super.drawScreen(par1, par2, par3);
	}
	
	@SideOnly(Side.CLIENT)
	class Details extends GuiSlot 
	{
		public int selectedSlot = -1;
		
		public Details()
		{
			super(GuiCreateWastelandWorld.this.mc, GuiCreateWastelandWorld.this.width, GuiCreateWastelandWorld.this.height, 43, GuiCreateWastelandWorld.this.height - 60, 24);
		}
		
		@Override
		protected int getSize()
		{
			return GuiCreateWastelandWorld.this.generatorInfo.getRarities().length;
		}
		
		@Override
		protected void elementClicked(int par1, boolean par2, int par3, int par4)
		{
			this.selectedSlot = par1;
			GuiCreateWastelandWorld.this.func_146375_g();
		}
		
		@Override
		protected boolean isSelected(int par1)
		{
			return par1 == this.selectedSlot;
		}
		
		@Override
		protected void drawBackground() { }
		
		@Override
		protected void drawSlot(int var1, int x, int y, int var4, Tessellator var5, int var6, int var7)
		{
			//String name = Ruin.ruins[var1].getLocalizedName();
			//GuiCreateWastelandWorld.this.fontRendererObj.drawString(name, x + 5, y + 6, 16777215);
			
			String rarity = String.valueOf(GuiCreateWastelandWorld.this.generatorInfo.getRarity(var1));
			
			if(GuiCreateWastelandWorld.this.generatorInfo.getRarity(var1) == 0) rarity = StatCollector.translateToLocal("createworld.customize.wasteland.disabled");
			
			GuiCreateWastelandWorld.this.fontRendererObj.drawString(rarity, x + 2 + 213 - GuiCreateWastelandWorld.this.fontRendererObj.getStringWidth(rarity), y + 6, 16777215);
		}
		
		@Override
		public void drawScreen(int p_148128_1_, int p_148128_2_, float p_148128_3_)
		{
			super.drawScreen(p_148128_1_, p_148128_2_, p_148128_3_);
		}
		
		@Override
		protected int getScrollBarX()
		{
			return this.width - 70;
		}
	}
}
