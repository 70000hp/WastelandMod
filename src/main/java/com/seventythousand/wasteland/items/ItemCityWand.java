package com.seventythousand.wasteland.items;

import com.hbm.lib.Library;
import com.seventythousand.wasteland.Wasteland;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

public class ItemCityWand extends Item {

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(world.isRemote)
            return stack;

        MovingObjectPosition pos = Library.rayTrace(player, 500, 1, false, true, false);

        if(pos != null) {

            int y = world.getHeightValue(pos.blockX, pos.blockZ);
            if(!player.isSneaking()) Wasteland.cityGenerator.generateCity(itemRand, pos.blockX/16, pos.blockZ/16, world, true);
            else Wasteland.villageGenerator.generateVillage(itemRand, pos.blockX/16, pos.blockZ/16, world, true);
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
        list.add("Shift click to spawn the smaller villages");
    }
}
