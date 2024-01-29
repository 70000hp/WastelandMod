package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.utils.Schematic;
import java.util.ArrayList;
import java.util.List;

public class SchematicBuilding {
  String name;
  
  Schematic[] top;
  
  Schematic[] middle;
  
  Schematic[] bottom;
  
  int width;
  
  int length;
  
  boolean initialized;
  
  public static final String largeTowerA = "LTA";
  
  public static final String largeTowerB = "LTB";
  
  public static final String largeTowerC = "LTC";
  
  public static final String largeTowerD = "LTD";
  
  public static final String largeTowerE = "LTE";
  
  public static final String largeTowerF = "LTF";
  
  public static final String midTowerA = "MAA";
  
  public static final String midTowerB = "MAB";
  
  public static final String midTowerC = "MAC";
  
  public static final String midTowerD = "MAD";
  
  public static final String midTowerE = "MAE";
  
  public static final String midChurchA = "MCA";
  
  public static final String smallTowerA = "STA";
  
  public static final String smallTowerB = "STB";
  
  public static final String smallTowerC = "STC";
  
  public static final String smallStoreA = "SSA";
  
  public static final String smallStoreB = "SSB";
  
  public static final String NA = "none";
  
  public SchematicBuilding(String name) {
    this.name = name;
    this.width = 0;
    this.length = 0;
    this.top = null;
    this.middle = null;
    this.bottom = null;
    this.initialized = false;
  }
  
  public SchematicBuilding addSchematic(Schematic s, int layer) {
    if (layer == 0) {
      this.bottom = addSchematic(this.bottom, s);
      this.initialized = true;
    } else if (layer == 1) {
      this.middle = addSchematic(this.middle, s);
    } else if (layer == 2) {
      this.top = addSchematic(this.top, s);
    } 
    return this;
  }
  
  public SchematicBuilding addBottomSchematic(Schematic s) {
    return addSchematic(s, 0);
  }
  
  public SchematicBuilding addMiddleSchematic(Schematic s) {
    return addSchematic(s, 1);
  }
  
  public SchematicBuilding addTopSchematic(Schematic s) {
    return addSchematic(s, 2);
  }
  
  private Schematic[] addSchematic(Schematic[] schematicArray, Schematic schematic) {
    if (schematicArray == null)
      return new Schematic[] { schematic }; 
    Schematic[] out = new Schematic[schematicArray.length + 1];
    for (int i = 0; i < schematicArray.length; i++)
      out[i] = schematicArray[i]; 
    out[schematicArray.length] = schematic;
    return out;
  }
  
  public void setDimensions() {
    if (this.initialized) {
      this.width = (this.bottom[0]).width;
      this.length = (this.bottom[0]).length;
    } 
  }
  
  public void setDimensions(int w, int l) {
    this.width = w;
    this.length = l;
  }
  
  public static List<SchematicBuilding> loadAllBuildings() {
    List<SchematicBuilding> buildingList = new ArrayList<SchematicBuilding>();
    buildingList.add((new SchematicBuilding("LTA")).addBottomSchematic(new Schematic("LTA_bottom")).addMiddleSchematic(new Schematic("LTA_middle")).addTopSchematic(new Schematic("LTA_top")));
    buildingList.add((new SchematicBuilding("LTB")).addBottomSchematic(new Schematic("LTB_bottom")).addMiddleSchematic(new Schematic("LTB_middle")).addTopSchematic(new Schematic("LTB_topA")).addTopSchematic(new Schematic("LTB_topB")).addTopSchematic(new Schematic("LTB_topC")));
    buildingList.add((new SchematicBuilding("LTC")).addBottomSchematic(new Schematic("LTC_bottom")).addMiddleSchematic(new Schematic("LTC_middle")).addTopSchematic(new Schematic("LTC_top")));
    buildingList.add((new SchematicBuilding("LTD")).addBottomSchematic(new Schematic("LTD_bottom")).addMiddleSchematic(new Schematic("LTD_middle")).addTopSchematic(new Schematic("LTD_top")));
    buildingList.add((new SchematicBuilding("LTE")).addBottomSchematic(new Schematic("LTE_bottom")).addMiddleSchematic(new Schematic("LTE_middle")).addTopSchematic(new Schematic("LTE_top")));
    buildingList.add((new SchematicBuilding("LTF")).addBottomSchematic(new Schematic("LTF_bottom")).addMiddleSchematic(new Schematic("LTF_middle")).addTopSchematic(new Schematic("LTF_top")));
    buildingList.add((new SchematicBuilding("MAA")).addBottomSchematic(new Schematic("MAA_bottom")).addMiddleSchematic(new Schematic("MAA_middle")).addTopSchematic(new Schematic("MAA_topA")).addTopSchematic(new Schematic("MAA_topB")));
    buildingList.add((new SchematicBuilding("MAB")).addBottomSchematic(new Schematic("MAB_bottom")).addMiddleSchematic(new Schematic("MAB_middle")).addTopSchematic(new Schematic("MAB_top")));
    buildingList.add((new SchematicBuilding("MAC")).addBottomSchematic(new Schematic("MAC_bottom")).addMiddleSchematic(new Schematic("MAC_middle")).addTopSchematic(new Schematic("MAC_top")));
    buildingList.add((new SchematicBuilding("MAD")).addBottomSchematic(new Schematic("MAD_bottom")).addMiddleSchematic(new Schematic("MAD_middle")).addTopSchematic(new Schematic("MAD_top")));
    buildingList.add((new SchematicBuilding("MAE")).addBottomSchematic(new Schematic("MAE_bottom")).addMiddleSchematic(new Schematic("MAE_middle")).addTopSchematic(new Schematic("MAE_top")));
    buildingList.add((new SchematicBuilding("MCA")).addBottomSchematic(new Schematic("MCA")));
    buildingList.add((new SchematicBuilding("STA")).addBottomSchematic(new Schematic("STA_bottom")).addMiddleSchematic(new Schematic("STA_middle")).addTopSchematic(new Schematic("STA_top")));
    buildingList.add((new SchematicBuilding("STB")).addBottomSchematic(new Schematic("STB_bottom")).addMiddleSchematic(new Schematic("STB_middle")).addTopSchematic(new Schematic("STB_top")));
    buildingList.add((new SchematicBuilding("STC")).addBottomSchematic(new Schematic("STC_bottom")).addMiddleSchematic(new Schematic("STC_middle")).addTopSchematic(new Schematic("STC_top")));
    buildingList.add((new SchematicBuilding("SSA")).addBottomSchematic(new Schematic("SSA")));
    buildingList.add((new SchematicBuilding("SSB")).addBottomSchematic(new Schematic("SSB")));
    return buildingList;
  }
}
