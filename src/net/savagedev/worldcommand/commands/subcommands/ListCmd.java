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
public class ListCmd implements SubCommand {
    private final WorldCommand plugin;

    public ListCmd(final WorldCommand plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        if (args.length == 1) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", "worldcommand list <world>"));
            return;
        }

        String world = args[1];

        if (this.plugin.getServer().getWorld(world) == null) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-world").replace("%world%", world));
            return;
        }

        List<String> commands = this.plugin.getConfig(WorldCommand.ConfigType.SETTINGS).getStringList(world);

        if (commands.isEmpty()) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("success.no-commands").replace("%world%", world));
            return;
        }

        MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("list.header").replace("%world%", world));

        for (String command : commands) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("list.format").replace("%command%", command));
        }
    }

    @Override
    public String getPermission() {
        return "wc.list";
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
