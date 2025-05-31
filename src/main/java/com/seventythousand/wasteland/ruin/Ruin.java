

package com.seventythousand.wasteland.ruin;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.hbm.tileentity.machine.storage.TileEntitySafe;
import com.seventythousand.wasteland.config.CityLootConfig;
import com.seventythousand.wasteland.config.RuinConfig;
import com.seventythousand.wasteland.items.LootStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class Ruin {
  protected String name;

  protected int weight = 10;

  public Ruin(String par1Name) {
    this.name = par1Name;
  }

  public static void handleLoot(World world, Random random, int x, int y, int z) {
    if(world.getBlock(x,y,z) == Blocks.air)
        if (random.nextInt(RuinConfig.hardRuinLootChance) == 0) {
            RuinGenHelper.setBlock(x, y, z, ModBlocks.safe, 1);
            TileEntityCrateBase safe = (TileEntityCrateBase) world.getTileEntity(x, y, z);
            if(safe != null) {
                safe.setMod(0.5);
                safe.setPins(random.nextInt(999) + 1);
                safe.lock();
                if(random.nextBoolean()){
                    safe.fillWithSpiders();
                }
            } else {
                System.out.println("Ruin Cabinet in" + x + " " + y + " " + z + " is null, this should not happen!!!!!!!!!!, block at position is:  " + world.getBlock(x,y,z).getUnlocalizedName());
            }
            LootStack.placeLoot(random, safe,
                RuinConfig.getLoot(RuinConfig.ruinHardLoot),
                RuinConfig.ruinRareLootMin,
                RuinConfig.ruinRareLootMax);

        } else if (random.nextInt(RuinConfig.rareRuinLootChance) == 0) {
            if(random.nextBoolean()) RuinGenHelper.setBlock(x, y, z, ModBlocks.crate_steel, 0);
            else RuinGenHelper.setBlock(x, y, z, ModBlocks.crate_iron, 0);
            TileEntityCrateBase chest = (TileEntityCrateBase) world.getTileEntity(x, y, z);
            if(chest != null) {
                LootStack.placeLoot(random, chest,
                    RuinConfig.getLoot(RuinConfig.ruinRareLoot),
                    RuinConfig.ruinRareLootMin,
                    RuinConfig.ruinRareLootMax);
            } else {
                System.out.println("Ruin Crate in" + x + " " + y + " " + z + " is null, this should not happen!!!!!!!!!!, block at position is:  " + world.getBlock(x,y,z).getUnlocalizedName());
            }
        } else {
            RuinGenHelper.setBlock(x, y, z, Blocks.chest, 0);
            TileEntityChest chest = (TileEntityChest)world.getTileEntity(x, y, z);
            if(chest != null) {
                LootStack.placeLoot(random, chest,
                    RuinConfig.getLoot(RuinConfig.ruinEasyLoot),
                    RuinConfig.ruinEasyLootMin,
                    RuinConfig.ruinEasyLootMax);
            } else {
                System.out.println("Ruin Chest in" + x + " " + y + " " + z + " is null, this should not happen!!!!!!!!!!, block at position is:  " + world.getBlock(x,y,z).getUnlocalizedName());
            }
        }
  }

  public Ruin setWeight(int par1Weight) {
    this.weight = par1Weight;
    return this;
  }

  protected boolean generate(World world, Random random, int x, int y, int z) {
    return false;
  }

  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.dimensionId == 0)
      generateSurface(world, random, chunkX * 16, chunkZ * 16);
  }

  protected void generateSurface(World world, Random random, int i, int j) {
    int xCoord = i + random.nextInt(16);
    int yCoord = world.getHeightValue(i, j);
    int zCoord = j + random.nextInt(16);
    if (!world.isRemote)
      generate(world, random, xCoord, yCoord, zCoord);
  }
}
