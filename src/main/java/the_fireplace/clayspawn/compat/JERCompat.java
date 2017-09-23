package the_fireplace.clayspawn.compat;

import com.google.common.collect.Lists;
import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.clayspawn.ClaySpawn;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class JERCompat implements IModCompat {

	@JERPlugin
	public static IJERAPI jerAPI;

	@Override
	public void init() {
		final ItemStack clay = new ItemStack(Blocks.CLAY);
		final ItemStack hardened_clay = ConfigValues.COLORFULCLAY ? new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, OreDictionary.WILDCARD_VALUE) : new ItemStack(Blocks.HARDENED_CLAY);
		final List<ItemStack> glazedStacks = Lists.newArrayList();
		for (int i = 0; i < WorldGeneratorClay.glazedStates.length; i++)
			glazedStacks.add(new ItemStack(WorldGeneratorClay.glazedStates[i].getBlock()));
		if (ConfigValues.GENERATE)
			jerAPI.getWorldGenRegistry().register(clay, new DistributionSquare(ClaySpawn.instance.wg.getVeinCount(), ClaySpawn.instance.wg.getRate(), ClaySpawn.instance.wg.getMinLayer(), ClaySpawn.instance.wg.getMaxLayer()));
		if (ConfigValues.HARDGENERATE)
			jerAPI.getWorldGenRegistry().register(hardened_clay, new DistributionSquare(ClaySpawn.instance.wg.getHardVeinCount(), ClaySpawn.instance.wg.getHardRate(), ClaySpawn.instance.wg.getHardMinLayer(), ClaySpawn.instance.wg.getHardMaxLayer()),
					new LootDrop(hardened_clay));
		if (ConfigValues.GLAZEDGENERATE)
			for (ItemStack stack : glazedStacks)
				jerAPI.getWorldGenRegistry().register(stack, new DistributionSquare(ClaySpawn.instance.wg.getGlazedVeinCount(), ClaySpawn.instance.wg.getGlazedRate(), ClaySpawn.instance.wg.getGlazedMinLayer(), ClaySpawn.instance.wg.getGlazedMaxLayer()),
						new LootDrop(stack));
	}
}
