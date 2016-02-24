package the_fireplace.clayspawn.event;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.clayspawn.ClaySpawn;

public class ForgeEvents {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(ClaySpawn.MODID))
			ClaySpawn.syncConfig();
	}
}