package pl.pickaxe.sharpy;

import java.util.ArrayList;

//import java.util.ArrayList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public interface SharpyCommand {
  
  public ArrayList<SharpyCommand> actions = new ArrayList<SharpyCommand>();
  
  public boolean register() throws RegistrationFailedException;
  
  public String[] getResponses();
  
  //public void checkMatching(DiscordAPI api, Message message);

  public void execute(DiscordAPI api, Message message);
}
