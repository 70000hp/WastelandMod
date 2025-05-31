

package com.seventythousand.wasteland.world.gen;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockDeadPlant;
import com.hbm.blocks.generic.BlockMush;
import com.hbm.config.GeneralConfig;
import com.hbm.config.WorldConfig;
import com.hbm.inventory.FluidStack;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.world.feature.BedrockOre;
import com.hbm.world.feature.GlyphidHive;
import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.ruin.RuinRuined;
import com.seventythousand.wasteland.ruin.RuinRuinedCiv1;
import com.seventythousand.wasteland.ruin.RuinSurvivorTent;
import com.seventythousand.wasteland.ruin.RuinTreeHouse;
import com.seventythousand.wasteland.world.biome.BiomeGenRadioactive;
import com.seventythousand.wasteland.world.biome.BiomeGenWastelandBase;

import java.util.Random;

import com.seventythousand.wasteland.world.biome.BiomeGenWastelandTundra;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeDecoratorWasteland extends BiomeDecorator {
    public static WorldGenRandomRubble randomRubbleGen = new WorldGenRandomRubble();
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
        boolean tundra = biome instanceof BiomeGenWastelandTundra;
        for (int i = 0; rand.nextInt(wBiome.smallLakeSpawnRate) == 0 && i < 3; i++) {

            int lakeX = x + this.randomGenerator.nextInt(16) + 8;
            int lakeZ = z + this.randomGenerator.nextInt(16) + 8;
            int y = this.currentWorld.getHeightValue(lakeX, lakeZ);

            lakeGen.generate(this.currentWorld, this.randomGenerator, lakeX, y, lakeZ);
            if (rand.nextInt(9) < 5)
                clayGen.generate(this.currentWorld, this.randomGenerator, lakeX, y, lakeZ);
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

            int lakeX = x + this.randomGenerator.nextInt(16) + 8;
            int lakeZ = z + this.randomGenerator.nextInt(16) + 8;

            switch (rand.nextInt(4)) {
                case 1:
                    tent.generate(this.currentWorld, this.randomGenerator, lakeX, this.currentWorld.getHeightValue(lakeX, lakeX) - 1,lakeZ);
                    break;
                case 2:
                    if (wBiome.temples && rand.nextInt(3) == 0)
                        temple.generate(this.currentWorld, this.randomGenerator, lakeX, this.currentWorld.getHeightValue(lakeX, lakeX) - 1, lakeZ);
                    else
                        house.generate(this.currentWorld, this.randomGenerator, lakeX, this.currentWorld.getHeightValue(lakeX, lakeX) - 1, lakeZ);
                    break;
                case 3:
                    treeHouse.generate(this.currentWorld, this.randomGenerator, lakeX, this.currentWorld.getHeightValue(lakeX, lakeX) - 1, lakeZ);
                    break;
                default:
                    randomRubbleGen.generate(this.currentWorld, this.randomGenerator, lakeX, this.currentWorld.getHeightValue(lakeX, lakeX), lakeZ);
                    break;
            }
        }
        if (rad && WorldConfig.newBedrockOres && rand.nextInt(ModConfig.radBiomeDepositChance) == 0) {

            int randPosX = x + 8 * ( 1 - rand.nextInt(5));
            int randPosZ = z + 8 * ( 1 - rand.nextInt(5));

            int nestAmount = 0;

            if (rand.nextInt(6) == 0) {
                nestAmount += 4;
                randomRubbleGen.generate(this.currentWorld, this.randomGenerator, randPosX, this.currentWorld.getHeightValue(randPosX, randPosZ), randPosZ);
                BedrockOre.generate(currentWorld, randPosX  + rand.nextInt(3), randPosZ + rand.nextInt(3), new ItemStack(ModItems.crystal_schraranium, 2), new FluidStack(Fluids.SAS3, 250), 0xD78A16, 3);
            } else {
                nestAmount += 3;
                randomRubbleGen.scale = 3;
                randomRubbleGen.generate(this.currentWorld, this.randomGenerator, randPosX, this.currentWorld.getHeightValue(randPosX, randPosZ), randPosZ);
                BedrockOre.generate(currentWorld, randPosX  + rand.nextInt(3), randPosZ  + rand.nextInt(3), new ItemStack(ModItems.bedrock_ore_base, 3), new FluidStack(Fluids.SULFURIC_ACID, 500, 5), 0xD78A16, 2);
                randomRubbleGen.scale = 1;
            }

            randomRubbleGen.generate(this.currentWorld, this.randomGenerator, randPosX, this.currentWorld.getHeightValue(randPosX, randPosZ), randPosZ);
            BedrockOre.generate(currentWorld, randPosX + rand.nextInt(3), randPosZ + rand.nextInt(3), new ItemStack(ModItems.gem_rad, 3), new FluidStack(Fluids.HOTSTEAM, 100), 0xD78A16, 2);

            if(ModConfig.radGlyphidToggle)
                for (int i = 0; i < nestAmount; i++) {
                    WastelandGlyphidNest.generateSmall(currentWorld, randPosX, currentWorld.getHeightValue(randPosX,randPosZ),randPosZ,rand,2, true);
                }
        }
        if (rand.nextInt(wBiome.treeSpawnRate) == 0) {
            for (int i = 0; i < wBiome.treesPerChunk; i++) {

                int meta = coniferous ? 1 : rand.nextInt(3);

                if (rand.nextInt(4) == 0 || coniferous) {
                    if(rad)
                        bigTree.setTreeType(ModBlocks.waste_log, 0);
                    else if (tundra){
                        bigTree.setTreeType(ModBlocks.frozen_log, 0);
                    }
                    else
                        bigTree.setTreeType(Blocks.log, meta);
                    bigTree.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
                } else {
                    if(rad)
                        tree.setTreeType(ModBlocks.waste_log, 0);
                    else if (tundra)
                        tree.setTreeType(ModBlocks.frozen_log, 0);
                    else
                        tree.setTreeType(Blocks.log, meta);

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

