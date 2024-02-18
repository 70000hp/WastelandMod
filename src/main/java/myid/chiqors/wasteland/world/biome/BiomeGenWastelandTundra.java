package myid.chiqors.wasteland.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenWastelandTundra extends BiomeGenWastelandBase
{
    private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
    private WorldGenIcePath field_150617_aE = new WorldGenIcePath(3);

    public BiomeGenWastelandTundra(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
        super(par1ID, par2Name, par3BiomeHeight);
        treesPerChunk = 5;
        treeSpawnRate *= 2;
        temples = true;
        this.topBlock = Blocks.snow;
        this.spawnableCreatureList.clear();
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        int k;
        int l;
        int i1;

        for (k = 0; k < 2 && p_76728_1_.rand.nextInt(4) == 0; ++k) {
            l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            this.field_150616_aD.generate(p_76728_1_, p_76728_2_, l, p_76728_1_.getHeightValue(l, i1), i1);
            l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            this.field_150617_aE.generate(p_76728_1_, p_76728_2_, l, p_76728_1_.getHeightValue(l, i1), i1);
        }

        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.field_150604_aj = 0;
        this.fillerBlock = Blocks.dirt;
        this.topBlock = Blocks.snow;
        if (p_150573_7_ > 1.75D)
        {
            this.topBlock = Blocks.dirt;
        }
        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

}
