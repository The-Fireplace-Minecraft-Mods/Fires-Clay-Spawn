package the_fireplace.clayspawn;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.clayspawn.api.CSAPI;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.event.FMLEvents;
import the_fireplace.clayspawn.firecorecompat.FCCompat;
import the_fireplace.clayspawn.firecorecompat.FCCompatDummy;
import the_fireplace.clayspawn.firecorecompat.IFCCompat;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;
/**
 *
 * @author The_Fireplace
 *
 */
@Mod(modid=ClaySpawn.MODID, name=ClaySpawn.MODNAME, version=ClaySpawn.VERSION, acceptedMinecraftVersions = "1.8", guiFactory = "the_fireplace.clayspawn.config.ClaySpawnGuiFactory", dependencies="required-after:fireplacecore@[1.0.3.0,)")
public class ClaySpawn {
	@Instance(ClaySpawn.MODID)
	public static ClaySpawn instance;
	public static final String MODID = "clayspawn";
	public static final String MODNAME = "Fire's Clay Spawn";
	public static final String VERSION = "2.1.1.0";
	public static final String downloadURL = "http://goo.gl/vi8Kom";

	public WorldGeneratorClay wg = new WorldGeneratorClay();

	public static Configuration file;
	public static Property OREGENRATE_PROPERTY;
	public static Property DENSITYOVERRIDE_PROPERTY;
	public static Property HEIGHTOVERRIDE_PROPERTY;

	public static void syncConfig(){
		ConfigValues.OREGENRATE = OREGENRATE_PROPERTY.getString();
		ConfigValues.DENSITYOVERRIDE = DENSITYOVERRIDE_PROPERTY.getInt();
		ConfigValues.HEIGHTOVERRIDE = HEIGHTOVERRIDE_PROPERTY.getInt();
		if(file.hasChanged()){
			file.save();
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new FMLEvents());
		file = new Configuration(event.getSuggestedConfigurationFile());
		file.load();
		OREGENRATE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.OREGENRATE_NAME, ConfigValues.OREGENRATE_DEFAULT);
		OREGENRATE_PROPERTY.comment=StatCollector.translateToLocal("OreGenRate.tooltip");
		DENSITYOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.DENSITYOVERRIDE_NAME, ConfigValues.DENSITYOVERRIDE_DEFAULT);
		DENSITYOVERRIDE_PROPERTY.comment=StatCollector.translateToLocal("DensityOverride.tooltip");
		HEIGHTOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.HEIGHTOVERRIDE_NAME, ConfigValues.HEIGHTOVERRIDE_DEFAULT);
		HEIGHTOVERRIDE_PROPERTY.comment=StatCollector.translateToLocal("HeightOverride.tooltip");
		syncConfig();
		IFCCompat compat;
		if(Loader.isModLoaded("fireplacecore")){
			compat=new FCCompat();
		}else{
			compat=new FCCompatDummy();
		}
		compat.register();
		CSAPI.registerOre("iron", 65, 8);
		CSAPI.registerOre("coal", 128, 16);
		CSAPI.registerOre("diamond", 15, 7);
		CSAPI.registerOre("gold", 32, 8);
		CSAPI.registerOre("budder", 32, 8);
		CSAPI.registerOre("gold", 32, 8);
		CSAPI.registerOre("emerald", 32, 1);
		CSAPI.registerOre("lapis", 31, 6);
		CSAPI.registerOre("lapis lazuli", 31, 6);
		CSAPI.registerOre("redstone", 16, 7);
		//Mod Ores
		CSAPI.registerOre("unlogicii:fossil", 12, 2);
		CSAPI.registerOre("clayspawn:clayworld", 255, 32);
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(wg, 1);
	}
}
