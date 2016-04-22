package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.RegistrationFailedException;
import pl.pickaxe.sharpy.Sharpy;
import pl.pickaxe.sharpy.SharpyCommand;
import pl.pickaxe.sharpy.util.Response;

public class CommandShutdown implements SharpyCommand {

  private static ArrayList<Pattern> matches = new ArrayList<Pattern>();
  
  @Override
  public ArrayList<Pattern> getMatches() {
    return matches;
  }

  @Override
  public boolean register(DiscordAPI api) throws RegistrationFailedException {
    matches.add(Pattern.compile(".*shutdown.*", Pattern.CASE_INSENSITIVE));
    matches.add(Pattern.compile(".*die.*", Pattern.CASE_INSENSITIVE));
    return true;
  }

  @Override
  public String[] getResponses() {
    return new String[] {"Shutting down!","Oh, I died","Am I.. disabled?!?","[*]","rip."};
  }

  @Override
  public String toString() {
    return "shutdown";
  }
  
  @Override
  public boolean execute(DiscordAPI api, Message message) {
    if (! Sharpy.admins.contains(message.getAuthor().getId())) {
      message.getAuthor().sendMessage("1 KN0w J00 R 5ucH a pR0 hAX0R 8U7 L3av3 m3H al0N3");
      message.delete();
      return true;
    }
    String r = Response.getRandom(this);
    message.getChannelReceiver().sendMessage(r);
    Sharpy.disable(false, message.getChannelReceiver());
    return true;
  }

}
