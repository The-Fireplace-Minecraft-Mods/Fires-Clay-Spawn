package the_fireplace.clayspawn.api;

import the_fireplace.clayspawn.ClaySpawn;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CSAPI {
	/**
	 * Registers the ore generation style
	 *
	 * @param oreName
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxLayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate amount per vein
	 */
	public static void registerOre(String oreName, int maxLayer, int veinSize) {
		registerOre(oreName, maxLayer, 0, veinSize);
	}

	/**
	 * Registers the ore generation style
	 *
	 * @param oreName
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxLayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param minLayer
	 * 		The lowest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate number of blocks per vein
	 */
	public static void registerOre(String oreName, int maxLayer, int minLayer, int veinSize) {
		registerOre(oreName, maxLayer, minLayer, veinSize, 0);
	}

	/**
	 * Registers the ore generation style
	 *
	 * @param oreName
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxLayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param minLayer
	 * 		The lowest layer that ore can generate, must be between 0 and 255
	 * @param veinSize
	 * 		The approximate amount per vein
	 * @param veinChances
	 * 		The number of possible veins per chunk
	 */
	public static void registerOre(String oreName, int maxLayer, int minLayer, int veinSize, int veinChances) {
		registerOre(oreName, maxLayer, minLayer, veinSize, veinSize, veinChances);
	}

	/**
	 * Registers the ore generation style
	 *
	 * @param oreName
	 * 		The name of the ore, without the " Ore"; if it isn't vanilla, add "yourmodid:" to the beginning
	 * @param maxLayer
	 * 		The highest layer that ore can generate, must be between 0 and 255
	 * @param minLayer
	 * 		The lowest layer that ore can generate, must be between 0 and 255
	 * @param minVeinSize
	 * 		The approximate amount per vein
	 * @param veinChances
	 * 		The number of possible veins per chunk
	 */
	public static void registerOre(String oreName, int maxLayer, int minLayer, int minVeinSize, int maxVeinSize, int veinChances) {
		String on = oreName.toLowerCase();
		ClaySpawn.instance.wg.maxLayers.put(on, maxLayer);
		ClaySpawn.instance.wg.minLayers.put(on, minLayer);
		ClaySpawn.instance.wg.minVeinSizes.put(on, minVeinSize);
		ClaySpawn.instance.wg.maxVeinSizes.put(on, maxVeinSize);
		ClaySpawn.instance.wg.genChances.put(on, veinChances);
		ClaySpawn.instance.configGuiSelectionEntries.put(on, oreName);
	}
}
