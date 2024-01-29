

package myid.chiqors.wasteland.ruin;

import cpw.mods.fml.common.IWorldGenerator;
import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.items.LootStack;
import myid.chiqors.wasteland.utils.CustomItemStack;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class RuinTreeHouse extends Ruin implements IWorldGenerator {
  private RuinGenHelper genHelper = new RuinGenHelper();
  
  public RuinTreeHouse(String par1Name) {
    super(par1Name);
  }
  
  public boolean generate(World world, Random random, int x, int y, int z) {
    RuinGenHelper.setWorld(world);
    if (world.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock())) {
      int xCoord = x;
      int yCoord = world.getHeightValue(x, z) - 1;
      int zCoord = z;
      int var1 = world.getHeightValue(xCoord - 1, zCoord - 1);
      RuinGenHelper.setBlock(xCoord - 1, var1, zCoord - 1, Blocks.planks);
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      if (!world.getBlock(xCoord, yCoord, zCoord - 1).getMaterial().isSolid())
        RuinGenHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.ladder, 2); 
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.log);
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord - 1, Blocks.ladder, 2);
      yCoord++;
      RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
      RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.log);
      RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.log);
      RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.ladder, 2);
      RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.planks);
      RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.planks);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.planks);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.planks);
      RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.log);
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, (Block)Blocks.chest);
      TileEntityChest chest = (TileEntityChest)world.getTileEntity(xCoord - 1, yCoord, zCoord + 1);
      LootStack loot = setItems(random);
      CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
      RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.log);
      RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.log);
      yCoord++;
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord, Blocks.log);
      RuinGenHelper.setBlock(xCoord, yCoord, zCoord + 1, Blocks.log, 8);
      return true;
    } 
    return false;
  }
}
