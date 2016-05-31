package pl.pickaxe.sharpy.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.Registrar;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyCommand;

import java.util.ArrayList;

public class CommandRunner {
	public static final String PREFIX = "//";
	public static boolean check(DiscordAPI api, Message message) {

		// To make things easier
		String text = message.getContent();

		ArrayList<String> logResult = new ArrayList<>();


		l:
		for (SharpyCommand cmd : Registrar.getCommands()) {
			for (String alias : cmd.getAliases()) {
				System.out.println(PREFIX + alias);
				if (text.equalsIgnoreCase(PREFIX + alias)) {
					logResult.add("» Message: " + text.toString());
					logResult.add("» Matched commands: " + cmd.toString());
					logResult.add("» Matched pattern: " + alias.toString());
					cmd.execute(api, message);
					logResult.add("» Command successfully executed");
					break l;
				}
			}
		}
		if (!logResult.isEmpty()) {
			String logMessage = "» " + message.getAuthor().getName() + "("
					+ message.getChannelReceiver().getServer().getName() + ")["
					+ message.getChannelReceiver().getName() + "] " + message.getContent();
			for (String l : logResult) {
				logMessage = logMessage + "\n" + l;
			}
			Sharpy.logChannel.sendMessage(logMessage);
			return true;
		}
		return false;
	}
}
