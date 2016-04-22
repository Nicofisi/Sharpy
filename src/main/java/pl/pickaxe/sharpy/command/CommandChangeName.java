package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.CommandRegistrator;
import pl.pickaxe.sharpy.SharpyCommand;
import pl.pickaxe.sharpy.util.Response;

public class CommandChangeName implements SharpyCommand {

  private static ArrayList<Pattern> matches = new ArrayList<Pattern>();

  @Override
  public ArrayList<Pattern> getMatches() {
    return matches;
  }
  
  @Override
  public boolean register(DiscordAPI api) {
    String name = api.getYourself().getName();
    matches.add(Pattern.compile(name + " change your name to .*", Pattern.CASE_INSENSITIVE));
    matches.add(Pattern.compile(name + " set your name to .*", Pattern.CASE_INSENSITIVE));
    return true;
  }

  @Override
  public String[] getResponses() {
    return new String[] {"OMG %0 I am %1 now11!1!", "%0 you won't guess who I am.. %1 welcome!"};
  }

  @Override
  public String toString() {
    return "hi";
  }
  
  @Override
  public boolean execute(DiscordAPI api, Message message) {
    String text = message.getContent();
    String name = api.getYourself().getName();
    text = text.replace(name + " change your name to ", "");
    text = text.replace(name + " set your name to ", "");
    api.updateUsername(text);
    name = api.getYourself().getName();
    CommandRegistrator.registerAll(api);
    String r = Response.getRandom(getResponses());
    r = r.replace("%0", message.getAuthor().getName());
    r = r.replace("%1", name);
    message.getChannelReceiver().sendMessage(r);
    return true;
  }

}
