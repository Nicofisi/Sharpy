package pl.pickaxe.sharpy.sharpy;

import java.util.concurrent.TimeUnit;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MessageHandler implements MessageCreateListener {
  
  @Override
  public void onMessageCreate(DiscordAPI api, Message message) {
    String msg = message.getContent();
    if (!msg.contains("sharpy") && !msg.contains("Sharpy")) {
      return;
    }
  }

}
