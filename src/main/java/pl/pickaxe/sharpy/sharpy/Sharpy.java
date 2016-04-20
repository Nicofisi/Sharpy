package pl.pickaxe.sharpy.sharpy;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;

public class Sharpy {
  public static ArrayList<String> admins = new ArrayList<>();
  public static ArrayList<String> trusted = new ArrayList<>();

  public static Channel logChannel;
  public static Channel adminLogChannel;

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
              }
              if (c.getId().equalsIgnoreCase("172333437280059392")) {
                adminLogChannel = c;
              }
            }
            break;
          }
        }

        api.registerListener(new MessageHandler());
        
        long took = System.currentTimeMillis() - enableTime;
        logChannel.sendMessage("**Sharpy has been enabled** - Took " + took + " ms.");
        adminLogChannel.sendMessage("**Sharpy has been enabled** - Took " + took + " ms.");

//        try {
//          api.parseInvite("https://discord.gg/0l3WlzBPKX9Kidpb").get().acceptInvite().get();
//          logChannel.sendMessage("I've joined SkUnity!");
//        } catch (InterruptedException | ExecutionException e) {
//          e.printStackTrace();
//        }
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }

    });


  }
}
