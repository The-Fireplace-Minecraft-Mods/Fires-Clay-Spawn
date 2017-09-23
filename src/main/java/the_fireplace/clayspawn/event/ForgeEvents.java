package the_fireplace.clayspawn.event;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.clayspawn.ClaySpawn;

@Mod.EventBusSubscriber(modid = ClaySpawn.MODID)
public class ForgeEvents {
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ClaySpawn.MODID))
			ClaySpawn.syncConfig();
	}
}