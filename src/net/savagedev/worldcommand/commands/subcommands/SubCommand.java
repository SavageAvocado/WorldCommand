package net.savagedev.worldcommand.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.List;

public interface SubCommand extends TabCompleter {
    void execute(CommandSender user, String[] args);

    String getPermission();

    @Override
    default List<String> onTabComplete(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String d, @Nonnull String[] args) {
        return null;
    }
}
