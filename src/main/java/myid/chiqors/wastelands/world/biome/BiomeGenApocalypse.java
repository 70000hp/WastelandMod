package myid.chiqors.wastelands.world.biome;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.entity.EntityDayZombie;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenApocalypse extends BiomeGenWastelandBase
{
	public BiomeGenApocalypse(int par1ID, String par2Name, Height par3BiomeHeight)
	{
		super(par1ID, par2Name, par3BiomeHeight);
		if (ModConfig.dayZombies)
		{
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDayZombie.class, 100, 0, 2)); // weight, maxG, minG
		}
	}
}
