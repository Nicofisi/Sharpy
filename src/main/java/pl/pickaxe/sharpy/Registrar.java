package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import pl.pickaxe.sharpy.actions.*;
import pl.pickaxe.sharpy.command.CommandTest;

import java.util.ArrayList;

public class Registrar {

	private static ArrayList<SharpyAction> actions = new ArrayList<>();
	private static ArrayList<SharpyCommand> commands = new ArrayList<>();

	public static ArrayList<SharpyAction> getActions() {
		return actions;
	}
	public static ArrayList<SharpyCommand> getCommands() {
		return commands;
	}

	public static void registerAll(DiscordAPI api) {

		actions.clear();

		action(new ActionRemoveBotsMessages());
		action(new ActionHi());
		action(new ActionShutdown());
		action(new ActionHelp());
		action(new ActionChangeName());
		action(new ActionSayInLastChannel());

		command(new CommandTest());

		for (SharpyAction act : actions) {
			try {
				act.register(api);
			} catch (RegistrationFailedException e) {
				e.printStackTrace();
			}
		}

		for (SharpyCommand cmd : commands) {
			try {
				cmd.register(api);
			} catch (RegistrationFailedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void action(SharpyAction act) {
		actions.add(act);
	}

	private static void command(SharpyCommand cmd) {
		commands.add(cmd);
	}
}
