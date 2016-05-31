package pl.pickaxe.sharpy.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.Registrar;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyAction;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandChecker {
	public static void check(DiscordAPI api, Message message) {

		// To make things easier
		String text = message.getContent();

		ArrayList<String> logResult = new ArrayList<>();

		boolean successful = true;

		l:
		for (SharpyAction cmd : Registrar.getPredefinedActions()) {
			for (Pattern pat : cmd.getMatches()) {
				if (pat.matcher(text).matches()) {
					logResult.add("» Matched commands: " + cmd.toString());
					logResult.add("» Matched pattern: " + pat.toString());
					if (cmd.execute(api, message)) {
						logResult.add("» Command successfully executed");
						break l;
					}
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
		}
	}
}
