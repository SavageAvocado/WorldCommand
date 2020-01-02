package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class WorldManagementCommand implements SubCommand {
    protected final WorldCommand plugin;

    WorldManagementCommand(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    String buildCommand(String[] args) {
        StringBuilder command = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            command.append(args[i]).append(" ");
        }
        return command.toString().trim();
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String d, @Nonnull String[] args) {
        if (args.length == 1) {
            List<String> suggestions = new ArrayList<>();

            for (World world : this.plugin.getServer().getWorlds()) {
                suggestions.add(world.getName());
            }

            return suggestions;
        }

        String worldIn = args[1].toLowerCase();
        List<String> suggestions = new ArrayList<>();

        for (World world : this.plugin.getServer().getWorlds()) {
            if (world.getName().toLowerCase().startsWith(worldIn)) {
                suggestions.add(world.getName());
            }
        }

        return suggestions;
    }
}
