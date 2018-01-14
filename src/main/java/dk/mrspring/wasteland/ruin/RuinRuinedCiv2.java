package dk.mrspring.wasteland.ruin;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RuinRuinedCiv2 extends Ruin implements IWorldGenerator
{
	private RuinGenHelper genHelper = new RuinGenHelper();
	
	public RuinRuinedCiv2(String par1Name)
	{
		super(par1Name);
	}

	public boolean generate(World world, Random random, int i, int j, int k)
	{
		this.genHelper.setWorld(world);
		
		byte byte0 = 3;
		int l = random.nextInt(2) + 2;
		int i1 = random.nextInt(2) + 2;
		boolean flag = false;
		int j1 = 0;
		int k1 = 0;
		Material material = world.getBlock(i, j, k).getMaterial();
		Material material1 = world.getBlock(i, j - 1, k).getMaterial();
		Material material2 = world.getBlock(i + 6, j, k).getMaterial();
		Material material3 = world.getBlock(i, j, k + 6).getMaterial();
		Material material4 = world.getBlock(i + 6, j, k + 6).getMaterial();
		Material material5 = world.getBlock(i, j + 1, k).getMaterial();
		Material material6 = world.getBlock(i + 6, j + 1, k).getMaterial();
		Material material7 = world.getBlock(i, j + 1, k + 6).getMaterial();
		Material material8 = world.getBlock(i + 6, j + 1, k + 6).getMaterial();
		if (((material.isSolid()) || (material1.isSolid())) && (material2.isSolid()) && (material3.isSolid()) && (material4.isSolid()) && (!material5.isSolid()) && (!material6.isSolid()) && (!material7.isSolid()) && (!material8.isSolid()))
		{
			boolean flag1 = true;
			if (flag1)
			{
				Block l1 = Blocks.air;
				int i2 = random.nextInt(3);
				if (i2 == 0)
					l1 = Blocks.cobblestone;
				
				if (i2 == 1)
					l1 = Blocks.mossy_cobblestone;
				
				if (i2 == 2)
					l1 = Blocks.cobblestone;
				
				if (i2 == 3)
					l1 = Blocks.mossy_cobblestone;
				
				int j2 = random.nextInt(2);
				Block k2 = Blocks.air;
				if (j2 == 0) {
					k2 = Blocks.cobblestone;
				} else if (j2 == 1) {
					k2 = Blocks.mossy_cobblestone;
				} else if (j2 == 2) {
					k2 = Blocks.planks;
				} else if (j2 == 3) {
					k2 = Blocks.planks;
				}
				boolean flag2 = false;
				if (!flag2)
				{
					for (int l2 = 1; l2 <= 3; l2++) {
						for (int j3 = 0; j3 < 80; j3++)
						{
							genHelper.setBlock(i + k1, j + l2, k + j1, Blocks.air);
							
							k1++;
							if (k1 == 8)
							{
								j1++;
								k1 = 0;
							}
						}
					}
					int i3 = 0;
					int k3 = 0;
					for (int l3 = 0; l3 < 80; l3++)
					{
						genHelper.setBlock(i + k3, j, k + i3, k2);
						
						k3++;
						if (k3 == 8)
						{
							i3++;
							k3 = 0;
						}
					}
					int i4 = random.nextInt(3);
					int j4 = 0;
					int k4 = 0;
					int l4 = random.nextInt(2);
					if ((i4 > 0) || (l4 == 0)) {
						for (int i5 = 0; i5 < 99; i5++)
						{
							genHelper.setBlock(i + j4, j + 4, k + k4, l1);
							
							j4++;
							if (j4 == 9)
							{
								k4++;
								j4 = 0;
							}
						}
					}
					for (int j5 = 0; j5 < 4; j5++)
					{
						for (int l6 = 0; l6 < 8; l6++) {
							genHelper.setBlock(i + l6, j + j5, k, l1);
						}
						for (int i7 = 0; i7 < 9; i7++) {
							genHelper.setBlock(i + i7, j + j5, k + 10, l1);
						}
						for (int j7 = 0; j7 < 10; j7++) {
							genHelper.setBlock(i, j + j5, k + j7, l1);
						}
						for (int k7 = 0; k7 < 10; k7++) {
							genHelper.setBlock(i + 8, j + j5, k + k7, l1);
						}
					}
					if (i4 > 0) {
						for (int k5 = 0; k5 < 4; k5++)
						{
							for (int l7 = 0; l7 < 8; l7++) {
								genHelper.setBlock(i + l7, j + k5 + 4, k, l1);
							}
							for (int i8 = 0; i8 < 9; i8++) {
								genHelper.setBlock(i + i8, j + k5 + 4, k + 10, l1);
							}
							for (int j8 = 0; j8 < 10; j8++) {
								genHelper.setBlock(i, j + k5 + 4, k + j8, l1);
							}
							for (int k8 = 0; k8 < 10; k8++) {
								genHelper.setBlock(i + 8, j + k5 + 4, k + k8, l1);
							}
						}
					}
					if (i4 > 1) {
						for (int l5 = 0; l5 < 4; l5++)
						{
							for (int l8 = 0; l8 < 8; l8++) {
								genHelper.setBlock(i + l8, j + l5 + 8, k, l1);
							}
							for (int i9 = 0; i9 < 9; i9++) {
								genHelper.setBlock(i + i9, j + l5 + 8, k + 10, l1);
							}
							for (int j9 = 0; j9 < 10; j9++) {
								genHelper.setBlock(i, j + l5 + 8, k + j9, l1);
							}
							for (int k9 = 0; k9 < 10; k9++) {
								genHelper.setBlock(i + 8, j + l5 + 8, k + k9, l1);
							}
						}
					}
					j4 = 0;
					k4 = 0;
					if (i4 > 0) {
						for (int i6 = 0; i6 < 99; i6++)
						{
							genHelper.setBlock(i + j4, j + 8, k + k4, l1);
							
							j4++;
							if (j4 == 9)
							{
								k4++;
								j4 = 0;
							}
						}
					}
					j4 = 0;
					k4 = 0;
					if (i4 > 1) {
						for (int j6 = 0; j6 < 99; j6++)
						{
							genHelper.setBlock(i + j4, j + 12, k + k4, l1);
							
							j4++;
							if (j4 == 9)
							{
								k4++;
								j4 = 0;
							}
						}
					}
					int k6 = random.nextInt(2);
					if (k6 == 0)
					{
						if (i4 >= 1)
						{
							int l9 = 0;
							int i11 = 0;
							for (int j12 = 0; j12 < 63; j12++)
							{
								if ((l9 == 0) || (l9 == 6)) {
									genHelper.setBlock(i + l9 + 1, j + 5, k + i11 + 1, Blocks.bookshelf);
								}
								if ((i11 == 0) || (i11 == 8)) {
									genHelper.setBlock(i + l9 + 1, j + 5, k + i11 + 1, Blocks.bookshelf);
								}
								i11++;
								if (i11 == 9)
								{
									l9++;
									i11 = 0;
								}
							}
						}
						if (i4 > 2)
						{
							int i10 = 0;
							int j11 = 0;
							for (int k12 = 0; k12 < 63; k12++)
							{
								if ((i10 == 0) || (i10 == 6)) {
									genHelper.setBlock(i + i10 + 1, j + 9, k + j11 + 1, Blocks.furnace);
								}
								if ((j11 == 0) || (j11 == 8)) {
									genHelper.setBlock(i + i10 + 1, j + 9, k + j11 + 1, Blocks.furnace);
								}
								j11++;
								if (j11 == 9)
								{
									i10++;
									j11 = 0;
								}
							}
						}
					}
					if (k6 == 1)
					{
						if (i4 >= 1) {
							for (int j10 = 0; j10 < 3; j10++)
							{
								int k11 = 0;
								int l12 = 0;
								for (int j13 = 0; j13 < 63; j13++)
								{
									if ((k11 == 0) || (k11 == 6)) {
										genHelper.setBlock(i + k11 + 1, j + 5 + j10, k + l12 + 1, Blocks.bookshelf);
									}
									if ((l12 == 0) || (l12 == 8)) {
										genHelper.setBlock(i + k11 + 1, j + 5 + j10, k + l12 + 1, Blocks.bookshelf);
									}
									l12++;
									if (l12 == 9)
									{
										k11++;
										l12 = 0;
									}
								}
								genHelper.setBlock(i + 3, j + 5, k + 3, Blocks.planks);
								genHelper.setBlock(i + 4, j + 5, k + 3, Blocks.planks);
								genHelper.setBlock(i + 5, j + 5, k + 3, Blocks.planks);
								genHelper.setBlock(i + 3, j + 5, k + 4, Blocks.planks);
								genHelper.setBlock(i + 4, j + 5, k + 4, Blocks.planks);
								genHelper.setBlock(i + 5, j + 5, k + 4, Blocks.planks);
								genHelper.setBlock(i + 3, j + 5, k + 6, Blocks.planks);
								genHelper.setBlock(i + 4, j + 5, k + 6, Blocks.planks);
								genHelper.setBlock(i + 5, j + 5, k + 6, Blocks.planks);
								genHelper.setBlock(i + 3, j + 5, k + 7, Blocks.planks);
								genHelper.setBlock(i + 4, j + 5, k + 7, Blocks.planks);
								genHelper.setBlock(i + 5, j + 5, k + 7, Blocks.planks);
							}
						}
						if (i4 >= 2)
						{
							for (int k10 = 0; k10 < 3; k10++)
							{
								int l11 = 0;
								int i13 = 0;
								for (int k13 = 0; k13 < 63; k13++)
								{
									if ((l11 == 0) || (l11 == 6)) {
										genHelper.setBlock(i + l11 + 1, j + 9 + k10, k + i13 + 1, Blocks.bookshelf);
									}
									if ((i13 == 0) || (i13 == 8)) {
										genHelper.setBlock(i + l11 + 1, j + 9 + k10, k + i13 + 1, Blocks.bookshelf);
									}
									i13++;
									if (i13 == 9)
									{
										l11++;
										i13 = 0;
									}
								}
							}
							genHelper.setBlock(i + 3, j + 9, k + 3, Blocks.planks);
							genHelper.setBlock(i + 4, j + 9, k + 3, Blocks.planks);
							genHelper.setBlock(i + 5, j + 9, k + 3, Blocks.planks);
							genHelper.setBlock(i + 3, j + 9, k + 4, Blocks.planks);
							genHelper.setBlock(i + 4, j + 9, k + 4, Blocks.planks);
							genHelper.setBlock(i + 5, j + 9, k + 4, Blocks.planks);
							genHelper.setBlock(i + 3, j + 9, k + 6, Blocks.planks);
							genHelper.setBlock(i + 4, j + 9, k + 6, Blocks.planks);
							genHelper.setBlock(i + 5, j + 9, k + 6, Blocks.planks);
							genHelper.setBlock(i + 3, j + 9, k + 7, Blocks.planks);
							genHelper.setBlock(i + 4, j + 9, k + 7, Blocks.planks);
							genHelper.setBlock(i + 5, j + 9, k + 7, Blocks.planks);
						}
					}
					if (i4 > 1)
					{
						genHelper.setBlock(i + 4, j + 9, k + 5, Blocks.torch);
						genHelper.setBlock(i + 4, j + 10, k + 1, Blocks.torch);
						genHelper.setBlock(i + 1, j + 10, k + 5, Blocks.torch);
						genHelper.setBlock(i + 7, j + 10, k + 5, Blocks.torch);
						genHelper.setBlock(i + 4, j + 10, k + 9, Blocks.torch);
					}
					if (i4 > 0)
					{
						genHelper.setBlock(i + 4, j + 5, k + 5, Blocks.torch);
						genHelper.setBlock(i + 4, j + 6, k + 1, Blocks.torch);
						genHelper.setBlock(i + 1, j + 6, k + 5, Blocks.torch);
						genHelper.setBlock(i + 7, j + 6, k + 5, Blocks.torch);
						genHelper.setBlock(i + 4, j + 6, k + 9, Blocks.torch);
					}
					genHelper.setBlock(i + 4, j + 1, k, Blocks.air);
					genHelper.setBlock(i + 4, j + 2, k, Blocks.air);
					genHelper.setBlock(i + 4, j + 1, k + 5, Blocks.torch);
					genHelper.setBlock(i + 3, j + 2, k + 1, Blocks.torch);
					genHelper.setBlock(i + 5, j + 2, k + 1, Blocks.torch);
					genHelper.setBlock(i + 1, j + 2, k + 5, Blocks.torch);
					genHelper.setBlock(i + 7, j + 2, k + 5, Blocks.torch);
					genHelper.setBlock(i + 4, j + 2, k + 9, Blocks.torch);
					int l10 = random.nextInt(24) + 16;
					for (int i12 = 0; i12 < l10; i12++)
					{
						boolean flag3 = false;
						if (!flag3)
						{
							int l13 = random.nextInt(9);
							int i14 = random.nextInt(16);
							int j14 = random.nextInt(11);
							genHelper.setBlock(i + l13, j + i14, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 - 1, j + i14, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 + 1, j + i14, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14, k + j14 + 1, Blocks.air);
							genHelper.setBlock(i + l13, j + i14, k + j14 - 1, Blocks.air);
							genHelper.setBlock(i + l13 - 1, j + i14 - 1, k + j14 - 1, Blocks.air);
							genHelper.setBlock(i + l13 + 1, j + i14 + 1, k + j14 + 1, Blocks.air);
							genHelper.setBlock(i + l13 - 1, j + i14, k + j14 + 1, Blocks.air);
							genHelper.setBlock(i + l13 + 1, j + i14, k + j14 - 1, Blocks.air);
							genHelper.setBlock(i + l13 - 2, j + i14, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 + 2, j + i14, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14, k + j14 + 2, Blocks.air);
							genHelper.setBlock(i + l13, j + i14, k + j14 - 2, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 - 2, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 - 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 - 1, j + i14 - 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 + 1, j + i14 - 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 - 1, k + j14 + 1, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 - 1, k + j14 - 1, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 + 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 + 2, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 - 1, j + i14 + 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13 + 1, j + i14 + 1, k + j14, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 + 1, k + j14 + 1, Blocks.air);
							genHelper.setBlock(i + l13, j + i14 + 1, k + j14 - 1, Blocks.air);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
