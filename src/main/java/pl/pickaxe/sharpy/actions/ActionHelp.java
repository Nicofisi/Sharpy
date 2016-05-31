package pl.pickaxe.sharpy.actions;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.SharpyAction;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActionHelp extends SharpyAction {

	@Override
	public boolean register(DiscordAPI api) {
		matches.add(Pattern.compile(".*help.*", Pattern.CASE_INSENSITIVE));
		return true;
	}

	@Override
	public String[] getResponses() {
		return null;
	}

	@Override
	public boolean execute(DiscordAPI api, Message message) {
		message.getAuthor()
				.sendMessage("Hi, my name is" + api.getYourself().getName()
						+ ":)\nI can't help you currently, but if you'd like to know something more about me, simply type *"
						+ api.getYourself().getName() + " who are you?*");
		return true;
	}
}
