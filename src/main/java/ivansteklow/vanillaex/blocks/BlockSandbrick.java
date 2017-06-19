package ivansteklow.vanillaex.blocks;

import ivansteklow.isdev.bases.BlockBase;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.block.material.Material;

public class BlockSandbrick extends BlockBase{

	public BlockSandbrick() {
		super(Material.ROCK, "blockSandbrick", Refs.MOD_ID);
		setHardness(0.8F);
		setHarvestLevel("pickaxe", 0);
	}

}
