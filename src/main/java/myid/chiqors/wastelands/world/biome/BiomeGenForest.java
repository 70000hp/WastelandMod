package myid.chiqors.wastelands.world.biome;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.entity.EntityDayZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class BiomeGenForest extends BiomeGenWastelandBase 
{
	public BiomeGenForest(int par1ID, String par2Name, Height par3BiomeHeight)
	{
		super(par1ID, par2Name, par3BiomeHeight);
		if (ModConfig.dayZombies)
		{
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDayZombie.class, 100, 0, 2)); // weight, maxG, minG
		}
	}
}
