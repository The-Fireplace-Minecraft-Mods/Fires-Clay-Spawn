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
	public final Map genlayermax = Maps.newHashMap();
	public final Map genlayermin = Maps.newHashMap();
	public final Map genrate = Maps.newHashMap();
	public final Map gencount = Maps.newHashMap();

	private int maxLayer = 65;
	private int minLayer = 0;
	private int rate = 8;
	private int veinCount = 0;

	private int hardMaxLayer = 65;
	private int hardMinLayer = 0;
	private int hardRate = 8;
	private int hardVeinCount = 0;

	private int glazedMaxLayer = 32;
	private int glazedMinLayer = 0;
	private int glazedRate = 8;
	private int glazedVeinCount = 0;

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
			for (int i = 0; i < getVeinCount(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(maxLayer - minLayer) + minLayer;
				(new WorldGenMinable(Blocks.CLAY.getDefaultState(), rate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		//Terracotta
		if (ConfigValues.HARDGENERATE && hardMaxLayer - hardMinLayer >= 0)
			for (int i = 0; i < getHardVeinCount(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(hardMaxLayer - hardMinLayer) + hardMinLayer;
				if (ConfigValues.COLORFULCLAY)
					hardState = Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(random.nextInt(15));
				(new WorldGenMinable(hardState, hardRate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		//Glazed Terracotta
		if (ConfigValues.GLAZEDGENERATE && glazedMaxLayer - glazedMinLayer >= 0)
			for (int i = 0; i < getGlazedVeinCount(); i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(glazedMaxLayer - glazedMinLayer) + glazedMinLayer;
				IBlockState glazedState = glazedStates[random.nextInt(glazedStates.length)];
				(new WorldGenGlazedTerracotta(glazedState, glazedRate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
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

	public void setRate(int weight) {
		rate = weight;
	}

	public int getRate() {
		return rate;
	}

	public void setVeinCount(int veinCount) {
		this.veinCount = veinCount;
	}

	public int getVeinCount() {
		return veinCount == 0 ? maxLayer - minLayer : veinCount;
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

	public void setHardRate(int weight) {
		hardRate = weight;
	}

	public int getHardRate() {
		return hardRate;
	}

	public void setHardVeinCount(int veinCount) {
		this.hardVeinCount = veinCount;
	}

	public int getHardVeinCount() {
		return hardVeinCount == 0 ? hardMaxLayer - hardMinLayer : hardVeinCount;
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

	public void setGlazedRate(int weight) {
		glazedRate = weight;
	}

	public int getGlazedRate() {
		return glazedRate;
	}

	public void setGlazedVeinCount(int veinCount) {
		this.glazedVeinCount = veinCount;
	}

	public int getGlazedVeinCount() {
		return glazedVeinCount == 0 ? glazedMaxLayer - glazedMinLayer : glazedVeinCount;
	}
}
