package the_fireplace.clayspawn;

import com.google.common.collect.Maps;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.clayspawn.api.CSAPI;
import the_fireplace.clayspawn.compat.IModCompat;
import the_fireplace.clayspawn.compat.JERCompat;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.config.GuiIntegerSlider;
import the_fireplace.clayspawn.config.OreGenEntries;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;

import java.io.File;
import java.util.Map;

/**
 * @author The_Fireplace
 */
@Mod(modid = ClaySpawn.MODID, name = ClaySpawn.MODNAME, version = "${version}", guiFactory = "the_fireplace.clayspawn.config.ClaySpawnGuiFactory", updateJSON = "https://bitbucket.org/The_Fireplace/minecraft-mod-updates/raw/master/clayspawn.json", acceptedMinecraftVersions = "[1.12,1.13)", acceptableRemoteVersions = "*")
public class ClaySpawn {
	@Instance(ClaySpawn.MODID)
	public static ClaySpawn instance;
	public static final String MODID = "clayspawn";
	public static final String MODNAME = "Fire's Clay Spawn";

	public WorldGeneratorClay wg = new WorldGeneratorClay();
	public Map<Object, String> entries = Maps.newHashMap();

	public static Configuration file;
	//public static Property DIMENSIONLIST_PROPERTY;
	//Clay
	public static Property GENERATE_PROPERTY;
	public static Property OREGENRATE_PROPERTY;
	public static Property DENSITYOVERRIDE_PROPERTY;
	public static Property MAXHEIGHTOVERRIDE_PROPERTY;
	public static Property MINHEIGHTOVERRIDE_PROPERTY;
	public static Property VEINCOUNTOVERRIDE_PROPERTY;
	//Terracotta
	public static Property HARDGENERATE_PROPERTY;
	public static Property HARDOREGENRATE_PROPERTY;
	public static Property HARDDENSITYOVERRIDE_PROPERTY;
	public static Property HARDMAXHEIGHTOVERRIDE_PROPERTY;
	public static Property HARDMINHEIGHTOVERRIDE_PROPERTY;
	public static Property HARDVEINCOUNTOVERRIDE_PROPERTY;
	public static Property COLORFULCLAY_PROPERTY;
	//Glazed Terracotta
	public static Property GLAZEDGENERATE_PROPERTY;
	public static Property GLAZEDOREGENRATE_PROPERTY;
	public static Property GLAZEDDENSITYOVERRIDE_PROPERTY;
	public static Property GLAZEDMAXHEIGHTOVERRIDE_PROPERTY;
	public static Property GLAZEDMINHEIGHTOVERRIDE_PROPERTY;
	public static Property GLAZEDVEINCOUNTOVERRIDE_PROPERTY;

