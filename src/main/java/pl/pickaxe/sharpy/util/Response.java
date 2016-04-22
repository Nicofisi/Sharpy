package pl.pickaxe.sharpy.util;

import java.util.Random;

import pl.pickaxe.sharpy.SharpyCommand;

public class Response {
  public static String getRandom(String[] responses) {
    Random r = new Random();
    return responses[r.nextInt(responses.length)];
  }

  public static String getRandom(SharpyCommand command) {
    Random r = new Random();
    String[] responses = command.getResponses();
    return responses[r.nextInt(responses.length)];
  }
}
