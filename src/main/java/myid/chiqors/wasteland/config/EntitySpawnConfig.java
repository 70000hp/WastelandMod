

package myid.chiqors.wasteland.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Configuration;

public class EntitySpawnConfig {
  private static final String WASTELAND = "wasteland";
  
  private static final String MOUNTAINS = "mountains";
  
  private static final String FOREST = "forest";
  
  private static final String CITY = "city";
  
  public static List<List<BiomeGenBase.SpawnListEntry>> wastelandCreatures;
  
  public static List<List<BiomeGenBase.SpawnListEntry>> forestCreatures;
  
  public static List<List<BiomeGenBase.SpawnListEntry>> mountainsCreatures;
  
  public static List<List<BiomeGenBase.SpawnListEntry>> cityCreatures;
  
  public static boolean enableHostileSpawn;
  
  public static boolean enablePassiveSpawn;
  
  public static boolean enableWaterSpawn;
  
  public static void load(Configuration config) {
    List<List<BiomeGenBase.SpawnListEntry>> creatures = getAllBiomeEntities();
    addRemainingCreatures(creatures);
    List<BiomeGenBase.SpawnListEntry> hostileCreatures = creatures.get(0);
    List<BiomeGenBase.SpawnListEntry> passiveCreatures = creatures.get(1);
    List<BiomeGenBase.SpawnListEntry> waterCreatures = creatures.get(2);
    wastelandCreatures = copyArrayList(creatures);
    forestCreatures = copyArrayList(creatures);
    mountainsCreatures = copyArrayList(creatures);
    cityCreatures = copyArrayList(creatures);
    Map<String, Object> entities = EntityList.classToStringMapping;
    config.load();
    config.setCategoryComment("General", "");
    enableHostileSpawn = config.get("General", "Enable hostile creatures", true).getBoolean(true);
    enablePassiveSpawn = config.get("General", "Enable passive creatures", false).getBoolean(false);
    enableWaterSpawn = config.get("General", "Enable water creatures", false).getBoolean(false);
    String category = "Hostile Creature List";
    config.setCategoryComment(category, "Class name = wasteland biome, spawnWeight, minGroup, maxGorup ...  --- do not use spaces!");
    int i;
    for (i = 0; i < hostileCreatures.size(); i++) {
      BiomeGenBase.SpawnListEntry entity = hostileCreatures.get(i);
      String creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
      setValues(((List<BiomeGenBase.SpawnListEntry>)wastelandCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "wasteland"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)forestCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "forest"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)mountainsCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "mountains"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)cityCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "city"));
    } 
    category = "Passive Creature List";
    config.setCategoryComment(category, "Class name = spawnWeight , minGroup , maxGorup  --- do not use spaces!");
    for (i = 0; i < passiveCreatures.size(); i++) {
      BiomeGenBase.SpawnListEntry entity = passiveCreatures.get(i);
      String creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
      setValues(((List<BiomeGenBase.SpawnListEntry>)wastelandCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "wasteland"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)forestCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "forest"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)mountainsCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "mountains"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)cityCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "city"));
    } 
    category = "Water Creature List";
    config.setCategoryComment(category, "Class name = spawnWeight , minGroup , maxGorup  --- do not use spaces!");
    for (i = 0; i < waterCreatures.size(); i++) {
      BiomeGenBase.SpawnListEntry entity = waterCreatures.get(i);
      String creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
      setValues(((List<BiomeGenBase.SpawnListEntry>)wastelandCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "wasteland"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)forestCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "forest"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)mountainsCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "mountains"));
      setValues(((List<BiomeGenBase.SpawnListEntry>)cityCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "city"));
    } 
    config.save();
  }
  
  private static void setValues(BiomeGenBase.SpawnListEntry entry, int[] values) {
    entry.itemWeight = values[0];
    entry.minGroupCount = values[1];
    entry.maxGroupCount = values[2];
  }
  
  private static List<List<BiomeGenBase.SpawnListEntry>> copyArrayList(List<List<BiomeGenBase.SpawnListEntry>> list) {
    List<List<BiomeGenBase.SpawnListEntry>> outList = new ArrayList<List<BiomeGenBase.SpawnListEntry>>(list.size());
      for (List<BiomeGenBase.SpawnListEntry> current : list) {
          List<BiomeGenBase.SpawnListEntry> newList = new ArrayList<BiomeGenBase.SpawnListEntry>();
          for (BiomeGenBase.SpawnListEntry spawnListEntry : current) newList.add(copy(spawnListEntry));
          outList.add(newList);
      }
    return outList;
  }
  
  private static int[] getSpawnValues(String configString, String biome) {
    int index = configString.indexOf(biome) + biome.length() + 1;
    String s = configString.substring(index);
    index = s.indexOf(",");
    String s1 = s.substring(0, index);
    s = s.substring(index + 1);
    index = s.indexOf(",");
    String s2 = s.substring(0, index);
    s = s.substring(index + 1);
    index = s.indexOf(",");
    String s3 = s.substring(0, index);
    return new int[] { Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(s3) };
  }
  
  private static List<List<BiomeGenBase.SpawnListEntry>> getAllBiomeEntities() {
    List<BiomeGenBase.SpawnListEntry> allMonsterCreatures = new ArrayList<BiomeGenBase.SpawnListEntry>();
    List<BiomeGenBase.SpawnListEntry> allPassiveCreatures = new ArrayList<BiomeGenBase.SpawnListEntry>();
    List<BiomeGenBase.SpawnListEntry> allWaterCreatures = new ArrayList<BiomeGenBase.SpawnListEntry>();
    BiomeDictionary.Type[] type = { BiomeDictionary.Type.DEAD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE };
      for (BiomeDictionary.Type value : type) {
          BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(value);
          for (BiomeGenBase biome : biomes) {
              List<BiomeGenBase.SpawnListEntry> monsterCreatures = biome.getSpawnableList(EnumCreatureType.monster);
              List<BiomeGenBase.SpawnListEntry> passiveCreatures = biome.getSpawnableList(EnumCreatureType.creature);
              List<BiomeGenBase.SpawnListEntry> waterCreatures = biome.getSpawnableList(EnumCreatureType.waterCreature);
              int k;
              for (k = 0; k < monsterCreatures.size(); k++) {
                  if (creatureIndex(monsterCreatures.get(k), allMonsterCreatures) == -1)
                      allMonsterCreatures.add(copy(monsterCreatures.get(k)));
              }
              for (k = 0; k < passiveCreatures.size(); k++) {
                  if (creatureIndex(passiveCreatures.get(k), allPassiveCreatures) == -1)
                      allPassiveCreatures.add(copy(passiveCreatures.get(k)));
              }
              for (k = 0; k < waterCreatures.size(); k++) {
                  if (creatureIndex(waterCreatures.get(k), allWaterCreatures) == -1)
                      allWaterCreatures.add(copy(waterCreatures.get(k)));
              }
          }
      }
    List<List<BiomeGenBase.SpawnListEntry>> creatures = new ArrayList<List<BiomeGenBase.SpawnListEntry>>(3);
    creatures.add(allMonsterCreatures);
    creatures.add(allPassiveCreatures);
    creatures.add(allWaterCreatures);
    return creatures;
  }
  
  private static void addRemainingCreatures(List<List<BiomeGenBase.SpawnListEntry>> creatures) {
    Map<String, Object> entities = EntityList.stringToClassMapping;
    for (String entityName : entities.keySet()) {
      Class<?> entity = (Class)entities.get(entityName);
      boolean containsCreature = false;
      if (EntityLiving.class.isAssignableFrom(entity)) {
          for (List<BiomeGenBase.SpawnListEntry> creature : creatures) {
              if (creatureIndex(entity, creature) >= 0)
                  containsCreature = true;
          }
        if (!containsCreature && !entity.equals(EntityMob.class) && !entity.equals(EntityLiving.class)) {
          if (IMob.class.isAssignableFrom(entity) || EntityMob.class.isAssignableFrom(entity)) {
            ((List<BiomeGenBase.SpawnListEntry>)creatures.get(0)).add(new BiomeGenBase.SpawnListEntry(entity, 0, 0, 0));
            continue;
          } 
          if (EntityWaterMob.class.isAssignableFrom(entity)) {
            ((List<BiomeGenBase.SpawnListEntry>)creatures.get(2)).add(new BiomeGenBase.SpawnListEntry(entity, 0, 0, 0));
            continue;
          } 
          ((List<BiomeGenBase.SpawnListEntry>)creatures.get(1)).add(new BiomeGenBase.SpawnListEntry(entity, 0, 0, 0));
        } 
      } 
    } 
  }
  
  private static int creatureIndex(BiomeGenBase.SpawnListEntry entry, List<BiomeGenBase.SpawnListEntry> list) {
    return creatureIndex(entry.entityClass, list);
  }
  
  private static int creatureIndex(Class<?> entry, List<BiomeGenBase.SpawnListEntry> list) {
    for (int i = 0; i < list.size(); i++) {
      BiomeGenBase.SpawnListEntry listEntry = list.get(i);
      if (entry.equals(listEntry.entityClass))
        return i; 
    } 
    return -1;
  }
  
  private static BiomeGenBase.SpawnListEntry copy(BiomeGenBase.SpawnListEntry entry) {
    return new BiomeGenBase.SpawnListEntry(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount);
  }
  
  public static void printString(List<BiomeGenBase.SpawnListEntry> list) {
    Map<String, Object> entities = EntityList.classToStringMapping;
      for (BiomeGenBase.SpawnListEntry entry : list) {
          System.out.println(entities.get(entry.entityClass).toString() + ": " + entry.itemWeight + " " + entry.minGroupCount + " " + entry.maxGroupCount);
      }
  }
}
