

package com.seventythousand.wasteland;

import com.seventythousand.wasteland.gui.ProgressGui;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class GuiEventHandler {
  public static boolean visible = true;

  @SideOnly(Side.CLIENT)
  public static Minecraft client = FMLClientHandler.instance().getClient();

  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRenderProgressBar(RenderGameOverlayEvent.Text event) {
    if (client.inGameHasFocus || client.currentScreen instanceof net.minecraft.client.gui.GuiChat) {
      ScaledResolution scaledresolution = event.resolution;
      ProgressGui.drawProgressBar(client.fontRenderer, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
    }
  }
}
