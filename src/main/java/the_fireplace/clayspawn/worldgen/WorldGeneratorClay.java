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
	switch(world.provider.getDimensionId()){
	case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
	}
	}
	private int layer = 65;//the highest layer it can generate; Default: Iron
	private int rate = 8;//generation rate; Default: Iron
	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		if(ConfigValues.DENSITYOVERRIDE > 0){
			rate=ConfigValues.DENSITYOVERRIDE;
		}
		if(ConfigValues.HEIGHTOVERRIDE > 0){
			layer=ConfigValues.HEIGHTOVERRIDE;
		}
		if(FCCV.CHEAT1.toLowerCase() == "clayworld" || FCCV.CHEAT1.toLowerCase() == "clay world" || FCCV.CHEAT2.toLowerCase() == "clayworld" || FCCV.CHEAT2.toLowerCase() == "clay world" || FCCV.CHEAT3.toLowerCase() == "clayworld" || FCCV.CHEAT3.toLowerCase() == "clay world" || FCCV.CHEAT4.toLowerCase() == "clayworld" || FCCV.CHEAT4.toLowerCase() == "clay world" || FCCV.CHEAT5.toLowerCase() == "clayworld" || FCCV.CHEAT5.toLowerCase() == "clay world"){
			rate = 512;
			layer = 128;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "iron"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 8;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 65;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "coal"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 16;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 128;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "diamond"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 7;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 15;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "gold" || ConfigValues.OREGENRATE.toLowerCase() == "budder"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 8;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 32;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "emerald"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 1;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 32;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "lapis" || ConfigValues.OREGENRATE.toLowerCase() == "lapis lazuli"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 6;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 31;
		}else if(ConfigValues.OREGENRATE.toLowerCase() == "redstone" || ConfigValues.OREGENRATE.toLowerCase() == "red stone"){
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 7;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 16;
		}else{
			if(ConfigValues.DENSITYOVERRIDE <= 0)
			rate = 8;
			if(ConfigValues.HEIGHTOVERRIDE <= 0)
			layer = 65;
		}
	for(int i =0; i<layer; i++){
	int Xcoord = BlockX + random.nextInt(16);
	int Zcoord = BlockZ + random.nextInt(16);
	int Ycoord = random.nextInt(layer);
	(new WorldGenMinable(Blocks.clay.getDefaultState(), rate)).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
	}}

}
