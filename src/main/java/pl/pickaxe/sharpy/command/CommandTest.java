package pl.pickaxe.sharpy.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.RegistrationFailedException;
import pl.pickaxe.sharpy.SharpyCommand;

public class CommandTest extends SharpyCommand {
	@Override
	public boolean register(DiscordAPI api) throws RegistrationFailedException {
		addAlias("test");
		return true;
	}

	@Override
	public void execute(DiscordAPI api, Message message) {
		message.reply("Test yourself!");
	}
}
