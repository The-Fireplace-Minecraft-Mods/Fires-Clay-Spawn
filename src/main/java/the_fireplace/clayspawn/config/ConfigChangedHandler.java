package the_fireplace.clayspawn.config;

import the_fireplace.clayspawn.ClaySpawnBase;
import the_fireplace.fireplacecore.FireCoreBaseFile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

public class ConfigChangedHandler {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
	     if(eventArgs.modID.equals("clayspawn"))
	         ClaySpawnBase.syncConfig();
	}
	@SubscribeEvent
	public void onPlayerJoinClient(final ClientConnectedToServerEvent event) {
		(new Thread() {
			public void run() {
				while (FMLClientHandler.instance().getClientPlayerEntity() == null)
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}

				ClaySpawnBase.onPlayerJoinClient(FMLClientHandler.instance()
						.getClientPlayerEntity(), event);
			}
		}).start();

	}
}
