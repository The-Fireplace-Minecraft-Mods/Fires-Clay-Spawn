package the_fireplace.clayspawn.api;

import the_fireplace.clayspawn.ClaySpawn;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CSAPI {
	/**
	 * Registers the ore generation style
	 * @param orename
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxlayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate amount per vein
	 */
	public static void registerOre(String orename, int maxlayer, int veinSize){
		registerOre(orename, maxlayer, 0, veinSize);
	}
	/**
	 * Registers the ore generation style
	 * @param orename
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxlayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param minlayer
	 * 		The lowest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate amount per vein
	 */
	public static void registerOre(String orename, int maxlayer, int minlayer, int veinSize){
		registerOre(orename, maxlayer, minlayer, veinSize, 0);
	}

	/**
	 * Registers the ore generation style
	 * @param orename
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxlayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param minlayer
	 * 		The lowest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate amount per vein
	 * @param veinCount
	 * 		The number of veins per chunk
	 */
	@SuppressWarnings("unchecked")
	public static void registerOre(String orename, int maxlayer, int minlayer, int veinSize, int veinCount){
		String on = orename.toLowerCase();
		ClaySpawn.instance.wg.genlayermax.put(on, maxlayer);
		ClaySpawn.instance.wg.genlayermin.put(on, minlayer);
		ClaySpawn.instance.wg.genrate.put(on, veinSize);
		ClaySpawn.instance.wg.gencount.put(on, veinCount);
		ClaySpawn.instance.entries.put(on, orename);
	}
}
