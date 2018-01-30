package net.simplyrin.togglesneak;

import java.io.File;
import java.io.IOException;

import club.sk1er.mods.publicmod.Sk1erMod;
import net.md_5.bungee.config.Configuration;
import net.simplyrin.config.Config;

public class TSMod {

	private boolean enabled = false;

	public boolean checkStatus() {
		String result = Sk1erMod.rawWithAgent("https://api.simplyrin.net/Forge-Mods/ToggleSneak/3.2/info.txt");
		boolean enabled = Boolean.valueOf(result).booleanValue();

		this.enabled = enabled;
		return this.enabled;
	}

	public void registerConfig() {
		if(!this.enabled) {
			return;
		}

		File folder = new File("mods/ToggleSneakMod");
		if (!folder.exists()) {
			folder.mkdir();
		}

		File file = new File(folder, "config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Configuration config = Config.getConfig(file);

			config.set("Flying.Flying", "[Flying]");
			config.set("Flying.Boost", "[Flying (%BOOST%x boost)]");
			config.set("Riding", "[Riding]");
			config.set("Descending", "[Descending]");
			config.set("Dismounting", "[Dismounting]");
			config.set("Sneaking.Key_Held", "[Sneaking (Key Held)]");
			config.set("Sneaking.Toggled", "[Sneaking (Toggled)]");
			config.set("Sprinting.Key_Held", "[Sprinting (Key Held)]");
			config.set("Sprinting.Vanilla", "[Sprinting (Vanilla)]");
			config.set("Sprinting.Toggled", "[Sprinting (Toggled)]");

			Config.saveConfig(config, file);
		}
	}

	public boolean isEnabled() {
		return this.enabled;
	}

}
