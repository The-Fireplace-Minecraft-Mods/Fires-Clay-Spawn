package the_fireplace.clayspawn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import the_fireplace.clayspawn.config.ConfigChangedHandler;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.clayspawn.worldgen.WorldGeneratorClay;
import the_fireplace.fireplacecore.FireCoreBaseFile;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid=ClaySpawnBase.MODID, name=ClaySpawnBase.MODNAME, version=ClaySpawnBase.VERSION, acceptedMinecraftVersions = "1.8", guiFactory = "the_fireplace.clayspawn.config.ClaySpawnGuiFactory", dependencies="required-after:fireplacecore@[1.0.3.0,)")
public class ClaySpawnBase {
	@Instance(ClaySpawnBase.MODID)
	public static ClaySpawnBase instance;
	public static final String MODID = "clayspawn";
	public static final String MODNAME = "Fire's Clay Spawn";
	public static final String VERSION = "2.0.0.2";
	
	private static int updateNotification;
	private static String releaseVersion;
	private static String prereleaseVersion;
	private static final String downloadURL = "http://goo.gl/Xy3Aak";
	
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
		FMLCommonHandler.instance().bus().register(new ConfigChangedHandler());
		file = new Configuration(event.getSuggestedConfigurationFile());
		file.load();
		OREGENRATE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.OREGENRATE_NAME, ConfigValues.OREGENRATE_DEFAULT);
		DENSITYOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.DENSITYOVERRIDE_NAME, ConfigValues.DENSITYOVERRIDE_DEFAULT);
		HEIGHTOVERRIDE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.HEIGHTOVERRIDE_NAME, ConfigValues.HEIGHTOVERRIDE_DEFAULT);
		syncConfig();
		retriveCurrentVersions();
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
		updateNotification=FireCoreBaseFile.getUpdateNotification();
		if (!prereleaseVersion.equals("")
				&& !releaseVersion.equals("")) {
			switch (updateNotification) {
			case 0:
				if (isHigherVersion(VERSION, releaseVersion) && isHigherVersion(prereleaseVersion, releaseVersion)) {
					sendToPlayer(
							player,
							"§6A new version of "+MODNAME+" is available!\n§l§c========== §4"
									+ releaseVersion
									+ "§c ==========\n"
									+ "§6Download it at §e" + downloadURL + "§6!");
				}else if(isHigherVersion(VERSION, prereleaseVersion)){
					sendToPlayer(
							player,
							"§6A new version of "+MODNAME+" is available!\n§l§c========== §4"
									+ prereleaseVersion
									+ "§c ==========\n"
									+ "§6Download it at §e" + downloadURL + "§6!");
				}

				break;
			case 1:
				if (isHigherVersion(VERSION, releaseVersion)) {
					sendToPlayer(
							player,
							"§6A new version of "+MODNAME+" is available!\n§l§c========== §4"
									+ releaseVersion
									+ "§c ==========\n"
									+ "§6Download it at §e" + downloadURL + "§6!");
				}
				break;
			case 2:
				
				break;
			}
		}
	}
	
	/**
	 * Sends a chat message to the current player. Only works client side
	 * 
	 * @param message
	 *            the message to be sent
	 */
	private static void sendToPlayer(EntityPlayer player, String message) {
		String[] lines = message.split("\n");

		for (String line : lines)
			((ICommandSender) player)
					.addChatMessage(new ChatComponentText(line));
	}

	/**
	 * Checks if the new version is higher than the current one
	 * 
	 * @param currentVersion
	 *            The version which is considered current
	 * @param newVersion
	 *            The version which is considered new
	 * @return Whether the new version is higher than the current one or not
	 */
	private static boolean isHigherVersion(String currentVersion,
			String newVersion) {
		final int[] _current = splitVersion(currentVersion);
		final int[] _new = splitVersion(newVersion);

		return (_current[0] < _new[0])
				|| ((_current[0] == _new[0]) && (_current[1] < _new[1]))
				|| ((_current[0] == _new[0]) && (_current[1] == _new[1]) && (_current[2] < _new[2]))
				|| ((_current[0] == _new[0]) && (_current[1] == _new[1]) && (_current[2] == _new[2]) && (_current[3] < _new[3]));
	}

	/**
	 * Splits a version in its number components (Format ".\d+\.\d+\.\d+.*" )
	 * 
	 * @param Version
	 *            The version to be splitted (Format is important!
	 * @return The numeric version components as an integer array
	 */
	private static int[] splitVersion(String Version) {
		final String[] tmp = Version.split("\\.");
		final int size = tmp.length;
		final int out[] = new int[size];

		for (int i = 0; i < size; i++) {
			out[i] = Integer.parseInt(tmp[i]);
		}

		return out;
	}

	/**
	 * Retrieves what the latest version is from Dropbox
	 */
	private static void retriveCurrentVersions() {
		try {
			releaseVersion = get_content(new URL(
					"https://dl.dropboxusercontent.com/s/p8r9sxhab98xqb5/release.version?dl=0")
					.openConnection());

			prereleaseVersion = get_content(new URL(
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

	private static String get_content(URLConnection con) throws IOException {
		String output = "";

		if (con != null) {
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			String input;

			while ((input = br.readLine()) != null) {
				output = output + input;
			}
			br.close();
		}

		return output;
	}
}
