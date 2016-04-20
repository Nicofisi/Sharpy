package pl.pickaxe.sharpy;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import pl.pickaxe.sharpy.command.PredefinedCommands;

public class MessageHandler implements MessageCreateListener {

  @Override
  public void onMessageCreate(DiscordAPI api, Message message) {
    String msg = message.getContent();
    if (!message.getChannelReceiver().equals(Sharpy.adminLogChannel)
        && !message.getChannelReceiver().equals(Sharpy.logChannel)) {
      Sharpy.adminLogChannel.sendMessage(
          message.getAuthor().getName() + "(" + message.getChannelReceiver().getServer().getName()
              + ")[" + message.getChannelReceiver().getName() + "] " + msg);
    }
    if (!msg.contains("sharpy") && !msg.contains("Sharpy")) {
      return;
    }
    PredefinedCommands.check(api, message);

  }

}
