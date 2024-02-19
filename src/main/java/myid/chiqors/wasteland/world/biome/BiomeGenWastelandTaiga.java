package myid.chiqors.wasteland.world.biome;

import java.util.Random;

import myid.chiqors.wasteland.world.biome.BiomeGenWastelandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenWastelandTaiga extends BiomeGenWastelandBase
{
    private static WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.snow, 0);
    private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
    private static final String __OBFID = "CL_00000186";

    public BiomeGenWastelandTaiga(int par1ID, String par2Name, BiomeGenBase.Height par3BiomeHeight) {
        super(par1ID, par2Name, par3BiomeHeight);
        treesPerChunk = 2;
        treeSpawnRate /= 2;
        temples = true;
        this.theBiomeDecorator.grassPerChunk = 2;
        this.theBiomeDecorator.deadBushPerChunk = 6;
        this.theBiomeDecorator.mushroomsPerChunk = 6;

    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
    {
        return p_76730_1_.nextInt(5) > 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    public void decorate(World world, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        int k;
        int l;
        int i1;
        int j1;

        k = p_76728_2_.nextInt(3);

        for (l = 0; l < k; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            field_150643_aG.generate(world, p_76728_2_, i1, k1, j1);
        }
        if(world.rand.nextInt(6) == 0){
            l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            this.field_150616_aD.generate(world, p_76728_2_, l, world.getHeightValue(l, i1), i1);
        }
        super.decorate(world, p_76728_2_, p_76728_3_, p_76728_4_);
    }

}
