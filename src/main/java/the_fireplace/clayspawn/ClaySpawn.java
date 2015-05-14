package the_fireplace.clayspawn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.event.FMLEvents;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;
import the_fireplace.fireplacecore.FireCoreBaseFile;
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
	public static final String VERSION = "2.0.4.0";

	private static int updateNotification;
	private static String releaseVersion;
	private static String prereleaseVersion;
	private static final String downloadURL = "http://goo.gl/vi8Kom";
	public static NBTTagCompound update = new NBTTagCompound();

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
		retriveCurrentVersions();
		FireCoreBaseFile.instance.addUpdateInfo(update, MODNAME, VERSION, prereleaseVersion, releaseVersion, downloadURL, MODID);
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new WorldGeneratorClay(), 1);
	}
	/**
	 * This method is client side called when a player joins the game. Both for
	 * a server or a single player world.
	 */
	public static void onPlayerJoinClient(EntityPlayer player,
			ClientConnectedToServerEvent event) {
		updateNotification=FireCoreBaseFile.instance.getUpdateNotification();
		if (!prereleaseVersion.equals("")
				&& !releaseVersion.equals("")) {
			switch (updateNotification) {
			case 0:
				if (FireCoreBaseFile.isHigherVersion(VERSION, releaseVersion) && FireCoreBaseFile.isHigherVersion(prereleaseVersion, releaseVersion)) {
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}else if(FireCoreBaseFile.isHigherVersion(VERSION, prereleaseVersion)){
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}

				break;
			case 1:
				if (FireCoreBaseFile.isHigherVersion(VERSION, releaseVersion)) {
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}
				break;
			case 2:

				break;
			}
		}
	}

	/**
	 * Retrieves what the latest version is from Dropbox
	 */
	private static void retriveCurrentVersions() {
		try {
			releaseVersion = FireCoreBaseFile.get_content(new URL(
					"https://dl.dropboxusercontent.com/s/p8r9sxhab98xqb5/release.version?dl=0")
			.openConnection());

			prereleaseVersion = FireCoreBaseFile.get_content(new URL(
					"https://dl.dropboxusercontent.com/s/f78akvw8ugw52ye/prerelease.version?dl=0")
			.openConnection());

		} catch (final MalformedURLException e) {
			System.out.println("Malformed URL Exception");
			releaseVersion = "";
			prereleaseVersion = "";
		} catch (final IOException e) {
			System.out.println("IO Exception");
			releaseVersion = "";
			prereleaseVersion = "";
		}
	}
}
