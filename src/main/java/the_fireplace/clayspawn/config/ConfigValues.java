package the_fireplace.clayspawn.config;

import net.minecraftforge.fml.common.Loader;

/**
 * 
 * @author The_Fireplace
 *
 */
public class ConfigValues {
	public static boolean GENERATE;
	public static final boolean GENERATE_DEFAULT = true;
	public static final String GENERATE_NAME = "Generate";
	public static String OREGENRATE;
	public static final String OREGENRATE_DEFAULT = "Iron";
	public static final String OREGENRATE_NAME = "OreGenRate";
	public static int DENSITYOVERRIDE;
	public static final int DENSITYOVERRIDE_DEFAULT = 0;
	public static final String DENSITYOVERRIDE_NAME = "DensityOverride";
	public static int MAXHEIGHTOVERRIDE;
	public static final int MAXHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String MAXHEIGHTOVERRIDE_NAME = "MaxHeightOverride";
	public static int MINHEIGHTOVERRIDE;
	public static final int MINHEIGHTOVERRIDE_DEFAULT = 0;
	public static final String MINHEIGHTOVERRIDE_NAME = "MinHeightOverride";
	public static int VEINCOUNTOVERRIDE;
	public static final int VEINCOUNTOVERRIDE_DEFAULT = 0;
	public static final String VEINCOUNTOVERRIDE_NAME = "VeinCountOverride";
	//Hardened Clay
	public static boolean HARDGENERATE;
	public static final boolean HARDGENERATE_DEFAULT = true;
	public static final String HARDGENERATE_NAME = "HardGenerate";
	public static String HARDOREGENRATE;
	public static final String HARDOREGENRATE_DEFAULT = "Iron";
	public static final String HARDOREGENRATE_NAME = "HardOreGenRate";
	public static int HARDDENSITYOVERRIDE;
	public static final int HARDDENSITYOVERRIDE_DEFAULT = 0;
	public static final String HARDDENSITYOVERRIDE_NAME = "HardDensityOverride";
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
}
