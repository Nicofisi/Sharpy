package pl.pickaxe.sharpy.sharpy.command;

import java.util.concurrent.TimeUnit;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.sharpy.Sharpy;

public class PredefinedCommands {
  public void check(DiscordAPI api, Message message) {
    String msg = message.getContent();
    if (msg.contains("hi") || msg.contains("hello")) {
      message.getChannelReceiver().sendMessage("Hi " + message.getAuthor().getName() + "!");
    } else if (msg.contains("cześć") || msg.contains("witaj") || msg.contains("siemka")
        || msg.contains("elo") || msg.contains("eldo")) {
      message.getChannelReceiver().sendMessage("Witaj " + message.getAuthor().getName() + "!");
    } else if (msg.contains("shutdown")) {
      if (!Sharpy.admins.contains(message.getAuthor().getId())) {
        return;
      }
      try {
        message.getChannelReceiver().sendMessage("Shutting down!");
        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().sendClose(1000);
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
      } catch (InterruptedException e) {

      }
    }
  }
}
