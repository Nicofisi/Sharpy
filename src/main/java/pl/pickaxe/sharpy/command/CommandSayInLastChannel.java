package pl.pickaxe.sharpy.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import pl.pickaxe.sharpy.SharpyCommand;

public class CommandSayInLastChannel implements SharpyCommand {

  private static ArrayList<Pattern> matches = new ArrayList<Pattern>();

  @Override
  public ArrayList<Pattern> getMatches() {
    return matches;
  }

  @Override
  public boolean register(DiscordAPI api) {
    matches.add(Pattern.compile(".*say .* in my last channel.*", Pattern.CASE_INSENSITIVE));
    return true;
  }

  @Override
  public String[] getResponses() {
    return null;
  }

  @Override
  public String toString() {
    return "say something in my last channel";
  }

  @Override
  public boolean execute(DiscordAPI api, Message message) {
    String text = message.getContent();
    int say = text.indexOf("say") + 4;
    int in = text.lastIndexOf("in") - 1;
    text = text.substring(say, in);
    message.getAuthor().sendMessage(text);

    Message search = null;

    for (Server s : api.getServers()) {
      for (Channel c : s.getChannels()) {
        Future<MessageHistory> f = c.getMessageHistory(10);
        try {
          Collection<Message> h = f.get().getMessages();
          for (Message m : h) {
            if (m.getAuthor() == message.getAuthor()) {
              if (m == message) {
                message.getChannelReceiver().sendMessage(m.getContent());
                continue;
              } else if (search == null) {
                search = m;
              } else if (m.getCreationDate().getTimeInMillis() > search.getCreationDate()
                  .getTimeInMillis()) {
                search = m;
              } ;
            }
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
    
    if (search == null) {
      message.getChannelReceiver().sendMessage("I haven't found any of your messages..");
      return true;
    }
    
    message.getChannelReceiver().sendMessage(search.getContent());
    
    search.getChannelReceiver().sendMessage(text);
    return true;
  }

}
