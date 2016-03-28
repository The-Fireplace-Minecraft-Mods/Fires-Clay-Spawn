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
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.clayspawn.api.CSAPI;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.config.OreGenEntries;
import the_fireplace.clayspawn.event.ForgeEvents;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;

import java.io.File;
import java.util.Map;
/**
 * @author The_Fireplace
 */
@Mod(modid=ClaySpawn.MODID, name=ClaySpawn.MODNAME, guiFactory = "the_fireplace.clayspawn.config.ClaySpawnGuiFactory")
public class ClaySpawn {
	@Instance(ClaySpawn.MODID)
	public static ClaySpawn instance;
	public static final String MODID = "clayspawn";
	public static final String MODNAME = "Fire's Clay Spawn";
	public static String VERSION;
	public static final String curseCode = "225539-fires-clay-spawn";

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
		String[] version = event.getModMetadata().version.split("\\.");
		if(version[3].equals("BUILDNUMBER"))//Dev environment
			VERSION = event.getModMetadata().version.replace("BUILDNUMBER", "9001");
		else//Released build
			VERSION = event.getModMetadata().version;

		CSAPI.registerOre("iron", 65, 8);
		CSAPI.registerOre("coal", 128, 16);
		CSAPI.registerOre("diamond", 15, 7);
		CSAPI.registerOre("gold", 32, 8);
		CSAPI.registerOre("emerald", 32, 4, 1);
		CSAPI.registerOre("lapis", 31, 6);
		CSAPI.registerOre("redstone", 16, 7);
		//Mod Ores
		CSAPI.registerOre("unlogicii:fossil", 12, 2);
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
		if(event.getSide().isClient())
			OREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
		if(event.getSide().isClient())
			HARDOREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
		transferOldConfig(event.getSuggestedConfigurationFile());
		syncConfig();
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(wg, 1);
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
