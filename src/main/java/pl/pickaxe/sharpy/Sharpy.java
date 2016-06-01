package pl.pickaxe.sharpy;

import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Sharpy {
	public static ArrayList<String> admins = new ArrayList<>();
	public static ArrayList<String> trusted = new ArrayList<>();

	public static Channel logChannel;
	public static Channel adminLogChannel;
	public static Channel messagesLogChannel;
	public static ArrayList<Channel> logChannels = new ArrayList<>();
	public static boolean onMinecraft = false;

	private static DiscordAPI api;

	public static long enableTime;

	private static String email, password;

	public static void main(String[] args) {

		enableTime = System.currentTimeMillis();

		email = args[0];
		password = args[1];

		final String NICOFISI = "140237130549952513";
		final String NIIGRU = "152500984491278337";
		final String KASIAQ = "151050119754678272";

		admins.add(NICOFISI);

		trusted.add(NICOFISI);
		trusted.add(NIIGRU);
		trusted.add(KASIAQ);

		api = Javacord.getApi(email, password);

		api.connect(new FutureCallback<DiscordAPI>() {

			@Override
			public void onSuccess(DiscordAPI api) {
				api.setGame("with Nicofisi");

				for (Server s : api.getServers()) {
					if (s.getId().equals("172058448211607568")) {
						for (Channel c : s.getChannels()) {
							if (c.getId().equalsIgnoreCase("172058448211607568")) {
								logChannel = c;
								logChannels.add(c);
							} else if (c.getId().equalsIgnoreCase("172333437280059392")) {
								adminLogChannel = c;
								logChannels.add(c);
							} else if (c.getId().equalsIgnoreCase("173002287772336128")) {
								messagesLogChannel = c;
								logChannels.add(c);
							}
						}
						break;
					}
				}

				api.registerListener(new MessageHandler());

				Registrar.registerAll(api);

				MessageHandler.startLogSendingThread();

				long took = System.currentTimeMillis() - enableTime;
				for (Channel l : logChannels) {
					l.sendMessage(
							"» Sharpy has loaded up and connected to Discord, what took " + took + " ms.");
				}
				// try {
				// api.parseInvite("https://discord.gg/0l3WlzBPKX9Kidpb").get().acceptInvite().get();
				// logChannel.sendMessage("I've joined SkUnity!");
				// } catch (InterruptedException | ExecutionException e) {
				// e.printStackTrace();
				// }

			}

			@Override
			public void onFailure(Throwable e) {
				e.printStackTrace();
			}

		});


	}

	public static void disable(Boolean restart, Channel executedIn) {
		if (executedIn == null) {
			executedIn = logChannel;
		}
		if (restart) {
		} else {
			try {
				for (Channel l : logChannels) {
					l.sendMessage("» Disabling " + api.getYourself().getName() + "..");
				}
				((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().sendClose(1000);
				TimeUnit.SECONDS.sleep(1);
				if (onMinecraft) {
					Bukkit.getServer().shutdown();
				} else {
					System.exit(0);
				}
			} catch (InterruptedException e) {
				executedIn.sendMessage("An error has occured, probably **nothing happened!** Message: *"
						+ e.getMessage() + "*.");
			}
		}
	}
}
