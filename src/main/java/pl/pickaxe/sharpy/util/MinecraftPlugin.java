package pl.pickaxe.sharpy.util;

import pl.pickaxe.core.PickaxePlugin;
import pl.pickaxe.sharpy.Sharpy;

public class MinecraftPlugin extends PickaxePlugin {

	@Override
	public void onEnableInner() {
		saveDefaultConfig();
		String[] args = getConfig().getString("args").split(" ");
		Sharpy.onMinecraft = true;
		Sharpy.main(args);
	}
}
