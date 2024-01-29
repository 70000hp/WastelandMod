

package myid.chiqors.wasteland;

import net.minecraft.util.MathHelper;

public class ModHelper {
  public static final float pixel = 0.0625F;
  
  public class Directions {
    public static final int SOUTH = 0;
    
    public static final int WEST = 1;
    
    public static final int NORTH = 2;
    
    public static final int EAST = 3;
  }
  
  public static class ModInfo {
    public static final String modid = "WLM";
    
    public static final String name = "The Wasteland Mod";
    
    public static final String version = "1.4.2";
    
    public static final boolean isDev = "1.4.2".contains("DEV");
  }
  
  public static class ResourceInfo {
    public static final String schematicDomain = "assets/wlm/schematics/";
  }
  
  public static String getCoordAsString(int x, int y, int z) {
    return String.valueOf(x + ", " + y + ", " + z);
  }
  
  public static String getCoordAsString(int x, int z) {
    return String.valueOf(x + ", " + z);
  }
  
  public static int getEntityRotationAsDirection(float rotationYaw) {
    return MathHelper.floor_double((rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
  }
}
