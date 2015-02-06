package the_fireplace.clayspawn.config;

import the_fireplace.clayspawn.ClaySpawnBase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ClaySpawnConfigGui extends GuiConfig{
	public ClaySpawnConfigGui(GuiScreen parentScreen) {
		super(parentScreen, 
				new ConfigElement(ClaySpawnBase.file.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), "clayspawn", false,
				false, GuiConfig.getAbridgedConfigPath(ClaySpawnBase.file.toString()));
	}
}
