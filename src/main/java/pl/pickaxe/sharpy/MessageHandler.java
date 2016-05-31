package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import org.apache.commons.lang3.StringUtils;
import pl.pickaxe.sharpy.actions.ActionRunner;
import pl.pickaxe.sharpy.command.CommandRunner;

import java.util.ArrayList;

public class MessageHandler implements MessageCreateListener {

	static ArrayList<Message> pendingMessagesLog = new ArrayList<Message>();

	public static void startLogSendingThread() {
		Thread log = new Thread() {

			@Override
			public void run() {
				while (true) {
					if (!pendingMessagesLog.isEmpty()) {
						String logMessage = "";
						for (Message message : pendingMessagesLog) {
							logMessage = logMessage + message.getAuthor().getName() + "("
									+ message.getChannelReceiver().getServer().getName() + ")["
									+ message.getChannelReceiver().getName() + "] " + message.getContent() + "\n";
						}
						logMessage = logMessage.substring(0, logMessage.length());
						Sharpy.messagesLogChannel.sendMessage(logMessage);
					}
					pendingMessagesLog.clear();
					try {
						Thread.sleep(5 * 1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		log.start();

	}

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (Sharpy.logChannels.contains(message.getChannelReceiver())) {
			if (!message.getAuthor().isYourself()) {
				message.delete();
				message.getAuthor().sendMessage("The log channels are only for me <3");
				return;
			}
		}

		if (message.getAuthor().isYourself()) {
			return;
		}

		if (!Sharpy.logChannels.contains(message.getChannelReceiver())) {
			pendingMessagesLog.add(message);
		}
		if (!StringUtils.containsIgnoreCase(message.getContent(), api.getYourself().getName())) {
			return;
		}

		System.out.println("tesd");
		if (CommandRunner.check(api, message)) {
			return;
		}
		ActionRunner.check(api, message);

	}

}
