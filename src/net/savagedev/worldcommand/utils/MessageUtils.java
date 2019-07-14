package net.savagedev.worldcommand.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtils {
    private MessageUtils() { /* Just to prevent instantiation of this class. */ }

    public static void message(CommandSender user, String message) {
        if (message != null && !message.equalsIgnoreCase("none")) {
            user.sendMessage(color(message));
        }
    }

    private static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
