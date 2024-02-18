

package myid.chiqors.wasteland.world.biome;

import myid.chiqors.wasteland.config.EntitySpawnConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenMountains extends BiomeGenWastelandBase {
  public BiomeGenMountains(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    treeSpawnRate /= 2;
    temples = true;
    smallLakeSpawnRate *= 3;
    wasteTerrain = true;
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.mountainsCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
}
