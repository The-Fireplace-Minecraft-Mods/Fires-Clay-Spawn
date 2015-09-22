package the_fireplace.clayspawn.event;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.clayspawn.ClaySpawn;

public class ForgeEvents {
	private static final String CLAYSPAWN = ClaySpawn.MODID;
	private static Random rand = new Random();
	@SubscribeEvent
	public void retroGen(ChunkDataEvent.Load event) {
		NBTTagCompound chunkData = event.getData();
		NBTTagCompound rgen = chunkData.getCompoundTag(CLAYSPAWN);
		boolean regen = false;

		long worldSeed = event.world.getSeed();

		rand.setSeed(worldSeed);
		long xSeed = rand.nextLong() >> 2 + 1L;
		long zSeed = rand.nextLong() >> 2 + 1L;
		long chunkSeed = (xSeed * event.getChunk().xPosition + zSeed * event.getChunk().zPosition) ^ worldSeed;
		rand.setSeed(chunkSeed);

		Block clay = Blocks.clay;
		NBTTagCompound b = rgen.getCompoundTag(clay.getUnlocalizedName());

		genChunk(event.getChunk(), rand, clay);
		regen = true;

		event.getChunk().setChunkModified();
	}

	@SubscribeEvent
	public void retroGenSave(ChunkDataEvent.Save event) {
		NBTTagCompound chunkData = event.getData();
		NBTTagCompound rgen = chunkData.getCompoundTag(CLAYSPAWN);

		Block clay = Blocks.clay;
		NBTTagCompound b = rgen.getCompoundTag(clay.getUnlocalizedName());
		rgen.setTag(clay.getUnlocalizedName(), b);

		chunkData.setTag(CLAYSPAWN, rgen);
	}
}
