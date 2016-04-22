package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.CommandRegistrator;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyCommand;

public class PredefinedCommands {
  public static void check(DiscordAPI api, Message message) {

    // To make things easier
    String text = message.getContent();

    ArrayList<String> logResult = new ArrayList<String>();

    boolean successful = true;

    l: for (SharpyCommand cmd : CommandRegistrator.getPredefinedActions()) {
      for (Pattern pat : cmd.getMatches()) {
        if (pat.matcher(text).matches()) {
          logResult.add("» Matched command: " + cmd.toString());
          logResult.add("» Matched pattern: " + pat.toString());
          if (cmd.execute(api, message)) {
            logResult.add("» Command successfully executed");
            successful = true;
            break l;
          } else {
            logResult.add("» Command returned false, checking patterns for other commands..");
            successful = false;
          }
        }
      }
    }

    if (!logResult.isEmpty()) {
      if (!successful) {
        logResult.add("» None of the other patterns matched any command");
      }
      String logMessage = "» " + message.getAuthor().getName() + "("
          + message.getChannelReceiver().getServer().getName() + ")["
          + message.getChannelReceiver().getName() + "] " + message.getContent();
      for (String l : logResult) {
        logMessage = logMessage + "\n" + l;
      }
      Sharpy.logChannel.sendMessage(logMessage);
    }
  }
}
