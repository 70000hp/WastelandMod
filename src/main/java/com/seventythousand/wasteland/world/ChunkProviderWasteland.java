

package com.seventythousand.wasteland.world;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderWasteland implements IChunkProvider {
  private Random rand;

  private NoiseGeneratorOctaves noiseGen1;

  private NoiseGeneratorOctaves noiseGen2;

  private NoiseGeneratorOctaves noiseGen3;

  private NoiseGeneratorPerlin noiseGen4;

  public NoiseGeneratorOctaves noiseGen5;

  public NoiseGeneratorOctaves noiseGen6;

  public NoiseGeneratorOctaves mobSpawnerNoise;

  private World worldObj;

  private final boolean mapFeaturesEnabled;

  private WorldType currentWorldType;

  private final double[] dblArray5;

  private final float[] parabolicField;

  private double[] stoneNoise = new double[256];

  private MapGenBase caveGenerator = (MapGenBase)new MapGenCaves();

  private MapGenStronghold strongholdGenerator = new MapGenStronghold();

  private MapGenVillage villageGenerator = new MapGenVillage();

  private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();

  private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();

  private MapGenBase ravineGenerator = (MapGenBase)new MapGenRavine();

  private BiomeGenBase[] biomesForGeneration;

  double[] dblArray1;

  double[] dblArray2;

  double[] dblArray3;

  double[] dblArray4;

  public ChunkProviderWasteland(World par1World, long par2, boolean par4) {
    this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, InitMapGenEvent.EventType.CAVE);
    this.strongholdGenerator = (MapGenStronghold)TerrainGen.getModdedMapGen((MapGenBase)this.strongholdGenerator, InitMapGenEvent.EventType.STRONGHOLD);
    this.villageGenerator = (MapGenVillage)TerrainGen.getModdedMapGen((MapGenBase)this.villageGenerator, InitMapGenEvent.EventType.VILLAGE);
    this.mineshaftGenerator = (MapGenMineshaft)TerrainGen.getModdedMapGen((MapGenBase)this.mineshaftGenerator, InitMapGenEvent.EventType.MINESHAFT);
    this.scatteredFeatureGenerator = (MapGenScatteredFeature)TerrainGen.getModdedMapGen((MapGenBase)this.scatteredFeatureGenerator, InitMapGenEvent.EventType.SCATTERED_FEATURE);
    this.ravineGenerator = TerrainGen.getModdedMapGen(this.ravineGenerator, InitMapGenEvent.EventType.RAVINE);
    this.worldObj = par1World;
    this.mapFeaturesEnabled = par4;
    this.currentWorldType = par1World.getWorldInfo().getTerrainType();
    this.rand = new Random(par2);
    this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
    this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
    this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
    this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
    this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
    this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
    this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    this.dblArray5 = new double[825];
    this.parabolicField = new float[25];
    for (int i = -2; i <= 2; i++) {
      for (int j = -2; j <= 2; j++) {
        float f = 10.0F / MathHelper.sqrt_float((i * i + j * j) + 0.2F);
        this.parabolicField[i + 2 + (j + 2) * 5] = f;
      }
    }
    NoiseGenerator[] noiseGens = { (NoiseGenerator)this.noiseGen1, (NoiseGenerator)this.noiseGen2, (NoiseGenerator)this.noiseGen3, (NoiseGenerator)this.noiseGen4, (NoiseGenerator)this.noiseGen5, (NoiseGenerator)this.noiseGen6, (NoiseGenerator)this.mobSpawnerNoise };
    noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, noiseGens);
    this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
    this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
    this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
    this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
    this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
    this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
    this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
  }

  public void func_147424_a(int xCoord, int zCoord, Block[] blocks) {
    byte byte1 = 63;
    this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, xCoord * 4 - 2, zCoord * 4 - 2, 10, 10);
    func_147423_a(xCoord * 4, 0, zCoord * 4);
    for (int i = 0; i < 4; i++) {
      int int1 = i * 5;
      int int2 = (i + 1) * 5;
      for (int j = 0; j < 4; j++) {
        int int3 = (int1 + j) * 33;
        int int4 = (int1 + j + 1) * 33;
        int int5 = (int2 + j) * 33;
        int int6 = (int2 + j + 1) * 33;
        for (int k = 0; k < 32; k++) {
          double dbl0 = 0.125D;
          double dbl1 = this.dblArray5[int3 + k];
          double dbl2 = this.dblArray5[int4 + k];
          double dbl3 = this.dblArray5[int5 + k];
          double dbl4 = this.dblArray5[int6 + k];
          double dbl5 = (this.dblArray5[int3 + k + 1] - dbl1) * dbl0;
          double dbl6 = (this.dblArray5[int4 + k + 1] - dbl2) * dbl0;
          double dbl7 = (this.dblArray5[int5 + k + 1] - dbl3) * dbl0;
          double dbl8 = (this.dblArray5[int6 + k + 1] - dbl4) * dbl0;
          for (int l = 0; l < 8; l++) {
            double dbl9 = 0.25D;
            double dbl10 = dbl1;
            double dbl11 = dbl2;
            double dbl12 = (dbl3 - dbl1) * dbl9;
            double dbl13 = (dbl4 - dbl2) * dbl9;
            for (int m = 0; m < 4; m++) {
              int j3 = m + i * 4 << 12 | 0 + j * 4 << 8 | k * 8 + l;
              short short1 = 256;
              j3 -= short1;
              double dbl14 = 0.25D;
              double dbl16 = (dbl11 - dbl10) * dbl14;
              double dbl15 = dbl10 - dbl16;
              for (int n = 0; n < 4; n++) {
                if ((dbl15 += dbl16) > 0.0D) {
                  blocks[j3 += short1] = Blocks.stone;
                } else if (k * 8 + l < byte1) {
                  blocks[j3 += short1] = Blocks.air;
                } else {
                  blocks[j3 += short1] = null;
                }
              }
              dbl10 += dbl12;
              dbl11 += dbl13;
            }
            dbl1 += dbl5;
            dbl2 += dbl6;
            dbl3 += dbl7;
            dbl4 += dbl8;
          }
        }
      }
    }
  }

  public void replaceBlocksForBiome(int xChunk, int zChunk, Block[] blocksForReplacement, byte[] metadataForReplacement, BiomeGenBase[] biomesForReplacement, World world) {
    ChunkProviderEvent.ReplaceBiomeBlocks blockReplacementEvent = new ChunkProviderEvent.ReplaceBiomeBlocks(this, xChunk, zChunk, blocksForReplacement, metadataForReplacement, biomesForReplacement, world);
    MinecraftForge.EVENT_BUS.post((Event)blockReplacementEvent);
    if (blockReplacementEvent.getResult() == Event.Result.DENY)
      return;
    double d0 = 0.03125D;
    this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, xChunk * 16, zChunk * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        BiomeGenBase biomegenbase = biomesForReplacement[j + i * 16];
        biomegenbase.genTerrainBlocks(this.worldObj, this.rand, blocksForReplacement, metadataForReplacement, xChunk * 16 + i, zChunk * 16 + j, this.stoneNoise[j + i * 16]);
      }
    }
  }

  public Chunk loadChunk(int par1, int par2) {
    return provideChunk(par1, par2);
  }

  public Chunk provideChunk(int par1, int par2) {
    this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
    Block[] blocks = new Block[65536];
    byte[] metadata = new byte[65536];
    func_147424_a(par1, par2, blocks);
    this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
    replaceBlocksForBiome(par1, par2, blocks, metadata, this.biomesForGeneration, this.worldObj);
    this.caveGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
    this.ravineGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
    if (this.mapFeaturesEnabled) {
      this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
      this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
      this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
      this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
    }
    Chunk chunk = new Chunk(this.worldObj, blocks, metadata, par1, par2);
    byte[] byteArray = chunk.getBiomeArray();
    for (int i = 0; i < byteArray.length; i++)
      byteArray[i] = (byte)(this.biomesForGeneration[i]).biomeID;
    chunk.generateSkylightMap();
    return chunk;
  }

  private void func_147423_a(int par1, int par2, int par3) {
    double dbl0 = 684.412D;
    double dbl1 = 684.412D;
    double dbl2 = 512.0D;
    double dbl3 = 512.0D;
    this.dblArray4 = this.noiseGen6.generateNoiseOctaves(this.dblArray4, par1, par3, 5, 5, 200.0D, 200.0D, 0.5D);
    this.dblArray1 = this.noiseGen3.generateNoiseOctaves(this.dblArray1, par1, par2, par3, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
    this.dblArray2 = this.noiseGen1.generateNoiseOctaves(this.dblArray2, par1, par2, par3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
    this.dblArray3 = this.noiseGen2.generateNoiseOctaves(this.dblArray3, par1, par2, par3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
    int int0 = 0;
    int int1 = 0;
    double dbl4 = 8.5D;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        float flt0 = 0.0F;
        float flt1 = 0.0F;
        float flt2 = 0.0F;
        byte byte0 = 2;
        BiomeGenBase biome0 = this.biomesForGeneration[i + 2 + (j + 2) * 10];
        for (int k = -byte0; k <= byte0; k++) {
          for (int l = -byte0; l <= byte0; l++) {
            BiomeGenBase biome1 = this.biomesForGeneration[i + k + 2 + (j + l + 2) * 10];
            float flt3 = biome1.rootHeight;
            float flt4 = biome1.heightVariation;
            if (this.currentWorldType == WorldType.AMPLIFIED && flt3 > 0.0F) {
              flt3 = 1.0F + flt3 * 2.0F;
              flt4 = 1.0F + flt4 * 4.0F;
            }
            float flt5 = this.parabolicField[k + 2 + (l + 2) * 5] / (flt3 + 2.0F);
            if (biome1.rootHeight > biome0.rootHeight)
              flt5 /= 2.0F;
            flt0 += flt4 * flt5;
            flt1 += flt3 * flt5;
            flt2 += flt5;
          }
        }
        flt0 /= flt2;
        flt1 /= flt2;
        flt0 = flt0 * 0.9F + 0.1F;
        flt1 = (flt1 * 4.0F - 1.0F) / 8.0F;
        double dbl13 = this.dblArray4[int1] / 8000.0D;
        if (dbl13 < 0.0D)
          dbl13 = -dbl13 * 0.3D;
        dbl13 = dbl13 * 3.0D - 2.0D;
        if (dbl13 < 0.0D) {
          dbl13 /= 2.0D;
          if (dbl13 < -1.0D)
            dbl13 = -1.0D;
          dbl13 /= 1.4D;
          dbl13 /= 2.0D;
        } else {
          if (dbl13 > 1.0D)
            dbl13 = 1.0D;
          dbl13 /= 8.0D;
        }
        int1++;
        double dbl12 = flt1;
        double dbl14 = flt0;
        dbl12 += dbl13 * 0.2D;
        dbl12 = dbl12 * 8.5D / 8.0D;
        double dbl5 = 8.5D + dbl12 * 4.0D;
        for (int m = 0; m < 33; m++) {
          double dbl6 = (m - dbl5) * 12.0D * 128.0D / 256.0D / dbl14;
          if (dbl6 < 0.0D)
            dbl6 *= 4.0D;
          double dbl7 = this.dblArray2[int0] / 512.0D;
          double dbl8 = this.dblArray3[int0] / 512.0D;
          double dbl9 = (this.dblArray1[int0] / 10.0D + 1.0D) / 2.0D;
          double dbl10 = MathHelper.denormalizeClamp(dbl7, dbl8, dbl9) - dbl6;
          if (m > 29) {
            double d11 = ((m - 29) / 3.0F);
            dbl10 = dbl10 * (1.0D - d11) + -10.0D * d11;
          }
          this.dblArray5[int0] = dbl10;
          int0++;
        }
      }
    }
  }

  public boolean chunkExists(int par1, int par2) {
    return true;
  }

  public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
    BlockFalling.fallInstantly = true;
    int k = par2 * 16;
    int l = par3 * 16;
    BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
    this.rand.setSeed(this.worldObj.getSeed());
    long i1 = this.rand.nextLong() / 2L * 2L + 1L;
    long j1 = this.rand.nextLong() / 2L * 2L + 1L;
    this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
    boolean bln = false;

    MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln));

    if (this.mapFeaturesEnabled) {

      this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
      bln = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
      this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
      this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);

    }

    boolean doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln, PopulateChunkEvent.Populate.EventType.DUNGEON);
    int var1;
    for (var1 = 0; doGen && var1 < 8; var1++) {
      int var2 = k + this.rand.nextInt(16) + 8;
      int var3 = this.rand.nextInt(256);
      int j2 = l + this.rand.nextInt(16) + 8;
      (new WorldGenDungeons()).generate(this.worldObj, this.rand, var2, var3, j2);
    }
    biomegenbase.decorate(this.worldObj, this.rand, k, l);
    SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
    k += 8;
    l += 8;
    doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln, PopulateChunkEvent.Populate.EventType.ICE);
    for (var1 = 0; doGen && var1 < 16; var1++) {
      for (int var2 = 0; var2 < 16; var2++) {
        int var3 = this.worldObj.getPrecipitationHeight(k + var1, l + var2);
        if (this.worldObj.isBlockFreezable(var1 + k, var3 - 1, var2 + l))
          this.worldObj.setBlock(var1 + k, var3 - 1, var2 + l, Blocks.ice, 0, 2);
        if (this.worldObj.func_147478_e(var1 + k, var3, var2 + l, true))
          this.worldObj.setBlock(var1 + k, var3, var2 + l, Blocks.snow_layer, 0, 2);
      }
    }
    MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln));
    BlockFalling.fallInstantly = false;
  }

  public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
    return true;
  }

  public void saveExtraData() {}

  public boolean unloadQueuedChunks() {
    return false;
  }

  public boolean canSave() {
    return true;
  }

  public String makeString() {
    return "RandomLevelSource";
  }

  public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
    BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
    return (par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(par2, par3, par4)) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(par1EnumCreatureType);
  }

  public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
    return ("Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null) ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
  }

  public int getLoadedChunkCount() {
    return 0;
  }

  public void recreateStructures(int par1, int par2) {
    if (this.mapFeaturesEnabled) {
      this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
      this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
      this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
      this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
    }
  }
}
