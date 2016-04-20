package pl.pickaxe.sharpy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;

public class Sharpy {
  public static ArrayList<String> admins = new ArrayList<>();
  public static ArrayList<String> trusted = new ArrayList<>();

  public static Channel logChannel;
  public static Channel adminLogChannel;
  public static ArrayList<Channel> logChannels = new ArrayList<>();

  private static DiscordAPI api;

  public static long enableTime;

  private static String email, password;

  public static void main(String[] args) {

    enableTime = System.currentTimeMillis();

    email = args[0];
    password = args[1];

    admins.add("140237130549952513");

    trusted.add("140237130549952513");
    trusted.add("152500984491278337");
    trusted.add("151050119754678272");

    Registrar.registerAll();

    api = Javacord.getApi(email, password);

    api.connect(new FutureCallback<DiscordAPI>() {

      @Override
      public void onSuccess(DiscordAPI api) {
        api.setGame("Personal Development");

        for (Server s : api.getServers()) {
          if (s.getId().equals("172058448211607568")) {
            for (Channel c : s.getChannels()) {
              if (c.getId().equalsIgnoreCase("172058448211607568")) {
                logChannel = c;
                logChannels.add(c);
              }
              if (c.getId().equalsIgnoreCase("172333437280059392")) {
                adminLogChannel = c;
                logChannels.add(c);
              }
            }
            break;
          }
        }
        
        api.registerListener(new MessageHandler());

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
          l.sendMessage(
              "» Disabling " + api.getYourself().getName() + "..");
        }
        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().sendClose(1000);
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
      } catch (InterruptedException e) {
        executedIn.sendMessage("An error has occured, probably **nothing happened!** Message: *"
            + e.getMessage() + "*.");
      }
    }
  }
}