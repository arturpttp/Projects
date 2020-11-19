package net.dev.art.core.lib.config.interfaces;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public interface IBukkitConfig extends IConfiguration, StorageFile, TypesConfig {

    void setItem(String path, ItemStack item);
    void setLocation(String path, Location location);
    ConfigurationSection create(String path);

    ConfigurationSection getSection(String path);
    Location getLocation(String path);
    ItemStack getItem(String path);
    Set<String> getKeys(boolean deep);

   JavaPlugin getPlugin();

}