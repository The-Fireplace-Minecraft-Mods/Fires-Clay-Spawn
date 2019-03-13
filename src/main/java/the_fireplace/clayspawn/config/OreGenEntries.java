package the_fireplace.clayspawn.config;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import the_fireplace.clayspawn.ClaySpawn;

public class OreGenEntries extends GuiConfigEntries.SelectValueEntry {

	public OreGenEntries(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
		super(owningScreen, owningEntryList, configElement, ClaySpawn.instance.configGuiSelectionEntries);
	}
}
