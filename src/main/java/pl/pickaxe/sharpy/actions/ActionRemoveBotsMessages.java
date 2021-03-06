package pl.pickaxe.sharpy.actions;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import pl.pickaxe.sharpy.SharpyAction;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public class ActionRemoveBotsMessages extends SharpyAction {

	@Override
	public boolean register(DiscordAPI api) {
		matches.add(Pattern.compile(".*remove your messages in this channel.*", Pattern.CASE_INSENSITIVE));
		return true;
	}

	@Override
	public String[] getResponses() {
		return null;
	}

	@Override
	public boolean execute(DiscordAPI api, Message message) {
		Channel c = message.getChannelReceiver();
		Future<MessageHistory> f = c.getMessageHistory(200);
		try {
			for (Message m : f.get().getMessages()) {
				if (m.getAuthor() == api.getYourself()) {
					m.delete();
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return true;
	}
}
