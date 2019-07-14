package net.savagedev.worldcommand.commands.subcommands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class RemoveCmd implements SubCommand {
    private final WorldCommand plugin;

    public RemoveCmd(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        if (args.length == 1) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", "worldcommand remove <world> <command>"));
            return;
        }

        String world = args[1];

        if (this.plugin.getServer().getWorld(world) == null) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-world").replace("%world%", world));
            return;
        }

        if (args.length == 2) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", String.format("worldcommand remove %s <command>", world)));
            return;
        }

        StringBuilder command = new StringBuilder();

        for (int i = 2; i < args.length; i++) {
            command.append(args[i]).append(" ");
        }

        List<String> commands = this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).getStringList(world);
        commands.remove(command.toString().trim());

        this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).set(world, commands);

        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            this.plugin.saveConfig();
            this.plugin.reloadConfig();
        });

        MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("success.removed-command").replace("%command%", command.toString().trim()).replace("%world%", world));
    }

    @Override
    public String getPermission() {
        return "wc.remove";
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

        if (args.length == 2) {
            List<String> suggestions = new ArrayList<>();

            for (World world : this.plugin.getServer().getWorlds()) {
                if (world.getName().toLowerCase().startsWith(worldIn)) {
                    suggestions.add(world.getName());
                }
            }

            return suggestions;
        }

        return this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).getStringList(worldIn);
    }
}
