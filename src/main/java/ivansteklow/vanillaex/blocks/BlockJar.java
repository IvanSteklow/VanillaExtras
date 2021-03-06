/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.blocks;

import ivansteklow.vanillaex.init.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The main class of water jar <i>(soon be renamed in well)</i>
 * 
 * @author IvanSteklow
 *
 */
public class BlockJar extends Block {

	public BlockJar() {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setHardness(1.0f);
		setHarvestLevel("pickaxe", 0);
		setUnlocalizedName("waterJar");
		setRegistryName(new ResourceLocation(Refs.MOD_ID, "waterJar"));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = playerIn.getHeldItemMainhand();
		if (itemstack.isEmpty()) {
			return true;
		} else {

			Item item = playerIn.getHeldItemMainhand().getItem();

			if (item == Items.BUCKET) {

				itemstack.shrink(1);

				if (itemstack.isEmpty()) {
					playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
				} else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
					playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
				}

				return true;
			} else if (item == Items.GLASS_BOTTLE) {

				itemstack.shrink(1);

				ItemStack itemstack1 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER);

				if (itemstack.isEmpty()) {
					playerIn.setHeldItem(hand, itemstack1);
				} else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
					playerIn.dropItem(itemstack1, false);
				} else if (playerIn instanceof EntityPlayerMP) {
					((EntityPlayerMP) playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
				}

				return true;
			}
			return false;
		}

	}

}
