package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.command.CommandSender;

public class HelpCmd implements SubCommand {
    private final WorldCommand plugin;

    public HelpCmd(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getStringList("help"));
    }

    @Override
    public String getPermission() {
        return "wc.help";
    }
}
