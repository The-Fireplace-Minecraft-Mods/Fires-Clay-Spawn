package the_fireplace.clayspawn.worldgen;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.clayspawn.config.ConfigValues;

import java.util.Map;
import java.util.Random;

/**
 * @author The_Fireplace
 */
public class WorldGeneratorClay implements IWorldGenerator {
	public final Map<String, Integer> maxLayers = Maps.newHashMap();
	public final Map<String, Integer> minLayers = Maps.newHashMap();
	public final Map<String, Integer> minVeinSizes = Maps.newHashMap();
	public final Map<String, Integer> maxVeinSizes = Maps.newHashMap();
	public final Map<String, Integer> genChances = Maps.newHashMap();

	private int maxLayer = 65;
	private int minLayer = 0;
	private int minVeinSize = 8;
	private int maxVeinSize = 8;
	private int veinChances = 0;

	private int hardMaxLayer = 65;
	private int hardMinLayer = 0;
	private int hardMinVeinSize = 8;
	private int hardMaxVeinSize = 8;
	private int hardVeinChances = 0;

	private int glazedMaxLayer = 32;
	private int glazedMinLayer = 0;
	private int glazedMinVeinSize = 8;
	private int glazedMaxVeinSize = 8;
	private int glazedVeinChances = 0;

	public static IBlockState hardState = Blocks.HARDENED_CLAY.getDefaultState();
	public static IBlockState[] glazedStates = {Blocks.WHITE_GLAZED_TERRACOTTA.getDefaultState(), Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState(), Blocks.BLUE_GLAZED_TERRACOTTA.getDefaultState(), Blocks.GRAY_GLAZED_TERRACOTTA.getDefaultState(), Blocks.BROWN_GLAZED_TERRACOTTA.getDefaultState(), Blocks.CYAN_GLAZED_TERRACOTTA.getDefaultState(), Blocks.GREEN_GLAZED_TERRACOTTA.getDefaultState(), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.getDefaultState(), Blocks.LIME_GLAZED_TERRACOTTA.getDefaultState(), Blocks.MAGENTA_GLAZED_TERRACOTTA.getDefaultState(), Blocks.ORANGE_GLAZED_TERRACOTTA.getDefaultState(), Blocks.PINK_GLAZED_TERRACOTTA.getDefaultState(), Blocks.PURPLE_GLAZED_TERRACOTTA.getDefaultState(), Blocks.RED_GLAZED_TERRACOTTA.getDefaultState(), Blocks.SILVER_GLAZED_TERRACOTTA.getDefaultState(), Blocks.YELLOW_GLAZED_TERRACOTTA.getDefaultState()};

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0)
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		//Clay
		if (ConfigValues.GENERATE && maxLayer - minLayer >= 0)
			for (int i = 0; i < getVeinChances(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(maxLayer - minLayer) + minLayer;
				(new WorldGenMinable(Blocks.CLAY.getDefaultState(), random.nextInt(maxVeinSize - minVeinSize) + minVeinSize)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		//Terracotta
		if (ConfigValues.HARDGENERATE && hardMaxLayer - hardMinLayer >= 0)
			for (int i = 0; i < getHardVeinChances(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(hardMaxLayer - hardMinLayer) + hardMinLayer;
				if (ConfigValues.COLORFULCLAY)
					hardState = Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(random.nextInt(15));
				(new WorldGenMinable(hardState, random.nextInt(hardMaxVeinSize - hardMinVeinSize) + hardMinVeinSize)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		//Glazed Terracotta
		if (ConfigValues.GLAZEDGENERATE && glazedMaxLayer - glazedMinLayer >= 0)
			for (int i = 0; i < getGlazedVeinChances(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(glazedMaxLayer - glazedMinLayer) + glazedMinLayer;
				IBlockState glazedState = glazedStates[random.nextInt(glazedStates.length)];
				(new WorldGenGlazedTerracotta(glazedState, random.nextInt(glazedMaxVeinSize - glazedMinVeinSize) + glazedMinVeinSize)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
	}

	public void setMaxLayer(int layer) {
		maxLayer = layer;
	}

	public int getMaxLayer() {
		return maxLayer;
	}

	public void setMinLayer(int layer) {
		minLayer = layer;
	}

	public int getMinLayer() {
		return minLayer;
	}

	public void setMinVeinSize(int weight) {
		minVeinSize = weight;
	}

	public int getMinVeinSize() {
		return minVeinSize;
	}

	public void setMaxVeinSize(int weight) {
		maxVeinSize = weight;
	}

	public int getMaxVeinSize() {
		return maxVeinSize;
	}

	public void setVeinChances(int veinChances) {
		this.veinChances = veinChances;
	}

	public int getVeinChances() {
		return veinChances == 0 ? maxLayer - minLayer : veinChances;
	}

	public void setHardMaxLayer(int layer) {
		hardMaxLayer = layer;
	}

	public int getHardMaxLayer() {
		return hardMaxLayer;
	}

	public void setHardMinLayer(int layer) {
		hardMinLayer = layer;
	}

	public int getHardMinLayer() {
		return hardMinLayer;
	}

	public void setHardMinVeinSize(int weight) {
		hardMinVeinSize = weight;
	}

	public int getHardMinVeinSize() {
		return hardMinVeinSize;
	}

	public void setHardMaxVeinSize(int weight) {
		hardMaxVeinSize = weight;
	}

	public int getHardMaxVeinSize() {
		return hardMaxVeinSize;
	}

	public void setHardVeinChances(int veinCount) {
		this.hardVeinChances = veinCount;
	}

	public int getHardVeinChances() {
		return hardVeinChances == 0 ? hardMaxLayer - hardMinLayer : hardVeinChances;
	}

	public void setGlazedMaxLayer(int layer) {
		glazedMaxLayer = layer;
	}

	public int getGlazedMaxLayer() {
		return glazedMaxLayer;
	}

	public void setGlazedMinLayer(int layer) {
		glazedMinLayer = layer;
	}

	public int getGlazedMinLayer() {
		return glazedMinLayer;
	}

	public void setGlazedMinVeinSize(int weight) {
		glazedMinVeinSize = weight;
	}

	public void setGlazedMaxVeinSize(int weight) {
		glazedMaxVeinSize = weight;
	}

	public int getGlazedMinVeinSize() {
		return glazedMinVeinSize;
	}

	public int getGlazedMaxVeinSize() {
		return glazedMaxVeinSize;
	}

	public void setGlazedVeinChances(int veinCount) {
		this.glazedVeinChances = veinCount;
	}

	public int getGlazedVeinChances() {
		return glazedVeinChances == 0 ? glazedMaxLayer - glazedMinLayer : glazedVeinChances;
	}
}
