package dk.mrspring.wasteland.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.entity.EntityDayZombie;

public class BiomeGenCity extends BiomeGenWastelandBase
{
	public BiomeGenCity(int par1ID, String par2Name, Height par3BiomeHeight)
	{
		super(par1ID, par2Name, par3BiomeHeight);
		if (ModConfig.dayZombies)
		{
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDayZombie.class, 100, 0, 2)); // weight, maxG, minG
			
		}
	}
}
