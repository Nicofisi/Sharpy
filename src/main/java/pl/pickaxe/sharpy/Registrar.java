package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import pl.pickaxe.sharpy.actions.*;

import java.util.ArrayList;

public class Registrar {

	private static ArrayList<SharpyAction> predefinedActions = new ArrayList<>();

	public static ArrayList<SharpyAction> getPredefinedActions() {
		return predefinedActions;
	}

	public static void registerAll(DiscordAPI api) {

		predefinedActions.clear();

		action(new ActionRemoveBotsMessages());
		action(new ActionHi());
		action(new ActionShutdown());
		action(new ActionHelp());
		action(new ActionChangeName());
		action(new ActionSayInLastChannel());

		for (SharpyAction l : predefinedActions) {
			try {
				l.register(api);
			} catch (RegistrationFailedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void action(SharpyAction cmd) {
		predefinedActions.add(cmd);
	}
}
