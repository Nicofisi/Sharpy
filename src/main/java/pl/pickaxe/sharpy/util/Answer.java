package pl.pickaxe.sharpy.util;

import java.util.Random;

public class Answer {
  public static String getRandom(String[] answers) {
    Random r = new Random();
    return answers[r.nextInt(answers.length)];
  }
}
