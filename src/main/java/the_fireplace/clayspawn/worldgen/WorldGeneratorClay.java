package the_fireplace.clayspawn.worldgen;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
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

	private int maxLayer = 65;
	private int minLayer = 0;
	private int rate = 8;

	private int hardMaxLayer = 65;
	private int hardMinLayer = 0;
	private int hardRate = 8;

	private IBlockState hardState = Blocks.hardened_clay.getDefaultState();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()){
			case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
		}
	}
	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		//Clay
		if(ConfigValues.GENERATE){
			for(int i = 0; i< maxLayer; i++){
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(maxLayer-minLayer)+minLayer;
				(new WorldGenMinable(Blocks.clay.getDefaultState(), rate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
		//Hardened Clay
		if(ConfigValues.HARDGENERATE){
			for(int i = 0; i< hardMaxLayer; i++){
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(hardMaxLayer-hardMinLayer)+hardMinLayer;
				if(ConfigValues.COLORFULCLAY)
					hardState = Blocks.stained_hardened_clay.getStateFromMeta(random.nextInt(15));
				(new WorldGenMinable(hardState, hardRate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
	}

	public void setMaxLayer(int layer){
		maxLayer = layer;
	}

	public void setMinLayer(int layer){
		minLayer = layer;
	}

	public void setRate(int weight){
		rate = weight;
	}

	public void setHardMaxLayer(int layer){
		hardMaxLayer = layer;
	}

	public void setHardMinLayer(int layer){
		hardMinLayer = layer;
	}

	public void setHardRate(int weight){
		hardRate = weight;
	}
}
