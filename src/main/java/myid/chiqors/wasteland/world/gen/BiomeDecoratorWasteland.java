

package myid.chiqors.wasteland.world.gen;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.ruin.RuinRuined;
import myid.chiqors.wasteland.ruin.RuinRuinedCiv1;
import myid.chiqors.wasteland.ruin.RuinSurvivorTent;
import myid.chiqors.wasteland.ruin.RuinTreeHouse;

import java.util.Random;

import myid.chiqors.wasteland.world.biome.BiomeGenWastelandBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeDecoratorWasteland extends BiomeDecorator {
  public static WorldGenerator randomRubbleGen = new WorldGenRandomRubble();
  private static final WorldGenTaiga2 smallSpruce = new WorldGenTaiga2(false);
  private static final WorldGenMegaPineTree bigSpruce = new WorldGenMegaPineTree(true, true);
  
  public static WorldGenWastelandBigTree bigTree = new WorldGenWastelandBigTree(true);
  public static WorldGenWastelandTrees tree = new WorldGenWastelandTrees(true);
  public static WorldGenWastelandLake lakeGen = new WorldGenWastelandLake(ModConfig.getlakeLiquid());
  
  public static WorldGenWastelandClay clayGen = new WorldGenWastelandClay(4);
  
  public static RuinTreeHouse treeHouse = new RuinTreeHouse("treeHouse");
  
  public static RuinSurvivorTent tent = new RuinSurvivorTent("tent");
  
  public static RuinRuined temple = new RuinRuined("temple");
  
  public  static RuinRuinedCiv1 house = new RuinRuinedCiv1("house");

  @Override
  public void decorateChunk(World world, Random rand, BiomeGenBase biome, int chunkX, int chunkZ) {
    if (this.currentWorld == null) {
      this.currentWorld = world;
      this.randomGenerator = rand;
      this.chunk_X = chunkX;
      this.chunk_Z = chunkZ;
      genDecorations(biome);
      this.currentWorld = null;
      this.randomGenerator = null;
    } 
  }

  @Override
  protected void genDecorations(BiomeGenBase biome) {
    super.genDecorations(biome);

    Random rand = new Random();

    if(!(biome instanceof BiomeGenWastelandBase)) return;
    if(BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER)) return;
    BiomeGenWastelandBase wastelandBiome = (BiomeGenWastelandBase) biome;
    int x = this.chunk_X;
    int z = this.chunk_Z;
    boolean coniferous = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS);
    for (int i = 0; rand.nextInt(wastelandBiome.smallLakeSpawnRate) == 0 && i < 3; i++) {

      x += this.randomGenerator.nextInt(16) + 8;
      z += this.randomGenerator.nextInt(16) + 8;
      int y = this.currentWorld.getHeightValue(x, z);

      lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
      if (rand.nextInt(6) < 5)
        clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
    }

    if (rand.nextInt(wastelandBiome.ruinSpawnRate) == 0) {

      x += this.randomGenerator.nextInt(16) + 8;
      z += this.randomGenerator.nextInt(16) + 8;

      switch (rand.nextInt(5)) {
        case 1:
          tent.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
          break;
        case 2:
          house.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
          break;
        case 3:
          if (wastelandBiome.temples) {
            temple.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
            break;
          }
        case 4:
          treeHouse.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
          break;
        default:
          randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
          break;
      }
    }
    if (rand.nextInt(wastelandBiome.treeSpawnRate) == 0) {
      for (int i = 0; i < wastelandBiome.treesPerChunk; i++) {

        int meta = coniferous ? 1 : rand.nextInt(3);

        if (rand.nextInt(4) == 0 || coniferous) {
          if(coniferous){
            bigSpruce.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
          }
          bigTree.setTreeType(Blocks.log, meta);
          bigTree.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
        } else {
          tree.setTreeType(Blocks.log, meta);
          tree.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
        }

      }
    }
  }
}

