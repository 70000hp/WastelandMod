package com.seventythousand.wasteland.world;

import com.seventythousand.wasteland.utils.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jnbt.CompoundTag;
import org.jnbt.IntTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

public class WastelandWordData {
  private File file;

  public WastelandWordData(String filename) {
    this.file = new File(filename);
  }

  public boolean checkIfExists() {
    return this.file.exists();
  }

  public void createFile() {
    Map<String, Tag> numVillages = new HashMap<String, Tag>();
    Map<String, Tag> villages = new HashMap<String, Tag>();
    numVillages.put("Total", new IntTag("Total", 0));
    CompoundTag villagesTag = new CompoundTag("Villages", numVillages);
    villages.put("Villages", villagesTag);
    CompoundTag globalTag = new CompoundTag("WastelandMod", villages);
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

  public void saveVillageData(Vector villagePosition, int villageID) {
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> parentList = new ArrayList<String>();
    List<String> tagName = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream(this.file);
      NBTInputStream nbt = new NBTInputStream(fis);
      CompoundTag oldGlobalTag = (CompoundTag)nbt.readTag();
      nbt.close();
      fis.close();
      Map<String, Tag> location = new HashMap<String, Tag>();
      location.put("X", new IntTag("X", villagePosition.X));
      location.put("Y", new IntTag("Y", villagePosition.Y));
      location.put("Z", new IntTag("Z", villagePosition.Z));
      CompoundTag tag = new CompoundTag(String.valueOf(villageID), location);
      tagList.add(tag);
      parentList.add("Villages");
      tagName.add(String.valueOf(villageID));
      tagList.add(new IntTag("Total", villageID));
      parentList.add("Villages");
      tagName.add("Total");
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

  private Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
      return items.get(key);
  }
}
