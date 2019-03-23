package the_fireplace.clayspawn.config;

import net.minecraftforge.fml.common.Loader;

/**
 * @author The_Fireplace
 */
public class ConfigValues {
	//public static String[] DIMENSIONLIST;
	//public static final String[] DIMENSIONLIST_DEFAULT = new String[]{"*"};
	//public static final String DIMENSIONLIST_NAME = "DimensionList";
	//Clay
	public static boolean GENERATE;
	public static final boolean GENERATE_DEFAULT = true;
	public static final String GENERATE_NAME = "Generate";
	public static String IMITATION;
	public static final String OREGENRATE_DEFAULT = "Iron";
	public static final String OREGENRATE_NAME = "OreGenRate";
	public static int MINVEINSIZEOVERRIDE;
	public static final int MINVEINSIZEOVERRIDE_DEFAULT = 0;
	public static final String MINVEINSIZEOVERRIDE_NAME = "DensityOverride";
	public static int MAXVEINSIZEOVERRIDE;
	public static final int MAXDENSITYOVERRIDE_DEFAULT = 0;
	public static final String MAXDENSITYOVERRIDE_NAME = "MaxDensityOverride";
	public static int MAXHEIGHTOVERRIDE;
	public static final int MAXHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String MAXHEIGHTOVERRIDE_NAME = "MaxHeightOverride";
	public static int MINHEIGHTOVERRIDE;
	public static final int MINHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String MINHEIGHTOVERRIDE_NAME = "MinHeightOverride";
	public static int VEINCOUNTOVERRIDE;
	public static final int VEINCOUNTOVERRIDE_DEFAULT = 0;
	public static final String VEINCOUNTOVERRIDE_NAME = "VeinCountOverride";
	//Terracotta
	public static boolean HARDGENERATE;
	public static final boolean HARDGENERATE_DEFAULT = true;
	public static final String HARDGENERATE_NAME = "HardGenerate";
	public static String HARDIMITATION;
	public static final String HARDOREGENRATE_DEFAULT = "Iron";
	public static final String HARDOREGENRATE_NAME = "HardOreGenRate";
	public static int HARDMINVEINSIZEOVERRIDE;
	public static final int HARDMINVEINSIZEOVERRIDE_DEFAULT = 0;
	public static final String HARDMINVEINSIZEOVERRIDE_NAME = "HardDensityOverride";
	public static int HARDMAXVEINSIZEOVERRIDE;
	public static final int HARDMAXDENSITYOVERRIDE_DEFAULT = 0;
	public static final String HARDMAXDENSITYOVERRIDE_NAME = "HardMaxDensityOverride";
	public static int HARDMAXHEIGHTOVERRIDE;
	public static final int HARDMAXHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String HARDMAXHEIGHTOVERRIDE_NAME = "HardMaxHeightOverride";
	public static int HARDMINHEIGHTOVERRIDE;
	public static final int HARDMINHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String HARDMINHEIGHTOVERRIDE_NAME = "HardMinHeightOverride";
	public static int HARDVEINCOUNTOVERRIDE;
	public static final int HARDVEINCOUNTOVERRIDE_DEFAULT = 0;
	public static final String HARDVEINCOUNTOVERRIDE_NAME = "HardVeinCountOverride";
	public static boolean COLORFULCLAY;
	public static final boolean COLORFULCLAY_DEFAULT = !Loader.isModLoaded("immersiveengineering");
	public static final String COLORFULCLAY_NAME = "ColorfulClay";
	//Glazed Terracotta
	public static boolean GLAZEDGENERATE;
	public static final boolean GLAZEDGENERATE_DEFAULT = false;
	public static final String GLAZEDGENERATE_NAME = "GlazedGenerate";
	public static String GLAZEDIMITATION;
	public static final String GLAZEDOREGENRATE_DEFAULT = "Gold";
	public static final String GLAZEDOREGENRATE_NAME = "GlazedOreGenRate";
	public static int GLAZEDMINVEINSIZEOVERRIDE;
	public static final int GLAZEDMINDENSITYOVERRIDE_DEFAULT = 0;
	public static final String GLAZEDMINDENSITYOVERRIDE_NAME = "GlazedDensityOverride";
	public static int GLAZEDMAXVEINSIZEOVERRIDE;
	public static final int GLAZEDMAXDENSITYOVERRIDE_DEFAULT = 0;
	public static final String GLAZEDMAXDENSITYOVERRIDE_NAME = "GlazedMaxDensityOverride";
	public static int GLAZEDMAXHEIGHTOVERRIDE;
	public static final int GLAZEDMAXHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String GLAZEDMAXHEIGHTOVERRIDE_NAME = "GlazedMaxHeightOverride";
	public static int GLAZEDMINHEIGHTOVERRIDE;
	public static final int GLAZEDMINHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String GLAZEDMINHEIGHTOVERRIDE_NAME = "GlazedMinHeightOverride";
	public static int GLAZEDVEINCOUNTOVERRIDE;
	public static final int GLAZEDVEINCOUNTOVERRIDE_DEFAULT = 0;
	public static final String GLAZEDVEINCOUNTOVERRIDE_NAME = "GlazedVeinCountOverride";
}
