package dk.mrspring.wasteland.world.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import dk.mrspring.wasteland.ModConfig;
import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.ruin.RuinRuined;
import dk.mrspring.wasteland.ruin.RuinRuinedCiv1;
import dk.mrspring.wasteland.ruin.RuinSurvivorTent;
import dk.mrspring.wasteland.ruin.RuinTreeHouse;
import dk.mrspring.wasteland.ruin.RuinVillageGenerator;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.gen.WorldGenRandomFire;
import dk.mrspring.wasteland.world.gen.WorldGenRandomRubble;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandBigTree;

public class BiomeDecoratorWasteland extends BiomeDecorator
{
	public int firePerChunk;
	public int rubblePerChunk;
	public int deadTreePerChunk;
	public WorldGenerator randomFireGen;
	public WorldGenerator randomRubbleGen;
	public WorldGenerator deadTreeGen;
	public RuinTreeHouse treeHouse;
	public RuinSurvivorTent tent;
	public RuinRuined temple;
	public RuinRuinedCiv1 house;
	
	public BiomeDecoratorWasteland()
	{
		super();
		
		this.randomFireGen = new WorldGenRandomFire();
		this.randomRubbleGen = new WorldGenRandomRubble();
		this.deadTreeGen = new WorldGenWastelandBigTree(true);
		this.treeHouse = new RuinTreeHouse("treeHouse");
		this.tent = new RuinSurvivorTent("tent");
		this.temple = new RuinRuined("temple");
		this.house = new RuinRuinedCiv1("house");
		
		this.firePerChunk = ModConfig.randomFirePerChunk;
		this.rubblePerChunk = 1;
		this.deadTreePerChunk = 1;
		
		this.flowersPerChunk = -999;
		this.grassPerChunk = -999;
		this.deadBushPerChunk = 5;
		this.generateLakes = false;
		this.treesPerChunk = -999;
	}
	
	@Override
	public void decorateChunk(World world, Random rand, BiomeGenBase biome, int chunkX, int chunkZ)
	{
		if (this.currentWorld != null)
		{
		//	throw new RuntimeException("Already decorating!!");
		}
		else
		{
			this.currentWorld = world;
			this.randomGenerator = rand;
			
			this.chunk_X = chunkX;
			this.chunk_Z = chunkZ;
			
			this.genDecorations(biome);
			
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}
	
	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		super.genDecorations(biome);
		Random rand = new Random();
		
		if (biome.biomeID == WastelandBiomes.apocalypse.biomeID)
		{
			decorateWasteland(rand);
		}
		else if (biome.biomeID == WastelandBiomes.mountains.biomeID)
		{
			decorateMountains(rand);
		}
		else if (biome.biomeID == WastelandBiomes.forest.biomeID)
		{
			decorateForest(rand);
		}
	}
	
	private void decorateWasteland(Random rand)
	{
		int x, y, z;
		boolean doGen = true;//rand.nextInt(1) == 0;//TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CUSTOM);
		for(int i = 0; doGen && i < firePerChunk; i++)
		{
			x = chunk_X + randomGenerator.nextInt(16) + 8;
			z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.randomFireGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = rand.nextInt(ModConfig.wastelandRuinRarirty*3) == 0;
		if (doGen)
		{
			Vector pos = new Vector(chunk_X + randomGenerator.nextInt(16), 0, chunk_Z + randomGenerator.nextInt(16));
			for (int i = 0; i < RuinVillageGenerator.villageNum; i ++)
			{
				doGen = doGen && (Vector.VtoVlengthXZ(pos, RuinVillageGenerator.villageLocation.get(i)) > 48);
			}
			if(doGen)
			{
				this.house.generate(this.currentWorld, this.randomGenerator, pos.X, this.currentWorld.getHeightValue(pos.X, pos.Z)-1, pos.Z);
			}
		}
		
		doGen = rand.nextInt(ModConfig.wastelandRuinRarirty) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16) + 8;
			z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate*15) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16) + 8;
			z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.deadTreeGen.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
	}
	
	private void decorateMountains(Random rand)
	{
		int x,y,z;
		boolean doGen = rand.nextInt(ModConfig.mountainRuinRarity*2) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16);
			z = chunk_Z + randomGenerator.nextInt(16);
			this.temple.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = rand.nextInt(ModConfig.mountainRuinRarity*3) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16) + 8;
			z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate*25) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16);
			z = chunk_Z + randomGenerator.nextInt(16);
			this.deadTreeGen.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
	}
	
	private void decorateForest(Random rand)
	{
		int x, y, z;
		boolean doGen = rand.nextInt(ModConfig.forestRuinRarity) == 0; //TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CUSTOM);
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16) + 8;
			z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.randomRubbleGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = rand.nextInt(ModConfig.forestRuinRarity*3) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16);
			z = chunk_Z + randomGenerator.nextInt(16);
			this.tent.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
		}
		
		doGen = rand.nextInt(ModConfig.forestRuinRarity*2) == 0;
		if(doGen)
		{
			x = chunk_X + randomGenerator.nextInt(16);
			z = chunk_Z + randomGenerator.nextInt(16);
			this.treeHouse.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z) - 1, z);
		}
		
		doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate) == 0;
		int treesPerChunk = 1;//rand.nextInt(2) + 1;
		for(int i = 0; doGen && i < treesPerChunk; i++)
		{
			x = chunk_X + randomGenerator.nextInt(16);
			z = chunk_Z + randomGenerator.nextInt(16);
			this.deadTreeGen.generate(currentWorld, randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
	}
}
