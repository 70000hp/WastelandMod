

package myid.chiqors.wasteland.world.gen;

import myid.chiqors.wasteland.WastelandBiomes;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.ruin.RuinRuined;
import myid.chiqors.wasteland.ruin.RuinRuinedCiv1;
import myid.chiqors.wasteland.ruin.RuinSurvivorTent;
import myid.chiqors.wasteland.ruin.RuinTreeHouse;
import myid.chiqors.wasteland.ruin.RuinVillageGenerator;
import myid.chiqors.wasteland.utils.Vector;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorWasteland extends BiomeDecorator {
  public WorldGenerator randomFireGen = new WorldGenRandomFire();
  
  public WorldGenerator randomRubbleGen = new WorldGenRandomRubble();
  
  public WorldGenWastelandBigTree deadTreeGen = new WorldGenWastelandBigTree(true);
  
  public WorldGenWastelandLake lakeGen = new WorldGenWastelandLake(ModConfig.getlakeLiquid());
  
  public WorldGenWastelandClay clayGen = new WorldGenWastelandClay(4);
  
  public RuinTreeHouse treeHouse = new RuinTreeHouse("treeHouse");
  
  public RuinSurvivorTent tent = new RuinSurvivorTent("tent");
  
  public RuinRuined temple = new RuinRuined("temple");
  
  public RuinRuinedCiv1 house = new RuinRuinedCiv1("house");
  
  public int firePerChunk = ModConfig.randomFirePerChunk;
  
  public int rubblePerChunk = 1;
  
  public int deadTreePerChunk = 1;
  
  public void func_150512_a(World world, Random rand, BiomeGenBase biome, int chunkX, int chunkZ) {
    if (this.currentWorld == null) {
      this.currentWorld = world;
      this.randomGenerator = rand;
      this.chunk_X = chunkX;
      this.chunk_Z = chunkZ;
      func_150513_a(biome);
      this.currentWorld = null;
      this.randomGenerator = null;
    } 
  }
  
  protected void func_150513_a(BiomeGenBase biome) {
    super.genDecorations(biome);
    Random rand = new Random();
    if (biome.biomeID == WastelandBiomes.apocalypse.biomeID) {
      decorateWasteland(rand);
    } else if (biome.biomeID == WastelandBiomes.mountains.biomeID) {
      decorateMountains(rand);
    } else if (biome.biomeID == WastelandBiomes.forest.biomeID) {
      decorateForest(rand);
    } else if (biome.biomeID == WastelandBiomes.radioactive.biomeID) {
      decorateRadioactive(rand);
    } 
  }
  
  private void decorateWasteland(Random rand) {
    boolean doGen = (rand.nextInt(ModConfig.lakeSpawnRate * 3) == 0);
    int i;
    for (i = 0; doGen && i < 1; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      int y = this.currentWorld.getHeightValue(x, z);
      this.lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
      if (rand.nextInt(6) < 5)
        this.clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z); 
    } 
    doGen = true;
    for (i = 0; doGen && i < this.firePerChunk; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomFireGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandRuinRarirty * 3) == 0);
    if (doGen) {
      Vector pos = new Vector(this.chunk_X + this.randomGenerator.nextInt(16), 0, this.chunk_Z + this.randomGenerator.nextInt(16));
      for (int j = 0; j < RuinVillageGenerator.villageNum; j++)
        doGen = (doGen && Vector.VtoVlengthXZ(pos, RuinVillageGenerator.villageLocation.get(j)) > 48.0D); 
      if (doGen)
        this.house.generate(this.currentWorld, this.randomGenerator, pos.X, this.currentWorld.getHeightValue(pos.X, pos.Z) - 1, pos.Z); 
    } 
    doGen = (rand.nextInt(ModConfig.wastelandRuinRarirty) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandTreeSpawnRate * 15) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.deadTreeGen.setTreeType(Blocks.log, 0);
      this.deadTreeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
  }
  
  private void decorateMountains(Random rand) {
    boolean doGen = (rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0);
    for (int i = 0; doGen && i < 1; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      int y = this.currentWorld.getHeightValue(x, z);
      this.lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
      if (rand.nextInt(6) < 5)
        this.clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z); 
    } 
    doGen = (rand.nextInt(ModConfig.mountainRuinRarity * 2) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16);
      int z = this.chunk_Z + this.randomGenerator.nextInt(16);
      this.temple.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.mountainRuinRarity * 3) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandTreeSpawnRate * 25) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16);
      int z = this.chunk_Z + this.randomGenerator.nextInt(16);
      this.deadTreeGen.setTreeType(Blocks.log, 0);
      this.deadTreeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
  }
  
  private void decorateForest(Random rand) {
    boolean doGen = (rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0);
    for (int i = 0; doGen && i < 1; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      int y = this.currentWorld.getHeightValue(x, z);
      this.lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
      if (rand.nextInt(6) < 5)
        this.clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z); 
    } 
    doGen = (rand.nextInt(ModConfig.forestRuinRarity) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.forestRuinRarity * 3) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16);
      int z = this.chunk_Z + this.randomGenerator.nextInt(16);
      this.tent.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
    } 
    doGen = (rand.nextInt(ModConfig.forestRuinRarity * 2) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16);
      int z = this.chunk_Z + this.randomGenerator.nextInt(16);
      this.treeHouse.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandTreeSpawnRate) == 0);
    int treesPerChunk = 1;
    for (int j = 0; doGen && j < treesPerChunk; j++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16);
      int z = this.chunk_Z + this.randomGenerator.nextInt(16);
      this.deadTreeGen.setTreeType(ModConfig.getWoodType(this.randomGenerator));
      this.deadTreeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
  }
  
  private void decorateRadioactive(Random rand) {
    boolean doGen = (rand.nextInt(ModConfig.lakeSpawnRate / 10) == 0);
    int i;
    for (i = 0; doGen && i < 1; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      int y = this.currentWorld.getHeightValue(x, z);
      this.lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
      this.clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
    } 
    doGen = (rand.nextInt(4) == 0);
    for (i = 0; doGen && i < 1; i++) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomFireGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandRuinRarirty * 5) == 0);
    if (doGen) {
      Vector pos = new Vector(this.chunk_X + this.randomGenerator.nextInt(16), 0, this.chunk_Z + this.randomGenerator.nextInt(16));
      for (int j = 0; j < RuinVillageGenerator.villageNum; j++)
        doGen = (doGen && Vector.VtoVlengthXZ(pos, RuinVillageGenerator.villageLocation.get(j)) > 48.0D); 
      if (doGen)
        this.house.generate(this.currentWorld, this.randomGenerator, pos.X, this.currentWorld.getHeightValue(pos.X, pos.Z) - 1, pos.Z); 
    } 
    doGen = (rand.nextInt(ModConfig.wastelandRuinRarirty * 2) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
    doGen = (rand.nextInt(ModConfig.wastelandTreeSpawnRate * 75) == 0);
    if (doGen) {
      int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.deadTreeGen.setTreeType(Blocks.log, 0);
      this.deadTreeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
    } 
  }
}
