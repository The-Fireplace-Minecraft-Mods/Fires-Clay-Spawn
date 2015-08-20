package the_fireplace.clayspawn.api;

import the_fireplace.clayspawn.ClaySpawn;

public class CSAPI {
	/**
	 * Registers the ore
	 * @param orename
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxlayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param rate
	 * 		The approximate amount per vein
	 */
	public static void registerOre(String orename, int maxlayer, int rate){
		String on = orename.toLowerCase();
		ClaySpawn.instance.wg.genlayer.put(on, maxlayer);
		ClaySpawn.instance.wg.genrate.put(on, rate);
		ClaySpawn.instance.entries.put(on, orename);
	}
}
