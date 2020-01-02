package net.savagedev.worldcommand.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MessageUtils {
    private MessageUtils() {
        throw new UnsupportedOperationException();
    }

    public static void message(CommandSender user, String message) {
        if (message != null && !message.equalsIgnoreCase("none")) {
            user.sendMessage(color(message));
        }
    }

    public static void message(CommandSender user, List<String> messages) {
        if (!messages.isEmpty()) {
            for (String message : messages) {
                MessageUtils.message(user, message);
            }
        }
    }

    private static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
