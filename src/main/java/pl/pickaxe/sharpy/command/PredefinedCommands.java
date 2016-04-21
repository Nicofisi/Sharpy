package pl.pickaxe.sharpy.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.Sharpy;

public class PredefinedCommands {
  public static void check(DiscordAPI api, Message message) {
    
    // To make things easier
    String msg = message.getContent();
    
    // Hi Sharpy
    if (msg.contains("hi") || msg.contains("hello")) {
      message.getChannelReceiver().sendMessage("Hi " + message.getAuthor().getName() + "!");
      
    } else if (msg.contains("who is the best")) {
      message.getChannelReceiver().sendMessage("" + message.getAuthor().getName() + " you are!");
      
      // Polish Hi Sharpy
    } else if (msg.contains("cześć") || msg.contains("witaj") || msg.contains("siemka")
        || msg.contains("elo") || msg.contains("eldo")) {
      message.getChannelReceiver().sendMessage("Witaj " + message.getAuthor().getName() + "!");
      
      // Kill sharpy
    } else if (msg.contains("shutdown") || msg.contains("die") || msg.contains("stop killing us")) {
      if (!Sharpy.admins.contains(message.getAuthor().getId())) {
        return;
      }
      message.getChannelReceiver().sendMessage("Shutting down!");
      Sharpy.disable(false, message.getChannelReceiver());
    }
  }
}
