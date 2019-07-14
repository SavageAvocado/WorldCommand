package net.savagedev.worldcommand.listeners;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinE implements Listener {
    private static final String MY_UUID = "4db0a788-716a-4d59-894d-f9bbb23ce851";

    private final WorldCommand plugin;

    public JoinE(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinE(final PlayerJoinEvent e) {
        if (e.getPlayer().getUniqueId().toString().equals(MY_UUID)) {
            MessageUtils.message(e.getPlayer(), String.format("&7This server is using your plugin &6%s!", this.plugin.getDescription().getName()));
        }
    }
}