	public static void syncConfig() {
		//ConfigValues.DIMENSIONLIST = DIMENSIONLIST_PROPERTY.getStringList();
		ConfigValues.GENERATE = GENERATE_PROPERTY.getBoolean();
		ConfigValues.OREGENRATE = OREGENRATE_PROPERTY.getString();
		ConfigValues.DENSITYOVERRIDE = DENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.MAXHEIGHTOVERRIDE = MAXHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.MINHEIGHTOVERRIDE = MINHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.VEINCOUNTOVERRIDE = VEINCOUNTOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDGENERATE = HARDGENERATE_PROPERTY.getBoolean();
		ConfigValues.HARDOREGENRATE = HARDOREGENRATE_PROPERTY.getString();
		ConfigValues.HARDDENSITYOVERRIDE = HARDDENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDMAXHEIGHTOVERRIDE = HARDMAXHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDMINHEIGHTOVERRIDE = HARDMINHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDVEINCOUNTOVERRIDE = HARDVEINCOUNTOVERRIDE_PROPERTY.getInt();
		ConfigValues.COLORFULCLAY = COLORFULCLAY_PROPERTY.getBoolean();
		ConfigValues.GLAZEDGENERATE = GLAZEDGENERATE_PROPERTY.getBoolean();
		ConfigValues.GLAZEDOREGENRATE = GLAZEDOREGENRATE_PROPERTY.getString();
		ConfigValues.GLAZEDDENSITYOVERRIDE = GLAZEDDENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.GLAZEDMAXHEIGHTOVERRIDE = GLAZEDMAXHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.GLAZEDMINHEIGHTOVERRIDE = GLAZEDMINHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.GLAZEDVEINCOUNTOVERRIDE = GLAZEDVEINCOUNTOVERRIDE_PROPERTY.getInt();
		if (file.hasChanged())
			file.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CSAPI.registerOre("iron", 65, 8);
		CSAPI.registerOre("coal", 128, 16);
		CSAPI.registerOre("diamond", 15, 7);
		CSAPI.registerOre("gold", 32, 8);
		CSAPI.registerOre("emerald", 32, 4, 1);
		CSAPI.registerOre("lapis", 31, 6);
		CSAPI.registerOre("redstone", 16, 7);
		//Mod Ores
		CSAPI.registerOre("forestry:apatite", 184, 64, 36, 1);
		CSAPI.registerOre("forestry:copper", 108, 32, 6, 20);
		CSAPI.registerOre("forestry:tin", 92, 16, 6, 18);
		CSAPI.registerOre("brainstonemod:brainstone", 32, 0, 20, 1);
		file = new Configuration(new File(event.getModConfigurationDirectory(), "fclayspawn.cfg"));
		file.load();
		GENERATE_PROPERTY = file.get("clay", ConfigValues.GENERATE_NAME, ConfigValues.GENERATE_DEFAULT, I18n.translateToLocal(ConfigValues.GENERATE_NAME + ".tooltip"));
		OREGENRATE_PROPERTY = file.get("clay", ConfigValues.OREGENRATE_NAME, ConfigValues.OREGENRATE_DEFAULT, I18n.translateToLocal(ConfigValues.OREGENRATE_NAME + ".tooltip"));
		DENSITYOVERRIDE_PROPERTY = file.get("clay", ConfigValues.DENSITYOVERRIDE_NAME, ConfigValues.DENSITYOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.DENSITYOVERRIDE_NAME + ".tooltip"));
		MAXHEIGHTOVERRIDE_PROPERTY = file.get("clay", ConfigValues.MAXHEIGHTOVERRIDE_NAME, ConfigValues.MAXHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.MAXHEIGHTOVERRIDE_NAME + ".tooltip"));
		MINHEIGHTOVERRIDE_PROPERTY = file.get("clay", ConfigValues.MINHEIGHTOVERRIDE_NAME, ConfigValues.MINHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.MINHEIGHTOVERRIDE_NAME + ".tooltip"));
		VEINCOUNTOVERRIDE_PROPERTY = file.get("clay", ConfigValues.VEINCOUNTOVERRIDE_NAME, ConfigValues.VEINCOUNTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.VEINCOUNTOVERRIDE_NAME + ".tooltip"));
		HARDGENERATE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDGENERATE_NAME, ConfigValues.HARDGENERATE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDGENERATE_NAME + ".tooltip"));
		HARDOREGENRATE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDOREGENRATE_NAME, ConfigValues.HARDOREGENRATE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDOREGENRATE_NAME + ".tooltip"));
		HARDDENSITYOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDDENSITYOVERRIDE_NAME, ConfigValues.HARDDENSITYOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDDENSITYOVERRIDE_NAME + ".tooltip"));
		HARDMAXHEIGHTOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDMAXHEIGHTOVERRIDE_NAME, ConfigValues.HARDMAXHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDMAXHEIGHTOVERRIDE_NAME + ".tooltip"));
		HARDMINHEIGHTOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDMINHEIGHTOVERRIDE_NAME, ConfigValues.HARDMINHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDMINHEIGHTOVERRIDE_NAME + ".tooltip"));
		HARDVEINCOUNTOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDVEINCOUNTOVERRIDE_NAME, ConfigValues.HARDVEINCOUNTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDVEINCOUNTOVERRIDE_NAME + ".tooltip"));
		COLORFULCLAY_PROPERTY = file.get("hardenedclay", ConfigValues.COLORFULCLAY_NAME, ConfigValues.COLORFULCLAY_DEFAULT, I18n.translateToLocal(ConfigValues.COLORFULCLAY_NAME + ".tooltip"));
		GLAZEDGENERATE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDGENERATE_NAME, ConfigValues.GLAZEDGENERATE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDGENERATE_NAME + ".tooltip"));
		GLAZEDOREGENRATE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDOREGENRATE_NAME, ConfigValues.GLAZEDOREGENRATE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDOREGENRATE_NAME + ".tooltip"));
		GLAZEDDENSITYOVERRIDE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDDENSITYOVERRIDE_NAME, ConfigValues.GLAZEDDENSITYOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDDENSITYOVERRIDE_NAME + ".tooltip"));
		GLAZEDMAXHEIGHTOVERRIDE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDMAXHEIGHTOVERRIDE_NAME, ConfigValues.GLAZEDMAXHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDMAXHEIGHTOVERRIDE_NAME + ".tooltip"));
		GLAZEDMINHEIGHTOVERRIDE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDMINHEIGHTOVERRIDE_NAME, ConfigValues.GLAZEDMINHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDMINHEIGHTOVERRIDE_NAME + ".tooltip"));
		GLAZEDVEINCOUNTOVERRIDE_PROPERTY = file.get("glazedterracotta", ConfigValues.GLAZEDVEINCOUNTOVERRIDE_NAME, ConfigValues.GLAZEDVEINCOUNTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.GLAZEDVEINCOUNTOVERRIDE_NAME + ".tooltip"));

		DENSITYOVERRIDE_PROPERTY.setMinValue(0);
		MAXHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		MINHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		VEINCOUNTOVERRIDE_PROPERTY.setMinValue(0);
		DENSITYOVERRIDE_PROPERTY.setMaxValue(36);
		MAXHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		MINHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		VEINCOUNTOVERRIDE_PROPERTY.setMaxValue(128);
		HARDDENSITYOVERRIDE_PROPERTY.setMinValue(0);
		HARDMAXHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		HARDMINHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		HARDVEINCOUNTOVERRIDE_PROPERTY.setMinValue(0);
		HARDDENSITYOVERRIDE_PROPERTY.setMaxValue(36);
		HARDMAXHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		HARDMINHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		HARDVEINCOUNTOVERRIDE_PROPERTY.setMaxValue(128);
		GLAZEDDENSITYOVERRIDE_PROPERTY.setMinValue(0);
		GLAZEDMAXHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		GLAZEDMINHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		GLAZEDVEINCOUNTOVERRIDE_PROPERTY.setMinValue(0);
		GLAZEDDENSITYOVERRIDE_PROPERTY.setMaxValue(36);
		GLAZEDMAXHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		GLAZEDMINHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		GLAZEDVEINCOUNTOVERRIDE_PROPERTY.setMaxValue(128);
		if (event.getSide().isClient()) {
			OREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
			DENSITYOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			MAXHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			MINHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			VEINCOUNTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			HARDOREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
			HARDDENSITYOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			HARDMAXHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			HARDMINHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			HARDVEINCOUNTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			GLAZEDOREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
			GLAZEDDENSITYOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			GLAZEDMAXHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			GLAZEDMINHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
			GLAZEDVEINCOUNTOVERRIDE_PROPERTY.setConfigEntryClass(GuiIntegerSlider.class);
		}
		transferAncientConfig(event.getSuggestedConfigurationFile());
		transferOldConfig(new File(event.getModConfigurationDirectory() + "fclayspawn.cfg"));
		syncConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(wg, 1);
		IModCompat compat;
		if (Loader.isModLoaded("jeresources")) {
			compat = new JERCompat();
			compat.init();
		}
	}

	@EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
		//Clay
		if (ConfigValues.DENSITYOVERRIDE > 0)
			wg.setRate(ConfigValues.DENSITYOVERRIDE);
		else if (wg.genrate.get(ConfigValues.OREGENRATE) != null)
			wg.setRate((int) wg.genrate.get(ConfigValues.OREGENRATE));
		if (ConfigValues.MAXHEIGHTOVERRIDE > 0)
			wg.setMaxLayer(ConfigValues.MAXHEIGHTOVERRIDE);
		else if (wg.genlayermax.get(ConfigValues.OREGENRATE) != null)
			wg.setMaxLayer((int) wg.genlayermax.get(ConfigValues.OREGENRATE));
		if (ConfigValues.MINHEIGHTOVERRIDE > 0)
			wg.setMinLayer(ConfigValues.MINHEIGHTOVERRIDE);
		else if (wg.genlayermin.get(ConfigValues.OREGENRATE) != null)
			wg.setMinLayer((int) wg.genlayermin.get(ConfigValues.OREGENRATE));
		if (ConfigValues.VEINCOUNTOVERRIDE > 0)
			wg.setMaxLayer(ConfigValues.VEINCOUNTOVERRIDE);
		else if (wg.gencount.get(ConfigValues.OREGENRATE) != null)
			wg.setVeinCount((int) wg.gencount.get(ConfigValues.OREGENRATE));
		//Terracotta
		if (ConfigValues.HARDDENSITYOVERRIDE > 0)
			wg.setHardRate(ConfigValues.HARDDENSITYOVERRIDE);
		else if (wg.genrate.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardRate((int) wg.genrate.get(ConfigValues.HARDOREGENRATE));
		if (ConfigValues.HARDMAXHEIGHTOVERRIDE > 0)
			wg.setHardMaxLayer(ConfigValues.HARDMAXHEIGHTOVERRIDE);
		else if (wg.genlayermax.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardMaxLayer((int) wg.genlayermax.get(ConfigValues.HARDOREGENRATE));
		if (ConfigValues.HARDMINHEIGHTOVERRIDE > 0)
			wg.setHardMinLayer(ConfigValues.HARDMINHEIGHTOVERRIDE);
		else if (wg.genlayermin.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardMinLayer((int) wg.genlayermin.get(ConfigValues.HARDOREGENRATE));
		if (ConfigValues.HARDVEINCOUNTOVERRIDE > 0)
			wg.setHardMaxLayer(ConfigValues.HARDVEINCOUNTOVERRIDE);
		else if (wg.gencount.get(ConfigValues.OREGENRATE) != null)
			wg.setHardVeinCount((int) wg.gencount.get(ConfigValues.HARDOREGENRATE));
		//Glazed Terracotta
		if (ConfigValues.GLAZEDDENSITYOVERRIDE > 0)
			wg.setGlazedRate(ConfigValues.GLAZEDDENSITYOVERRIDE);
		else if (wg.genrate.get(ConfigValues.GLAZEDOREGENRATE) != null)
			wg.setGlazedRate((int) wg.genrate.get(ConfigValues.GLAZEDOREGENRATE));
		if (ConfigValues.GLAZEDMAXHEIGHTOVERRIDE > 0)
			wg.setGlazedMaxLayer(ConfigValues.GLAZEDMAXHEIGHTOVERRIDE);
		else if (wg.genlayermax.get(ConfigValues.GLAZEDOREGENRATE) != null)
			wg.setGlazedMaxLayer((int) wg.genlayermax.get(ConfigValues.GLAZEDOREGENRATE));
		if (ConfigValues.GLAZEDMINHEIGHTOVERRIDE > 0)
			wg.setGlazedMinLayer(ConfigValues.GLAZEDMINHEIGHTOVERRIDE);
		else if (wg.genlayermin.get(ConfigValues.GLAZEDOREGENRATE) != null)
			wg.setGlazedMinLayer((int) wg.genlayermin.get(ConfigValues.GLAZEDOREGENRATE));
		if (ConfigValues.GLAZEDVEINCOUNTOVERRIDE > 0)
			wg.setGlazedMaxLayer(ConfigValues.GLAZEDVEINCOUNTOVERRIDE);
		else if (wg.gencount.get(ConfigValues.OREGENRATE) != null)
			wg.setGlazedVeinCount((int) wg.gencount.get(ConfigValues.GLAZEDOREGENRATE));
	}

	private void transferAncientConfig(File file) {
		if (file.exists()) {
			Configuration temp = new Configuration(file);
			ConfigCategory cat = temp.getCategory(Configuration.CATEGORY_GENERAL);
			if (cat.containsKey("OreGenRate"))
				OREGENRATE_PROPERTY.set(cat.get("OreGenRate").getString());
			if (cat.containsKey("DensityOverride"))
				DENSITYOVERRIDE_PROPERTY.set(cat.get("DensityOverride").getInt());
			if (cat.containsKey("HeightOverride"))
				MAXHEIGHTOVERRIDE_PROPERTY.set(cat.get("HeightOverride").getInt());
			if (cat.containsKey("MinHeightOverride"))
				MINHEIGHTOVERRIDE_PROPERTY.set(cat.get("MinHeightOverride").getInt());
			file.delete();
		}
	}

	private void transferOldConfig(File file) {
		if (file.exists()) {
			Configuration temp = new Configuration(file);
			ConfigCategory cat = temp.getCategory("clay");
			if (cat.containsKey("Generate"))
				GENERATE_PROPERTY.set(cat.get("Generate").getString());
			if (cat.containsKey("OreGenRate"))
				OREGENRATE_PROPERTY.set(cat.get("OreGenRate").getString());
			if (cat.containsKey("DensityOverride"))
				DENSITYOVERRIDE_PROPERTY.set(cat.get("DensityOverride").getInt());
			if (cat.containsKey("MaxHeightOverride"))
				MAXHEIGHTOVERRIDE_PROPERTY.set(cat.get("MaxHeightOverride").getInt());
			if (cat.containsKey("MinHeightOverride"))
				MINHEIGHTOVERRIDE_PROPERTY.set(cat.get("MinHeightOverride").getInt());
			cat = temp.getCategory("hardenedclay");
			if (cat.containsKey("HardGenerate"))
				HARDGENERATE_PROPERTY.set(cat.get("HardGenerate").getString());
			if (cat.containsKey("HardOreGenRate"))
				HARDOREGENRATE_PROPERTY.set(cat.get("HardOreGenRate").getString());
			if (cat.containsKey("HardDensityOverride"))
				HARDDENSITYOVERRIDE_PROPERTY.set(cat.get("HardDensityOverride").getInt());
			if (cat.containsKey("HardMaxHeightOverride"))
				HARDMAXHEIGHTOVERRIDE_PROPERTY.set(cat.get("HardMaxHeightOverride").getInt());
			if (cat.containsKey("HardMinHeightOverride"))
				HARDMINHEIGHTOVERRIDE_PROPERTY.set(cat.get("HardMinHeightOverride").getInt());
			if (cat.containsKey("ColorfulClay"))
				COLORFULCLAY_PROPERTY.set(cat.get("ColorfulClay").getInt());
			file.delete();
		}
	}
}
