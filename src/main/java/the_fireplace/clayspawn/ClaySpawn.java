package the_fireplace.clayspawn;

import com.google.common.collect.Maps;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
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
	public static Property OREGENRATE_PROPERTY;
	public static Property DENSITYOVERRIDE_PROPERTY;
	public static Property HEIGHTOVERRIDE_PROPERTY;
	public static Property MINHEIGHTOVERRIDE_PROPERTY;

	public static void syncConfig(){
		ConfigValues.OREGENRATE = OREGENRATE_PROPERTY.getString();
		ConfigValues.DENSITYOVERRIDE = DENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.HEIGHTOVERRIDE = HEIGHTOVERRIDE_PROPERTY.getInt();
		ConfigValues.MINHEIGHTOVERRIDE = MINHEIGHTOVERRIDE_PROPERTY.getInt();
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
		CSAPI.registerOre("clayspawn:clayworld", 255, 32);
		CSAPI.registerOre("forestry:apatite", 184, 64, 36);
		CSAPI.registerOre("forestry:copper", 108, 32, 6);
		CSAPI.registerOre("forestry:tin", 92, 16, 6);
		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
		file = new Configuration(event.getSuggestedConfigurationFile());
		file.load();
		OREGENRATE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.OREGENRATE_NAME, ConfigValues.OREGENRATE_DEFAULT, StatCollector.translateToLocal(ConfigValues.OREGENRATE_NAME+".tooltip"));
		DENSITYOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.DENSITYOVERRIDE_NAME, ConfigValues.DENSITYOVERRIDE_DEFAULT, StatCollector.translateToLocal(ConfigValues.DENSITYOVERRIDE_NAME+".tooltip"));
		HEIGHTOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.HEIGHTOVERRIDE_NAME, ConfigValues.HEIGHTOVERRIDE_DEFAULT, StatCollector.translateToLocal(ConfigValues.HEIGHTOVERRIDE_NAME+".tooltip"));
		MINHEIGHTOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.MINHEIGHTOVERRIDE_NAME, ConfigValues.MINHEIGHTOVERRIDE_DEFAULT, StatCollector.translateToLocal(ConfigValues.MINHEIGHTOVERRIDE_NAME+".tooltip"));
		if(event.getSide().isClient())
			OREGENRATE_PROPERTY.setConfigEntryClass(OreGenEntries.class);
		syncConfig();
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(wg, 1);
	}
}
