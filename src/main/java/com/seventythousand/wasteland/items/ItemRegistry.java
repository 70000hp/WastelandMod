

package com.seventythousand.wasteland.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ItemRegistry {
  public static Fluid radiationWaste;

  public static BlockRadFluid radiationWasteBlock;

  public static ItemBucket radiationWasteBucket;

  public ItemRegistry() {
    radiationWaste = (new Fluid("toxicWasteFluid")).setLuminosity(10).setDensity(1000).setViscosity(1500);
    registerFluids();
    radiationWasteBlock = (BlockRadFluid)(new BlockRadFluid(radiationWaste, Material.water)).setBlockName("toxicWasteBlock");
    registerBlocks();
    radiationWasteBucket = (ItemBucket)(new ItemBucket((Block)radiationWasteBlock)).setUnlocalizedName("toxicWasteBucket");
    radiationWasteBucket.setContainerItem(Items.bucket);
    radiationWasteBucket.setTextureName("WLM:bucket_toxic");
    radiationWasteBucket.setCreativeTab(CreativeTabs.tabMisc);
    registerItems();
  }

  public void registerFluids() {
    FluidRegistry.registerFluid(radiationWaste);
  }

  public void registerBlocks() {
    GameRegistry.registerBlock((Block)radiationWasteBlock, radiationWasteBlock.getUnlocalizedName());
  }

  public void registerItems() {
    GameRegistry.registerItem((Item)radiationWasteBucket, radiationWasteBucket.getUnlocalizedName());
  }
}
