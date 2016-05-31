package pl.pickaxe.sharpy.util;

import pl.pickaxe.sharpy.SharpyAction;

import java.util.Random;

public class Response {
	public static String getRandom(String[] responses) {
		Random r = new Random();
		return responses[r.nextInt(responses.length)];
	}

	public static String getRandom(SharpyAction command) {
		Random r = new Random();
		String[] responses = command.getResponses();
		return responses[r.nextInt(responses.length)];
	}
}
