package the_fireplace.clayspawn;

import com.google.common.collect.Maps;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.clayspawn.api.CSAPI;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.config.GuiImpreciseSlider;
import the_fireplace.clayspawn.config.OreGenEntries;
import the_fireplace.clayspawn.event.ForgeEvents;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;

import java.io.File;
import java.util.Map;
/**
 * @author The_Fireplace
 */
@Mod(modid=ClaySpawn.MODID, name=ClaySpawn.MODNAME, guiFactory = "the_fireplace.clayspawn.config.ClaySpawnGuiFactory", updateJSON = "http://caterpillar.bitnamiapp.com/jsons/clayspawn.json", acceptedMinecraftVersions = "[1.9.4,1.10.2]")
public class ClaySpawn {
	@Instance(ClaySpawn.MODID)
	public static ClaySpawn instance;
	public static final String MODID = "clayspawn";
	public static final String MODNAME = "Fire's Clay Spawn";

	public WorldGeneratorClay wg = new WorldGeneratorClay();
	public Map entries = Maps.newHashMap();

	public static Configuration file;
	//Clay
	public static Property GENERATE_PROPERTY;
	public static Property OREGENRATE_PROPERTY;
	public static Property DENSITYOVERRIDE_PROPERTY;
	public static Property MAXHEIGHTOVERRIDE_PROPERTY;
	public static Property MINHEIGHTOVERRIDE_PROPERTY;
	//Hardened Clay
	public static Property HARDGENERATE_PROPERTY;
	public static Property HARDOREGENRATE_PROPERTY;
	public static Property HARDDENSITYOVERRIDE_PROPERTY;
	public static Property HARDMAXHEIGHTOVERRIDE_PROPERTY;
	public static Property HARDMINHEIGHTOVERRIDE_PROPERTY;
	public static Property COLORFULCLAY_PROPERTY;

