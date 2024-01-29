package myid.chiqors.wasteland.world.gen;

public class WastelandGeneratorInfo {
  private static int ruins;
  
  private static String info;
  
  private static String[] rarities;
  
  public static boolean hasMadeStrings;
  
  public static void createDefault() {
    StringBuilder builder = new StringBuilder();
    info = builder.toString();
    rarities = info.split(",");
    hasMadeStrings = true;
  }
  
  public void setComplete(String str) {
    StringBuilder builder = new StringBuilder();
    builder.append(str);
    this;
    ruins = (str.split(",")).length;
    info = builder.toString();
    rarities = info.split(",");
    hasMadeStrings = true;
  }
  
  public String[] getRarities() {
    if (!hasMadeStrings)
      createDefault(); 
    return rarities;
  }
  
  public static int getRarity(int id) {
    if (!hasMadeStrings)
      createDefault(); 
    return Integer.parseInt(rarities[id]);
  }
  
  public void setRarity(int id, int rarity) {
    if (!hasMadeStrings)
      createDefault(); 
    if (rarity > -1 && 
      id <= rarities.length) {
      this;
      rarities[id] = String.valueOf(rarity);
    } 
  }
  
  public String getFinal() {
    if (!hasMadeStrings)
      createDefault(); 
    StringBuilder builder = new StringBuilder();
    return builder.toString();
  }
  
  public static int getRarityFromStringAndID(String string, int id) {
    if (!hasMadeStrings)
      createDefault(); 
    return Integer.parseInt(string.split(",")[id]);
  }
}
