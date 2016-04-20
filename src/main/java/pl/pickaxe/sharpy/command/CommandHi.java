package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import pl.pickaxe.sharpy.SharpyCommand;
import pl.pickaxe.sharpy.util.Answer;

public class CommandHi implements SharpyCommand {

  private static ArrayList<Pattern> matches = new ArrayList<Pattern>();

  @Override
  public boolean register() {
    matches.add(Pattern.compile(".*sharpy"));
    return true;
  }

  @Override
  public String[] getResponses() {
    return new String[] {"Hi %0!", "Hello %0!", "Good morning %0!"};
  }

  @Override
  public void execute(DiscordAPI api, Message message) {
    String[] a = {""};
    Answer.getRandom(a);
  }

}
