package pl.pickaxe.sharpy;

import java.util.ArrayList;

import pl.pickaxe.sharpy.command.CommandHi;

public class Registrar {
  public static void registerAll() {
    ArrayList<SharpyCommand> actions = new ArrayList<SharpyCommand>();

    actions.add(new CommandHi());

    for (SharpyCommand l : actions) {
      try {
        l.register();
      } catch (RegistrationFailedException e) {
        e.printStackTrace();
      }
    }
  }
}
