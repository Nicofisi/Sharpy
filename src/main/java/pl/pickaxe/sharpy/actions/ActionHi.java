package pl.pickaxe.sharpy.actions;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.SharpyAction;
import pl.pickaxe.sharpy.util.Response;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActionHi extends SharpyAction {

	@Override
	public boolean register(DiscordAPI api) {
		matches.add(Pattern.compile(".*hi .*", Pattern.CASE_INSENSITIVE));
		matches.add(Pattern.compile(".*hello .*", Pattern.CASE_INSENSITIVE));
		matches.add(Pattern.compile(".*welcome .*", Pattern.CASE_INSENSITIVE));
		matches.add(Pattern.compile(".*hey .*", Pattern.CASE_INSENSITIVE));
		return true;
	}

	@Override
	public String[] getResponses() {
		return new String[]{"Hi %0!", "Hello %0!", "Good morning %0!", "Servus %0!"};
	}

	@Override
	public boolean execute(DiscordAPI api, Message message) {
		String r = Response.getRandom(getResponses());
		r = r.replace("%0", message.getAuthor().getName());
		message.getChannelReceiver().sendMessage(r);
		return true;
	}
}
