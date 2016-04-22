package pl.pickaxe.sharpy;

import org.apache.commons.lang3.StringUtils;

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
    if (! StringUtils.containsIgnoreCase(message.getContent(), api.getYourself().getName())) {
      return;
    }
    PredefinedCommands.check(api, message);

  }

}
