package net.savagedev.worldcommand.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public interface SubCommand extends TabCompleter {
    void execute(CommandSender user, String[] args);

    String getPermission();
}
