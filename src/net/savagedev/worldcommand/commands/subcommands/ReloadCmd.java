package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class ReloadCmd implements SubCommand {
    private final WorldCommand plugin;

    public ReloadCmd(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        this.plugin.reloadConfig();
        MessageUtils.message(user, String.format("&6%s &7v&6%s &7by&6 %s &7reloaded.", this.plugin.getDescription().getName(), this.plugin.getDescription().getVersion(), this.plugin.getDescription().getAuthors().get(0)));
    }

    @Override
    public String getPermission() {
        return "wc.reload";
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String d, @Nonnull String[] args) {
        return null;
    }
}
