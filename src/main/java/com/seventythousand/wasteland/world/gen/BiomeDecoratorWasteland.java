

package com.seventythousand.wasteland.world.gen;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockDeadPlant;
import com.hbm.blocks.generic.BlockMush;
import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.ruin.RuinRuined;
import com.seventythousand.wasteland.ruin.RuinRuinedCiv1;
import com.seventythousand.wasteland.ruin.RuinSurvivorTent;
import com.seventythousand.wasteland.ruin.RuinTreeHouse;
import com.seventythousand.wasteland.world.biome.BiomeGenRadioactive;
import com.seventythousand.wasteland.world.biome.BiomeGenWastelandBase;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
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

    public static RuinRuinedCiv1 house = new RuinRuinedCiv1("house");

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

        if (!(biome instanceof BiomeGenWastelandBase wBiome)) return;
        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER)) return;
        int x = this.chunk_X;
        int z = this.chunk_Z;


        boolean coniferous = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS);
        boolean rad = biome instanceof BiomeGenRadioactive;
        for (int i = 0; rand.nextInt(wBiome.smallLakeSpawnRate) == 0 && i < 3; i++) {

            x += this.randomGenerator.nextInt(16) + 8;
            z += this.randomGenerator.nextInt(16) + 8;
            int y = this.currentWorld.getHeightValue(x, z);

            lakeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
            if (rand.nextInt(6) < 5)
                clayGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
        }

        for (int i = 0; rand.nextInt(wBiome.smallLakeSpawnRate)/2 == 0 && i < 10; i++) {
            int nextBlock = rand.nextInt(wBiome.wFlowers.keySet().size());
            int nextMeta =  rand.nextInt(wBiome.wFlowers.get((Block) wBiome.wFlowers.keySet().toArray()[nextBlock]).size());

            generateFlowers(
                currentWorld,
                rand,
                x,z,
                (Block)wBiome.wFlowers.keySet().toArray()[nextBlock],
                (int)wBiome.wFlowers.values().toArray()[nextMeta]);
        }

        if (rand.nextInt(wBiome.ruinSpawnRate) == 0) {

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
                    if (wBiome.temples) {
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
        if (rand.nextInt(wBiome.treeSpawnRate) == 0) {
            for (int i = 0; i < wBiome.treesPerChunk; i++) {

                int meta = coniferous ? 1 : rand.nextInt(3);

                if (rand.nextInt(4) == 0 || coniferous) {
                    if (coniferous) {
                        bigSpruce.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
                    }
                    if(!rad)
                        tree.setTreeType(Blocks.log, meta);
                    else
                        tree.setTreeType(ModBlocks.waste_log, 0);
                    bigTree.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
                } else {
                    if(!rad)
                        tree.setTreeType(Blocks.log, meta);
                    else
                        tree.setTreeType(ModBlocks.waste_log, 0);

                    tree.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
                }

            }
        }
    }
    private static WorldGenFlowers genFlowers = new WorldGenFlowers(null);
    public static void generateFlowers(World world, Random rand, int chunkX, int chunkZ, Block flower, int meta) {
        int x = chunkX + rand.nextInt(16);
        int z = chunkZ + rand.nextInt(16);
        int y = world.getHeightValue(x, z);
        if(flower.getMaterial() == Material.plants) {
            genFlowers.func_150550_a(flower, meta);
            genFlowers.generate(world, rand, x, y, z);
        } else {
            world.setBlock(x,y,z,flower, meta, 2);
        }

    }
}

