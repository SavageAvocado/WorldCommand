package net.savagedev.worldcommand.listeners;

import net.savagedev.worldcommand.WorldCommand;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.List;

public class WorldChangeE implements Listener {
    private WorldCommand plugin;

    public WorldChangeE(WorldCommand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldChangeE(PlayerChangedWorldEvent e) {
        Player user = e.getPlayer();
        World from = e.getFrom();
        World to = user.getLocation().getWorld();

        List<String> commands = this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).getStringList(to.getName());

        if (!commands.isEmpty()) {
            for (String command : commands) {
                this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), command.replace("{player}", user.getName()).replace("{world_from}", from.getName()).replace("{world_to}", to.getName()));
            }
        }
    }
}
