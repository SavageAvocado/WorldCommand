package net.savagedev.worldcommand.listeners;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinE implements Listener {
    private static final UUID AVOCADOS_UUID = UUID.fromString("4db0a788-716a-4d59-894d-f9bbb23ce851");

    private final WorldCommand plugin;

    public JoinE(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinE(final PlayerJoinEvent e) {
        if (e.getPlayer().getUniqueId().equals(AVOCADOS_UUID)) {
            MessageUtils.message(e.getPlayer(), "&7This server is running your plugin: &6" + this.plugin.getDescription().getName() + " &7v&6" + this.plugin.getDescription().getVersion() + "&7.");
        }
    }
}
