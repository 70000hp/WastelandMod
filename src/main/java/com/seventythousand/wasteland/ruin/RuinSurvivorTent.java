

package com.seventythousand.wasteland.ruin;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.hbm.tileentity.machine.storage.TileEntitySafe;
import com.seventythousand.wasteland.config.CityLootConfig;
import com.seventythousand.wasteland.config.ModConfig;
import com.seventythousand.wasteland.config.RuinConfig;
import com.seventythousand.wasteland.items.LootStack;
import com.seventythousand.wasteland.utils.Rectangle;
import com.seventythousand.wasteland.utils.Vector;
import cpw.mods.fml.common.IWorldGenerator;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class RuinSurvivorTent extends Ruin implements IWorldGenerator {

  public RuinSurvivorTent(String par1Name) {
    super(par1Name);
  }

  public boolean generate(World world, Random random, int x, int y, int z) {
    RuinGenHelper.setWorld(world);
    Rectangle pos = new Rectangle(new Vector(x - 2, y, z - 3), 5, 6);
    Block biomeBlock = ModConfig.getSurfaceBlock();
    boolean deluxe = random.nextInt(5) == 0;
    int[] levels = Layout.getLevels(world, pos);
    if (Layout.checkLevel(levels, 0)) {
        int yCoord = Layout.getAverageLevel(levels) - 1;
        RuinGenHelper.setBlock(x - 2, yCoord, z - 3, biomeBlock);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 2, biomeBlock);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 1, biomeBlock);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 0, biomeBlock);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 1, biomeBlock);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 2, biomeBlock);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 3, biomeBlock);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 2, biomeBlock);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, biomeBlock);
      if(!deluxe) {
          handleLoot(world, random,x - 1, yCoord, z);
      } else {
          RuinGenHelper.setBlock(x - 1, yCoord, z, ModBlocks.filing_cabinet, 0);
          TileEntityCrateBase safe = (TileEntityCrateBase) world.getTileEntity(x - 1, yCoord, z);
          if(safe != null) {
              safe.setMod(1);
              safe.setPins(random.nextInt(999) + 1);
              safe.lock();
          } else {
              System.out.println("Tent Cabinet in" + (x - 1) + " " + yCoord + " " + z + " is null, this should not happen!!!!!!!!!!, block at position is:  " + world.getBlock(x,y,z).getUnlocalizedName());
          }
          LootStack.placeLoot(random, safe,
              RuinConfig.getLoot(RuinConfig.hardLoot),
              CityLootConfig.hardLootMin,
              CityLootConfig.hardLootMax);
      }
      RuinGenHelper.setBlock(x - 1, yCoord, z + 1, biomeBlock);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 2, biomeBlock);
      RuinGenHelper.setBlock(x, yCoord, z - 3, biomeBlock);
      RuinGenHelper.setBlock(x, yCoord, z - 2, biomeBlock);
      RuinGenHelper.setBlock(x, yCoord, z - 1, deluxe ? ModBlocks.machine_diesel : Blocks.planks);
      RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.planks);
      RuinGenHelper.setBlock(x, yCoord, z + 1, deluxe ? ModBlocks.red_barrel : Blocks.planks);
      RuinGenHelper.setBlock(x, yCoord, z + 2, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 3, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 2, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 1, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 0, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 1, biomeBlock);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 2, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 3, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 2, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 1, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 0, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 1, biomeBlock);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 2, biomeBlock);
      yCoord++;
      RuinGenHelper.setBlock(x - 2, yCoord, z - 3, Blocks.wool);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.wool);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 1, Blocks.wool);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.wool);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.wool);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 2, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 2, ModBlocks.radiorec);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z + 0, deluxe ? ModBlocks.mine_he : ModBlocks.mine_ap);
      RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 3, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 1, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 0, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 1, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 2, Blocks.wool);
      yCoord++;
      RuinGenHelper.setBlock(x - 2, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x - 2, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x - 2, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.wool);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.wool);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x + 2, yCoord, z + 2, Blocks.air);
      yCoord++;
      RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.air);
      RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.wool);
      RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.wool);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.air);
      RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.air);
      return true;
    }
    return false;
  }
}
