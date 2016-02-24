package the_fireplace.clayspawn.worldgen;

import com.google.common.collect.Maps;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
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

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimensionId()){
			case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
		}
	}
	private int maxLayer = 65;
	private int minLayer = 0;
	private int rate = 8;
	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		if(ConfigValues.DENSITYOVERRIDE > 0)
			rate=ConfigValues.DENSITYOVERRIDE;
		else
			if(genrate.get(ConfigValues.OREGENRATE) != null)
				rate=(Integer) genrate.get(ConfigValues.OREGENRATE);
		if(ConfigValues.HEIGHTOVERRIDE > 0)
			maxLayer =ConfigValues.HEIGHTOVERRIDE;
		else
			if(genlayermax.get(ConfigValues.OREGENRATE) != null)
				maxLayer =(Integer) genlayermax.get(ConfigValues.OREGENRATE);
		if(ConfigValues.MINHEIGHTOVERRIDE > 0)
			minLayer =ConfigValues.MINHEIGHTOVERRIDE;
		else
			if(genlayermin.get(ConfigValues.OREGENRATE) != null)
				minLayer =(Integer) genlayermin.get(ConfigValues.OREGENRATE);

		for(int i = 0; i< maxLayer; i++){
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(maxLayer-minLayer)+minLayer;
			(new WorldGenMinable(Blocks.clay.getDefaultState(), rate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
		}
	}
}
