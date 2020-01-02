package net.savagedev.worldcommand;

import net.savagedev.worldcommand.commands.WorldCommandCmd;
import net.savagedev.worldcommand.listeners.JoinE;
import net.savagedev.worldcommand.listeners.WorldChangeE;
import net.savagedev.worldcommand.utils.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class WorldCommand extends JavaPlugin {
    private FileConfiguration lang;

    @Override
    public void onEnable() {
        this.initConfig();
        this.initListeners();
        this.initCommands();
    }

    public void reload() {
        this.reloadConfig();
        this.reloadLang();
    }

    private void initConfig() {
        this.saveDefaultConfig();
        this.saveResource("lang.yml", false);
        this.reloadLang();
    }

    private void initCommands() {
        Objects.requireNonNull(this.getCommand("worldcommand")).setExecutor(new WorldCommandCmd(this));
    }

    private void initListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new WorldChangeE(this), this);
        pluginManager.registerEvents(new JoinE(this), this);
    }

    private void reloadLang() {
        this.lang = FileUtils.load(new File(this.getDataFolder(), "lang.yml"));
    }

    public FileConfiguration getConfig(ConfigType type) {
        if (type == ConfigType.LANG) {
            return this.lang;
        }
        return super.getConfig();
    }

    public enum ConfigType {SETTINGS, LANG}
}
