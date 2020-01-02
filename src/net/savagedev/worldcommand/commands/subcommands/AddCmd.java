package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AddCmd extends WorldManagementCommand {
    public AddCmd(WorldCommand plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        if (args.length == 1) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", "worldcommand add <world> <command>"));
            return;
        }

        String world = args[1];

        if (this.plugin.getServer().getWorld(world) == null) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-world").replace("%world%", world));
            return;
        }

        if (args.length == 2) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", String.format("worldcommand add %s <command>", world)));
            return;
        }

        final String command = super.buildCommand(args);

        List<String> commands = this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).getStringList(world);
        commands.add(command);

        this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).set(world, commands);

        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            this.plugin.saveConfig();
            this.plugin.reloadConfig();
        });

        MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("success.added-command").replace("%command%", command).replace("%world%", world));
    }

    @Override
    public String getPermission() {
        return "wc.add";
    }
}
