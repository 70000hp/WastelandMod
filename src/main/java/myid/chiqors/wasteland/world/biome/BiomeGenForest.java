

package myid.chiqors.wasteland.world.biome;

import myid.chiqors.wasteland.config.EntitySpawnConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenForest extends BiomeGenWastelandBase {

  public BiomeGenForest(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    this.treeSpawnRate /= 5;
    this.ruinSpawnRate /= 2;
    this.theBiomeDecorator.mushroomsPerChunk = 2;
    this.theBiomeDecorator.deadBushPerChunk = 2;
    this.treesPerChunk = 6;
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.forestCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
}
