package myid.chiqors.wasteland.world;

import myid.chiqors.wasteland.Wasteland;
import myid.chiqors.wasteland.utils.Schematic;
import myid.chiqors.wasteland.utils.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.IntTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.ShortTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;

public class WastelandWorldData {
  private File file;
  
  public WastelandWorldData(String filename) {
    this.file = new File(filename);
  }
  
  public WastelandWorldData() {
    this.file = null;
  }
  
  public void setFile(String filename) {
    this.file = new File(filename);
  }
  
  public boolean checkIfExists() {
    return this.file.exists();
  }
  
  public void createFile() {
    Map<String, Tag> numVillages = new HashMap<String, Tag>();
    Map<String, Tag> numCities = new HashMap<String, Tag>();
    Map<String, Tag> global = new HashMap<String, Tag>();
    Map<String, Tag> playerNames = new HashMap<String, Tag>();
    numVillages.put("Total", new IntTag("Total", 0));
    numCities.put("Total", new IntTag("Total", 0));
    CompoundTag villagesTag = new CompoundTag("Villages", numVillages);
    CompoundTag citiesTag = new CompoundTag("Cities", numVillages);
    CompoundTag playersTag = new CompoundTag("Players", playerNames);
    global.put("Villages", villagesTag);
    global.put("Cities", citiesTag);
    global.put("Players", playersTag);
    CompoundTag globalTag = new CompoundTag("WastelandMod", global);
    try {
      this.file.createNewFile();
      FileOutputStream fos = new FileOutputStream(this.file);
      NBTOutputStream nos = new NBTOutputStream(fos);
      nos.writeTag((Tag)globalTag);
      nos.close();
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public List<Vector> loadVillageData() {
    List<Vector> villagePos = new ArrayList<Vector>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      Map<String, Tag> villageCollection = ((CompoundTag)getChildTag(((CompoundTag)nbt.readTag()).getValue(), "Villages", (Class)CompoundTag.class)).getValue();
      nbt.close();
      fis.close();
      for (String villageID : villageCollection.keySet()) {
        if (!villageID.equals("Total")) {
          Map<String, Tag> village = ((CompoundTag)getChildTag(villageCollection, villageID, (Class)CompoundTag.class)).getValue();
          villagePos.add(new Vector(((Integer)getChildTag(village, "X", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(village, "Y", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(village, "Z", (Class)IntTag.class).getValue()).intValue()));
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return villagePos;
  }
  
  public void saveVillageData(List<Vector> villagePosition) {
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> parentList = new ArrayList<String>();
    List<String> tagName = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      CompoundTag oldGlobalTag = (CompoundTag)nbt.readTag();
      nbt.close();
      fis.close();
      for (int i = 0; i < villagePosition.size(); i++) {
        Map<String, Tag> location = new HashMap<String, Tag>();
        location.put("X", new IntTag("X", ((Vector)villagePosition.get(i)).X));
        location.put("Y", new IntTag("Y", ((Vector)villagePosition.get(i)).Y));
        location.put("Z", new IntTag("Z", ((Vector)villagePosition.get(i)).Z));
        CompoundTag tag = new CompoundTag(String.valueOf(i + 1), location);
        tagList.add(tag);
        parentList.add("Villages");
        tagName.add(String.valueOf(i + 1));
      } 
      tagList.add(new IntTag("Total", villagePosition.size()));
      parentList.add("Villages");
      tagName.add(String.valueOf("Total"));
      CompoundTag newGlobalTag = addTags(oldGlobalTag, parentList, tagList, tagName);
      FileOutputStream fos = new FileOutputStream(this.file);
      NBTOutputStream nos = new NBTOutputStream(fos);
      nos.writeTag((Tag)new CompoundTag("WastelandMod", newGlobalTag.getValue()));
      nos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public List<Vector> loadCityData() {
    List<Vector> cityPos = new ArrayList<Vector>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      Map<String, Tag> cityCollection = ((CompoundTag)getChildTag(((CompoundTag)nbt.readTag()).getValue(), "Cities", (Class)CompoundTag.class)).getValue();
      nbt.close();
      fis.close();
      for (String cityID : cityCollection.keySet()) {
        if (!cityID.equals("Total")) {
          Map<String, Tag> city = ((CompoundTag)getChildTag(cityCollection, cityID, (Class)CompoundTag.class)).getValue();
          cityPos.add(new Vector(((Integer)getChildTag(city, "X", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(city, "Y", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(city, "Z", (Class)IntTag.class).getValue()).intValue()));
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return cityPos;
  }
  
  public void saveCityData(List<Vector> cityPosition) {
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> parentList = new ArrayList<String>();
    List<String> tagName = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      CompoundTag oldGlobalTag = (CompoundTag)nbt.readTag();
      nbt.close();
      fis.close();
      for (int i = 0; i < cityPosition.size(); i++) {
        Map<String, Tag> location = new HashMap<String, Tag>();
        location.put("X", new IntTag("X", ((Vector)cityPosition.get(i)).X));
        location.put("Y", new IntTag("Y", ((Vector)cityPosition.get(i)).Y));
        location.put("Z", new IntTag("Z", ((Vector)cityPosition.get(i)).Z));
        CompoundTag tag = new CompoundTag(String.valueOf(i + 1), location);
        tagList.add(tag);
        parentList.add("Cities");
        tagName.add(String.valueOf(i + 1));
      } 
      tagList.add(new IntTag("Total", cityPosition.size()));
      parentList.add("Cities");
      tagName.add(String.valueOf("Total"));
      CompoundTag newGlobalTag = addTags(oldGlobalTag, parentList, tagList, tagName);
      FileOutputStream fos = new FileOutputStream(this.file);
      NBTOutputStream nos = new NBTOutputStream(fos);
      nos.writeTag((Tag)new CompoundTag("WastelandMod", newGlobalTag.getValue()));
      nos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public List<String> getPlayerNames() {
    List<String> names = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      Map<String, Tag> playerCollection = ((CompoundTag)getChildTag(((CompoundTag)nbt.readTag()).getValue(), "Players", (Class)CompoundTag.class)).getValue();
      nbt.close();
      fis.close();
      for (String PlayerName : playerCollection.keySet())
        names.add(PlayerName); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return names.isEmpty() ? null : names;
  }
  
  public void savePlayerNames(List<String> names) {
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> parentList = new ArrayList<String>();
    List<String> tagName = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      CompoundTag oldGlobalTag = (CompoundTag)nbt.readTag();
      nbt.close();
      fis.close();
      for (int i = 0; i < names.size(); i++) {
        StringTag tag = new StringTag(names.get(i), names.get(i));
        tagList.add(tag);
        parentList.add("Players");
        tagName.add(String.valueOf(names.get(i)));
      } 
      CompoundTag newGlobalTag = addTags(oldGlobalTag, parentList, tagList, tagName);
      FileOutputStream fos = new FileOutputStream(this.file);
      NBTOutputStream nos = new NBTOutputStream(fos);
      nos.writeTag((Tag)new CompoundTag("WastelandMod", newGlobalTag.getValue()));
      nos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void savePlayerName(String name) {
    List<String> names = new ArrayList<String>();
    names.add(name);
    savePlayerNames(names);
  }
  
  private CompoundTag addTags(CompoundTag globalTag, List<String> parentTag, List<Tag> tagMap, List<String> tagName) {
    Map<String, Tag> tagCollection = globalTag.getValue();
    Set<String> keys = tagCollection.keySet();
    Map<String, Tag> newTagCollection = new HashMap<String, Tag>();
    for (String key : keys) {
      Class<? extends Tag> t = (Class)((Tag)tagCollection.get(key)).getClass();
      if (t.getName().endsWith("StringTag")) {
        newTagCollection.put(key, new StringTag(key, (String)((Tag)tagCollection.get(key)).getValue()));
        continue;
      } 
      if (t.getName().endsWith("IntTag")) {
        newTagCollection.put(key, new IntTag(key, ((Integer)((Tag)tagCollection.get(key)).getValue()).intValue()));
        continue;
      } 
      if (t.getName().endsWith("CompoundTag")) {
        newTagCollection.put(key, addTags((CompoundTag)tagCollection.get(key), parentTag, tagMap, tagName));
        continue;
      } 
      System.out.println("Unknown Tag Class: " + t.getName());
    } 
    for (int i = 0; i < tagMap.size(); i++) {
      if (globalTag.getName().equals(parentTag.get(i)))
        newTagCollection.put(tagName.get(i), tagMap.get(i)); 
    } 
    return new CompoundTag(globalTag.getName(), newTagCollection);
  }
  
  private CompoundTag copyAllTags(CompoundTag tag) {
    Map<String, Tag> tagCollection = tag.getValue();
    Set<String> keys = tagCollection.keySet();
    Map<String, Tag> newTagCollection = new HashMap<String, Tag>();
    for (String key : keys) {
      Class<? extends Tag> t = (Class)((Tag)tagCollection.get(key)).getClass();
      if (t.getName().endsWith("StringTag")) {
        newTagCollection.put(key, new StringTag(key, (String)((Tag)tagCollection.get(key)).getValue()));
        continue;
      } 
      if (t.getName().endsWith("IntTag")) {
        newTagCollection.put(key, new IntTag(key, ((Integer)((Tag)tagCollection.get(key)).getValue()).intValue()));
        continue;
      } 
      if (t.getName().endsWith("CompoundTag")) {
        newTagCollection.put(key, copyAllTags((CompoundTag)tagCollection.get(key)));
        continue;
      } 
      System.out.println("Unknown Tag Class: " + t.getName());
    } 
    return new CompoundTag(tag.getName(), newTagCollection);
  }
  
  private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
    Tag tag = items.get(key);
    return tag;
  }
  
  public void saveSpawnLoc(Vector spawn) {
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> parentList = new ArrayList<String>();
    List<String> tagName = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      CompoundTag oldGlobalTag = (CompoundTag)nbt.readTag();
      nbt.close();
      fis.close();
      Map<String, Tag> spawnTag = new HashMap<String, Tag>();
      spawnTag.put("spawnX", new IntTag("spawnX", spawn.X));
      spawnTag.put("spawnY", new IntTag("spawnY", spawn.Y));
      spawnTag.put("spawnZ", new IntTag("spawnZ", spawn.Z));
      CompoundTag tag = new CompoundTag("Spawn", spawnTag);
      tagList.add(tag);
      parentList.add("WastelandMod");
      tagName.add(String.valueOf("Spawn"));
      CompoundTag newGlobalTag = addTags(oldGlobalTag, parentList, tagList, tagName);
      FileOutputStream fos = new FileOutputStream(this.file);
      NBTOutputStream nos = new NBTOutputStream(fos);
      nos.writeTag((Tag)new CompoundTag("WastelandMod", newGlobalTag.getValue()));
      nos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public Vector loadSpawnLoc() {
    Vector spawn = null;
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      Map<String, Tag> spawnLoc = ((CompoundTag)getChildTag(((CompoundTag)nbt.readTag()).getValue(), "Spawn", (Class)CompoundTag.class)).getValue();
      nbt.close();
      fis.close();
      spawn = new Vector(((Integer)getChildTag(spawnLoc, "spawnX", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(spawnLoc, "spawnY", (Class)IntTag.class).getValue()).intValue(), ((Integer)getChildTag(spawnLoc, "spawnZ", (Class)IntTag.class).getValue()).intValue());
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return spawn;
  }
  
  public static void loadSchematic(InputStream is, Schematic s) throws IOException {
    NBTInputStream nbt = new NBTInputStream(is);
    CompoundTag globalTag = (CompoundTag)nbt.readTag();
    nbt.close();
    is.close();
    Map<String, Tag> tagCollection = globalTag.getValue();
    short width = ((Short)getChildTag(tagCollection, "Width", (Class)ShortTag.class).getValue()).shortValue();
    short length = ((Short)getChildTag(tagCollection, "Length", (Class)ShortTag.class).getValue()).shortValue();
    short height = ((Short)getChildTag(tagCollection, "Height", (Class)ShortTag.class).getValue()).shortValue();
    byte[] blocks = (byte[])getChildTag(tagCollection, "Blocks", (Class)ByteArrayTag.class).getValue();
    byte[] data = (byte[])getChildTag(tagCollection, "Data", (Class)ByteArrayTag.class).getValue();
    s.load(width, length, height, blocks, data);
  }
  
  public static void loadSchematic(String s, Schematic sch) throws IOException {
    loadSchematic(Wasteland.class.getClassLoader().getResourceAsStream("assets/wlm/schematics/" + s), sch);
  }
}