	public static void syncConfig(){
		ConfigValues.GENERATE = GENERATE_PROPERTY.getBoolean();
		ConfigValues.OREGENRATE = OREGENRATE_PROPERTY.getString();
		ConfigValues.DENSITYOVERRIDE = DENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.MAXHEIGHTOVERRIDE = MAXHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.MINHEIGHTOVERRIDE = MINHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDGENERATE = HARDGENERATE_PROPERTY.getBoolean();
		ConfigValues.HARDOREGENRATE = HARDOREGENRATE_PROPERTY.getString();
		ConfigValues.HARDDENSITYOVERRIDE = HARDDENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDMAXHEIGHTOVERRIDE = HARDMAXHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.HARDMINHEIGHTOVERRIDE = HARDMINHEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.COLORFULCLAY = COLORFULCLAY_PROPERTY.getBoolean();
		if(file.hasChanged())
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
		CSAPI.registerOre("frt:fossil", 12, 2);
		CSAPI.registerOre("forestry:apatite", 184, 64, 36);
		CSAPI.registerOre("forestry:copper", 108, 32, 6);
		CSAPI.registerOre("forestry:tin", 92, 16, 6);
		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
		file = new Configuration(new File(event.getModConfigurationDirectory()+"fclayspawn.cfg"));
		file.load();
		GENERATE_PROPERTY = file.get("clay", ConfigValues.GENERATE_NAME, ConfigValues.GENERATE_DEFAULT, I18n.translateToLocal(ConfigValues.GENERATE_NAME+".tooltip"));
		OREGENRATE_PROPERTY = file.get("clay", ConfigValues.OREGENRATE_NAME, ConfigValues.OREGENRATE_DEFAULT, I18n.translateToLocal(ConfigValues.OREGENRATE_NAME+".tooltip"));
		DENSITYOVERRIDE_PROPERTY = file.get("clay", ConfigValues.DENSITYOVERRIDE_NAME, ConfigValues.DENSITYOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.DENSITYOVERRIDE_NAME+".tooltip"));
		MAXHEIGHTOVERRIDE_PROPERTY = file.get("clay", ConfigValues.MAXHEIGHTOVERRIDE_NAME, ConfigValues.MAXHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.MAXHEIGHTOVERRIDE_NAME +".tooltip"));
		MINHEIGHTOVERRIDE_PROPERTY = file.get("clay", ConfigValues.MINHEIGHTOVERRIDE_NAME, ConfigValues.MINHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.MINHEIGHTOVERRIDE_NAME+".tooltip"));
		HARDGENERATE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDGENERATE_NAME, ConfigValues.HARDGENERATE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDGENERATE_NAME+".tooltip"));
		HARDOREGENRATE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDOREGENRATE_NAME, ConfigValues.HARDOREGENRATE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDOREGENRATE_NAME+".tooltip"));
		HARDDENSITYOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDDENSITYOVERRIDE_NAME, ConfigValues.HARDDENSITYOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDDENSITYOVERRIDE_NAME+".tooltip"));
		HARDMAXHEIGHTOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDMAXHEIGHTOVERRIDE_NAME, ConfigValues.HARDMAXHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDMAXHEIGHTOVERRIDE_NAME +".tooltip"));
		HARDMINHEIGHTOVERRIDE_PROPERTY = file.get("hardenedclay", ConfigValues.HARDMINHEIGHTOVERRIDE_NAME, ConfigValues.HARDMINHEIGHTOVERRIDE_DEFAULT, I18n.translateToLocal(ConfigValues.HARDMINHEIGHTOVERRIDE_NAME+".tooltip"));
		COLORFULCLAY_PROPERTY = file.get("hardenedclay", ConfigValues.COLORFULCLAY_NAME, ConfigValues.COLORFULCLAY_DEFAULT, I18n.translateToLocal(ConfigValues.COLORFULCLAY_NAME+".tooltip"));

		DENSITYOVERRIDE_PROPERTY.setMinValue(0);
		MAXHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		MINHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		HARDDENSITYOVERRIDE_PROPERTY.setMinValue(0);
		HARDMAXHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		HARDMINHEIGHTOVERRIDE_PROPERTY.setMinValue(0);
		DENSITYOVERRIDE_PROPERTY.setMaxValue(36);
		MAXHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		MINHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		HARDDENSITYOVERRIDE_PROPERTY.setMaxValue(36);
		HARDMAXHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		HARDMINHEIGHTOVERRIDE_PROPERTY.setMaxValue(255);
		if(event.getSide().isClient()) {
			OREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
			HARDOREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
			DENSITYOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
			MAXHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
			MINHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
			HARDDENSITYOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
			HARDMAXHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
			HARDMINHEIGHTOVERRIDE_PROPERTY.setConfigEntryClass(GuiImpreciseSlider.class);
		}
		transferOldConfig(event.getSuggestedConfigurationFile());
		syncConfig();
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(wg, 1);
	}

	@EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent event){
		//Regular Clay
		if(ConfigValues.DENSITYOVERRIDE > 0)
			wg.setRate(ConfigValues.DENSITYOVERRIDE);
		else
		if(wg.genrate.get(ConfigValues.OREGENRATE) != null)
			wg.setRate((int)wg.genrate.get(ConfigValues.OREGENRATE));
		if(ConfigValues.MAXHEIGHTOVERRIDE > 0)
			wg.setMaxLayer(ConfigValues.MAXHEIGHTOVERRIDE);
		else
		if(wg.genlayermax.get(ConfigValues.OREGENRATE) != null)
			wg.setMaxLayer((int)wg.genlayermax.get(ConfigValues.OREGENRATE));
		if(ConfigValues.MINHEIGHTOVERRIDE > 0)
			wg.setMinLayer(ConfigValues.MINHEIGHTOVERRIDE);
		else
		if(wg.genlayermin.get(ConfigValues.OREGENRATE) != null)
			wg.setMinLayer((int)wg.genlayermin.get(ConfigValues.OREGENRATE));
		//Hardened Clay
		if(ConfigValues.HARDDENSITYOVERRIDE > 0)
			wg.setHardRate(ConfigValues.HARDDENSITYOVERRIDE);
		else
		if(wg.genrate.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardRate((int)wg.genrate.get(ConfigValues.HARDOREGENRATE));
		if(ConfigValues.HARDMAXHEIGHTOVERRIDE > 0)
			wg.setHardMaxLayer(ConfigValues.HARDMAXHEIGHTOVERRIDE);
		else
		if(wg.genlayermax.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardMaxLayer((int)wg.genlayermax.get(ConfigValues.HARDOREGENRATE));
		if(ConfigValues.HARDMINHEIGHTOVERRIDE > 0)
			wg.setHardMinLayer(ConfigValues.HARDMINHEIGHTOVERRIDE);
		else
		if(wg.genlayermin.get(ConfigValues.HARDOREGENRATE) != null)
			wg.setHardMinLayer((int)wg.genlayermin.get(ConfigValues.HARDOREGENRATE));
	}

	private void transferOldConfig(File file){
		if(file.exists()){
			Configuration temp = new Configuration(file);
			ConfigCategory cat = temp.getCategory(Configuration.CATEGORY_GENERAL);
			if(cat.containsKey("OreGenRate"))
				OREGENRATE_PROPERTY.set(cat.get("OreGenRate").getString());
			if(cat.containsKey("DensityOverride"))
				DENSITYOVERRIDE_PROPERTY.set(cat.get("DensityOverride").getInt());
			if(cat.containsKey("HeightOverride"))
				MAXHEIGHTOVERRIDE_PROPERTY.set(cat.get("HeightOverride").getInt());
			if(cat.containsKey("MinHeightOverride"))
				MINHEIGHTOVERRIDE_PROPERTY.set(cat.get("MinHeightOverride").getInt());
			file.delete();
		}
	}
}
