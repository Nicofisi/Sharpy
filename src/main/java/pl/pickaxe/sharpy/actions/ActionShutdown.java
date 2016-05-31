package pl.pickaxe.sharpy.actions;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.RegistrationFailedException;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyAction;
import pl.pickaxe.sharpy.util.Response;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActionShutdown extends SharpyAction {

	@Override
	public boolean register(DiscordAPI api) throws RegistrationFailedException {
		matches.add(Pattern.compile(".*shutdown.*", Pattern.CASE_INSENSITIVE));
		matches.add(Pattern.compile(".*die.*", Pattern.CASE_INSENSITIVE));
		return true;
	}

	@Override
	public String[] getResponses() {
		return new String[]{"Shutting down!", "Oh, I died", "Am I.. disabled?!?", "[*]", "rip."};
	}

	@Override
	public boolean execute(DiscordAPI api, Message message) {
		if (!Sharpy.admins.contains(message.getAuthor().getId())) {
			message.getAuthor().sendMessage("1 KN0w J00 R 5ucH a pR0 hAX0R 8U7 L3av3 m3H al0N3");
			message.delete();
			return true;
		}
		String r = Response.getRandom(this);
		message.getChannelReceiver().sendMessage(r);
		Sharpy.disable(false, message.getChannelReceiver());
		return true;
	}
}
