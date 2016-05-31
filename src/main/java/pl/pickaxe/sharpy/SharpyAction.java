package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class SharpyAction {

	protected final ArrayList<Pattern> matches = new ArrayList<>();

	public final ArrayList<Pattern> getMatches() {
		return matches;
	}

	public abstract boolean register(DiscordAPI api) throws RegistrationFailedException;

	public abstract String[] getResponses();

	public final String toString() {
		return getClass().getSimpleName().substring(6);
	}

	public abstract boolean execute(DiscordAPI api, Message message);
}
