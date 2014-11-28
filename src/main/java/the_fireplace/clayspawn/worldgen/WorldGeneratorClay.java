package the_fireplace.clayspawn.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.clayspawn.config.ConfigValues;
import the_fireplace.fireplacecore.config.FCCV;

public class WorldGeneratorClay implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
	IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	switch(world.provider.dimensionId){
	case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
	}
	}
	private int layer = 65;//the highest layer it can generate; Default: Iron
	private int rate = 8;//generation rate; Default: Iron
	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		if(FCCV.CHEAT1.toLowerCase() == "clayworld" || FCCV.CHEAT1.toLowerCase() == "clay world" || FCCV.CHEAT2.toLowerCase() == "clayworld" || FCCV.CHEAT2.toLowerCase() == "clay world" || FCCV.CHEAT3.toLowerCase() == "clayworld" || FCCV.CHEAT3.toLowerCase() == "clay world" || FCCV.CHEAT4.toLowerCase() == "clayworld" || FCCV.CHEAT4.toLowerCase() == "clay world" || FCCV.CHEAT5.toLowerCase() == "clayworld" || FCCV.CHEAT5.toLowerCase() == "clay world"){
			rate = 512;
			layer = 128;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "iron"){
			rate = 8;
			layer = 65;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "coal"){
			rate = 16;
			layer = 128;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "diamond"){
			rate = 7;
			layer = 15;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "gold" || ConfigValues.OREGENRATE.toLowerCase() == "budder"){
			rate = 8;
			layer = 32;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "emerald"){
			rate = 1;
			layer = 32;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "lapis" || ConfigValues.OREGENRATE.toLowerCase() == "lapis lazuli"){
			rate = 6;
			layer = 31;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "redstone" || ConfigValues.OREGENRATE.toLowerCase() == "red stone"){
			rate = 7;
			layer = 16;
		}else{
			rate = 8;
			layer = 65;
		}
	for(int i =0; i<layer; i++){
	int Xcoord = BlockX + random.nextInt(16);
	int Zcoord = BlockZ + random.nextInt(16);
	int Ycoord = random.nextInt(16);
	(new WorldGenMinable(Blocks.clay, rate)).generate(world, random, Xcoord, Ycoord, Zcoord);
	}}

}
