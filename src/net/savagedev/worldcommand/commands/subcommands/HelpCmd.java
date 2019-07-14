package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class HelpCmd implements SubCommand {
    private final WorldCommand plugin;

    public HelpCmd(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        for (String line : this.plugin.getConfig(WorldCommand.ConfigType.LANG).getStringList("help")) {
            MessageUtils.message(user, line);
        }
    }

    @Override
    public String getPermission() {
        return "wc.help";
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String d, @Nonnull String[] args) {
        return null;
    }
}
