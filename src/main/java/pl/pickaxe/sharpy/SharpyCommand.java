package pl.pickaxe.sharpy;

import java.util.ArrayList;
import java.util.regex.Pattern;

//import java.util.ArrayList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public interface SharpyCommand {
  
  public ArrayList<Pattern> getMatches();
  
  public boolean register(DiscordAPI api) throws RegistrationFailedException;
  
  public String[] getResponses();
  
  public String toString();
  
  //public void checkMatching(DiscordAPI api, Message message);

  public boolean execute(DiscordAPI api, Message message);
}
