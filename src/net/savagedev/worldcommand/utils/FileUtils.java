package net.savagedev.worldcommand.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileUtils {
    private FileUtils() { /* Just to prevent instantiation of this class. */ }

    public static FileConfiguration load(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
