package net.savagedev.worldcommand.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException();
    }

    public static FileConfiguration load(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
