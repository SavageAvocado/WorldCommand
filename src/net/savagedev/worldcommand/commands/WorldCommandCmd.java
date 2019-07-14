package net.savagedev.worldcommand.commands;

import net.savagedev.worldcommand.WorldCommand;
import net.savagedev.worldcommand.commands.subcommands.*;
import net.savagedev.worldcommand.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldCommandCmd implements CommandExecutor, TabCompleter {
    private final Map<String, SubCommand> subCommands;
    private final WorldCommand plugin;

    public WorldCommandCmd(final WorldCommand plugin) {
        this.subCommands = new HashMap<>();
        this.plugin = plugin;
        this.init();
    }

    private void init() {
        this.subCommands.put("add", new AddCmd(this.plugin));
        this.subCommands.put("help", new HelpCmd(this.plugin));
        this.subCommands.put("list", new ListCmd(this.plugin));
        this.subCommands.put("reload", new ReloadCmd(this.plugin));
        this.subCommands.put("remove", new RemoveCmd(this.plugin));
    }

    public boolean onCommand(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String s, @Nonnull String[] args) {
        if (args.length == 0) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", "worldcommand help"));
            return true;
        }

        String commandName = args[0].toLowerCase();

        if (!this.subCommands.containsKey(commandName)) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.invalid-arguments").replace("%command%", "worldcommand " + this.getSuggestion(commandName)));
            return true;
        }

        SubCommand command = this.subCommands.get(commandName);

        if (!user.hasPermission(command.getPermission())) {
            MessageUtils.message(user, this.plugin.getConfig(WorldCommand.ConfigType.LANG).getString("error.no-permission"));
            return true;
        }

        command.execute(user, args);
        return true;
    }

    private String getSuggestion(String argument) {
        for (String command : this.subCommands.keySet()) {
            if (command.startsWith(argument)) {
                return command;
            }
        }

        return "help";
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender user, @Nonnull Command cmd, @Nonnull String d, @Nonnull String[] args) {
        if (args.length == 0) {
            return new ArrayList<>(this.subCommands.keySet());
        }

        String commandName = args[0].toLowerCase();

        if (!this.subCommands.containsKey(commandName)) {
            List<String> suggestions = new ArrayList<>();

            for (String command : this.subCommands.keySet()) {
                if (command.startsWith(commandName)) {
                    suggestions.add(command);
                }
            }

            return suggestions;
        }

        return this.subCommands.get(commandName).onTabComplete(user, cmd, d, args);
    }
}
