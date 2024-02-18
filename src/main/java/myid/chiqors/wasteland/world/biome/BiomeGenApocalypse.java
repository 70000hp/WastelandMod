

package myid.chiqors.wasteland.world.biome;

import myid.chiqors.wasteland.config.EntitySpawnConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenApocalypse extends BiomeGenWastelandBase {

  public BiomeGenApocalypse(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
    super(par1ID, par2Name, par3BiomeHeight);
    smallLakeSpawnRate *= 2;
    this.theBiomeDecorator.mushroomsPerChunk = 4;
    this.theBiomeDecorator.deadBushPerChunk = 2;
    this.treesPerChunk = 8;
    wasteTerrain = true;
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(0), this.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(1), this.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
    setCreatureSpawns(EntitySpawnConfig.wastelandCreatures.get(2), this.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
  }
}
