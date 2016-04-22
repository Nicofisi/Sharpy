package pl.pickaxe.sharpy;

import java.util.ArrayList;

import de.btobastian.javacord.DiscordAPI;
import pl.pickaxe.sharpy.command.CommandChangeName;
import pl.pickaxe.sharpy.command.CommandHelp;
import pl.pickaxe.sharpy.command.CommandHi;
import pl.pickaxe.sharpy.command.CommandShutdown;

public class CommandRegistrator {
  
  private static ArrayList<SharpyCommand> predefinedActions = new ArrayList<SharpyCommand>();
  
  public static ArrayList<SharpyCommand> getPredefinedActions() {
    return predefinedActions;
  }
  
  public static void registerAll(DiscordAPI api) {

    predefinedActions.clear();
    
    predefinedActions.add(new CommandHi());
    predefinedActions.add(new CommandShutdown());
    predefinedActions.add(new CommandHelp());
    predefinedActions.add(new CommandChangeName());
    
    for (SharpyCommand l : predefinedActions) {
      try {
        l.register(api);
      } catch (RegistrationFailedException e) {
        e.printStackTrace();
      }
    }
  }
}
