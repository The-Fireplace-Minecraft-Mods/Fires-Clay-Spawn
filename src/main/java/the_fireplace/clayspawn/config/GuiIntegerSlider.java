package the_fireplace.clayspawn.config;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.client.config.IConfigElement;

/**
 * @author The_Fireplace
 */
public class GuiIntegerSlider extends GuiConfigEntries.NumberSliderEntry {

	public GuiIntegerSlider(GuiConfig owningScreen,
	                        GuiConfigEntries owningEntryList, IConfigElement configElement) {
		super(owningScreen, owningEntryList, configElement);
		((GuiSlider) this.btnValue).precision = 0;
		((GuiSlider) this.btnValue).showDecimal = false;
		((GuiSlider) this.btnValue).updateSlider();
	}
}