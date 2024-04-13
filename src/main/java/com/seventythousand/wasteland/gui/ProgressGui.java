

package com.seventythousand.wasteland.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class ProgressGui extends Gui {
  public static float progress = 0.0F;

  public static boolean visible = false;

  public static void drawProgressBar(FontRenderer font, int width, int height) {
    if (visible) {
      drawCircle(progress, width / 2.0D + 0.5D, height / 2.0D + 0.5D, 12, 10, -2130771794);
      font.drawString("Generating...", width / 2 - 28, height / 2 - 22, -1, true);
    }
  }

  public static void drawCircle(float progress, double x, double y, int rOuter, int rInner, int colour) {
    double angleInc = 0.06283185307179587D;
    float f3 = (colour >> 24 & 0xFF) / 255.0F;
    float f = (colour >> 16 & 0xFF) / 255.0F;
    float f1 = (colour >> 8 & 0xFF) / 255.0F;
    float f2 = (colour & 0xFF) / 255.0F;
    Tessellator tessellator = Tessellator.instance;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    GL11.glColor4f(f, f1, f2, f3);
    tessellator.startDrawingQuads();
    double[] rX = new double[4];
    double[] rY = new double[4];
    int count = 0;
    double angle = 0.0D;
    double angleOffset = -1.5707963267948966D;
    while (angle < (progress * 2.0F) * Math.PI - angleInc) {
      rX[0] = Math.cos(angle + angleOffset) * rOuter;
      rY[0] = Math.sin(angle + angleOffset) * rOuter;
      rX[1] = Math.cos(angle + angleInc + angleOffset) * rOuter;
      rY[1] = Math.sin(angle + angleInc + angleOffset) * rOuter;
      rX[2] = Math.cos(angle + angleInc + angleOffset) * rInner;
      rY[2] = Math.sin(angle + angleInc + angleOffset) * rInner;
      rX[3] = Math.cos(angle + angleOffset) * rInner;
      rY[3] = Math.sin(angle + angleOffset) * rInner;
      tessellator.addVertex(rX[0] + x, rY[0] + y, 0.0D);
      tessellator.addVertex(rX[3] + x, rY[3] + y, 0.0D);
      tessellator.addVertex(rX[2] + x, rY[2] + y, 0.0D);
      tessellator.addVertex(rX[1] + x, rY[1] + y, 0.0D);
      angle += angleInc;
      count++;
    }
    tessellator.draw();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
  }
}
