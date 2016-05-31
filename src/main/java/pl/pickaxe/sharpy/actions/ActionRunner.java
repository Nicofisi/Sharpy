package pl.pickaxe.sharpy.actions;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.Registrar;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyAction;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActionRunner {
	public static void check(DiscordAPI api, Message message) {

		// To make things easier
		String text = message.getContent();

		ArrayList<String> logResult = new ArrayList<String>();

		boolean successful = true;

		l:
		for (SharpyAction cmd : Registrar.getActions()) {
			for (Pattern pat : cmd.getMatches()) {
				if (pat.matcher(text).matches()) {
					logResult.add("» Matched actions: " + cmd.toString());
					logResult.add("» Matched pattern: " + pat.toString());
					if (cmd.execute(api, message)) {
						logResult.add("» Action code successfully executed");
						successful = true;
						break l;
					} else {
						logResult.add("» Action returned false, checking patterns for other commands & actions..");
						successful = false;
					}
				}
			}
		}

		if (!logResult.isEmpty()) {
			if (!successful) {
				logResult.add("» None of the other patterns matched any actions");
			}
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
