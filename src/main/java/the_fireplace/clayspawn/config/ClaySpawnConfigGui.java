package the_fireplace.clayspawn.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import the_fireplace.clayspawn.ClaySpawn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author The_Fireplace
 */
public class ClaySpawnConfigGui extends GuiConfig{
	public ClaySpawnConfigGui(GuiScreen parentScreen) {
		super(parentScreen,
				getConfigElements(), ClaySpawn.MODID, true,
				false, GuiConfig.getAbridgedConfigPath(ClaySpawn.file.toString()));
	}
	public static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<>();
		list.add(new DummyConfigElement.DummyCategoryElement("clayCfg", "clayCfg", ClayEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("hardClayCfg", "hardClayCfg", HardClayEntry.class));
		return list;
	}
	public static class ClayEntry extends GuiConfigEntries.CategoryEntry {

		public ClayEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}
		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(owningScreen,
					new ConfigElement(ClaySpawn.file.getCategory("clay")).getChildElements(), ClaySpawn.MODID, true,
					false, GuiConfig.getAbridgedConfigPath(ClaySpawn.file.toString()));
		}
	}
	public static class HardClayEntry extends GuiConfigEntries.CategoryEntry {

		public HardClayEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}
		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(owningScreen,
					new ConfigElement(ClaySpawn.file.getCategory("hardenedclay")).getChildElements(), ClaySpawn.MODID, true,
					false, GuiConfig.getAbridgedConfigPath(ClaySpawn.file.toString()));
		}
	}
}
