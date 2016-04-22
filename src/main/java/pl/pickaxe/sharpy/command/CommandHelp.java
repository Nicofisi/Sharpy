package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.SharpyCommand;
import pl.pickaxe.sharpy.util.Response;

public class CommandHelp implements SharpyCommand {

  private static ArrayList<Pattern> matches = new ArrayList<Pattern>();

  @Override
  public ArrayList<Pattern> getMatches() {
    return matches;
  }

  @Override
  public boolean register(DiscordAPI api) {
    matches.add(Pattern.compile(".*help.*", Pattern.CASE_INSENSITIVE));
    return true;
  }

  @Override
  public String[] getResponses() {
    return null;
  }

  @Override
  public String toString() {
    return "hi";
  }

  @Override
  public boolean execute(DiscordAPI api, Message message) {
    message.getAuthor()
        .sendMessage("Hi, my name is" + api.getYourself().getName()
            + ":)\nI can't help you currently, but if you'd like to know something more about me, simply type *"
            + api.getYourself().getName() + " who are you?*");
    return true;
  }

}
