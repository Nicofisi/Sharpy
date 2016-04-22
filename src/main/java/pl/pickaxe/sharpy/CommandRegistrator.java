package pl.pickaxe.sharpy;

import java.util.ArrayList;

import de.btobastian.javacord.DiscordAPI;
import pl.pickaxe.sharpy.command.CommandChangeName;
import pl.pickaxe.sharpy.command.CommandHelp;
import pl.pickaxe.sharpy.command.CommandHi;
import pl.pickaxe.sharpy.command.CommandRemoveBotsMessages;
import pl.pickaxe.sharpy.command.CommandSayInLastChannel;
import pl.pickaxe.sharpy.command.CommandShutdown;

public class CommandRegistrator {

  private static ArrayList<SharpyCommand> predefinedActions = new ArrayList<SharpyCommand>();

  public static ArrayList<SharpyCommand> getPredefinedActions() {
    return predefinedActions;
  }

  public static void registerAll(DiscordAPI api) {

    predefinedActions.clear();

    register(new CommandRemoveBotsMessages());
    register(new CommandHi());
    register(new CommandShutdown());
    register(new CommandHelp());
    register(new CommandChangeName());
    register(new CommandSayInLastChannel());

    for (SharpyCommand l : predefinedActions) {
      try {
        l.register(api);
      } catch (RegistrationFailedException e) {
        e.printStackTrace();
      }
    }
  }

  private static void register(SharpyCommand cmd) {
    predefinedActions.add(cmd);
  }
}
