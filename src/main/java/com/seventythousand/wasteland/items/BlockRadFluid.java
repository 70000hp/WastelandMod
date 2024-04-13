

package com.seventythousand.wasteland.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockRadFluid extends BlockFluidClassic {
  @SideOnly(Side.CLIENT)
  protected IIcon stillIcon;

  @SideOnly(Side.CLIENT)
  protected IIcon flowingIcon;


  public BlockRadFluid(Fluid fluid, Material material) {
    super(fluid, material);
  }

  public IIcon getIcon(int side, int meta) {
    return (side == 0 || side == 1) ? this.stillIcon : this.flowingIcon;
  }

  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister register) {
    this.stillIcon = register.registerIcon("WLM:toxic_still");
    this.flowingIcon = register.registerIcon("WLM:toxic_flow");
  }

  public void onEntityCollidedWithBlock(World world, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
    if (!world.isRemote && entity instanceof EntityLivingBase && (world.getWorldTime() & 0xAL) == 0L)
      if (!((EntityLivingBase)entity).isPotionActive(Potion.poison.id))
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 205, 0, false));
  }
}
