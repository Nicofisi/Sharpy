package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

import java.util.ArrayList;

public abstract class SharpyCommand {

	protected final ArrayList<String> aliases = new ArrayList<>();

	protected final void addAlias(String alias) {
		aliases.add(alias);
	}

	public final ArrayList<String> getAliases() {
		return aliases;
	}

	public abstract boolean register(DiscordAPI api) throws RegistrationFailedException;

	public abstract void execute(DiscordAPI api, Message message);
}
