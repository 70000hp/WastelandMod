package myid.chiqors.wastelands.world.biome;

import myid.chiqors.wastelands.ModConfig;
import myid.chiqors.wastelands.entity.EntityDayZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class BiomeGenMountains extends BiomeGenWastelandBase
{
	public BiomeGenMountains(int par1ID, String par2Name, Height par3BiomeHeight)
	{
		super(par1ID, par2Name, par3BiomeHeight);
		
		//this.theBiomeDecorator = new BiomeDecoratorWasteland();
		if (ModConfig.dayZombies)
		{
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDayZombie.class, 10, 0, 2)); // weight, maxG, minG
		}
	}
}