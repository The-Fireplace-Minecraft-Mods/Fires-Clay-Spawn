package the_fireplace.clayspawn.compat;

import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.clayspawn.ClaySpawn;
import the_fireplace.clayspawn.config.ConfigValues;

/**
 * @author The_Fireplace
 */
public class JERCompat implements IModCompat{

    @JERPlugin
    public static IJERAPI jerAPI;

    @Override
    public void init() {
        final ItemStack clay = new ItemStack(Blocks.CLAY);
        final ItemStack hardened_clay = ConfigValues.COLORFULCLAY ? new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, OreDictionary.WILDCARD_VALUE) : new ItemStack(Blocks.HARDENED_CLAY);

        if(ConfigValues.GENERATE)
            jerAPI.getWorldGenRegistry().register(clay, new DistributionSquare(ClaySpawn.instance.wg.getMaxLayer()-ClaySpawn.instance.wg.getMinLayer(), ClaySpawn.instance.wg.getRate(), ClaySpawn.instance.wg.getMinLayer(), ClaySpawn.instance.wg.getMaxLayer()));
        if(ConfigValues.HARDGENERATE)
            jerAPI.getWorldGenRegistry().register(hardened_clay, new DistributionSquare(ClaySpawn.instance.wg.getHardMaxLayer()-ClaySpawn.instance.wg.getHardMinLayer(), ClaySpawn.instance.wg.getHardRate(), ClaySpawn.instance.wg.getHardMinLayer(), ClaySpawn.instance.wg.getHardMaxLayer()),
                new LootDrop(hardened_clay));
    }
}
