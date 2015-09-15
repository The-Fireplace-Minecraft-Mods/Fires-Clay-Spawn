package the_fireplace.clayspawn.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import the_fireplace.clayspawn.ClaySpawn;
/**
 * @author The_Fireplace
 */
public class ClaySpawnConfigGui extends GuiConfig{
	public ClaySpawnConfigGui(GuiScreen parentScreen) {
		super(parentScreen,
				new ConfigElement(ClaySpawn.file.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), ClaySpawn.MODID, true,
				false, GuiConfig.getAbridgedConfigPath(ClaySpawn.file.toString()));
		this.titleLine2="";
	}
}
