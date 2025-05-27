package com.seventythousand.wasteland.world.biome;

import com.seventythousand.wasteland.config.EntitySpawnConfig;

public class BiomeGenWastelandRiver extends BiomeGenWastelandBase {

    public BiomeGenWastelandRiver(int par1ID, String par2Name, Height biomeHeight) {
        super(par1ID, par2Name, biomeHeight);
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
