package net.dev.art.core.lib.config;

import net.dev.art.core.lib.config.interfaces.IBukkitConfig;
import net.dev.art.core.lib.config.storage.StorageObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Config implements IBukkitConfig {

    private String path, realName;
    private JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    public Config(String path, JavaPlugin plugin) {
        this.path = path;
        this.realName = this.path.contains("/") ? this.path.split("/")[this.path.split("/").length - 1] : this.path;
        this.realName = this.realName.endsWith(".yml") ? this.realName : this.realName + ".yml";
        this.plugin = plugin;
        this.file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                debug("§ecan't create config §f'" + realName + "'§e");
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private void debug(String message) {
        Bukkit.getConsoleSender().sendMessage("§6§l[CONFIG] §8» §f" + message);
    }

    @Override
    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            debug("§ecan't save config §f'"+realName+"'§e.");
        }
    }

    @Override
    public void delete() {
        this.file.delete();
        debug("§edeleting config §f'"+realName+"'§e.");

    }

    @Override
    public void reload() {

    }

    @Override
    public void saveDefault() {

    }

    @Override
    public void set(String path, Object value) {
        this.config.set(path, value);
    }

    @Override
    public Object setIfNotContains(String path, Object _default) {
        if (!this.contains(path))
            this.set(path, _default);
        return _default;
    }

    @Override
    public void setItem(String path, ItemStack item) {
        ConfigurationSection config = create(path);
        config.set("id", item.getTypeId());
        config.set("data", item.getDurability());
        config.set("amount", item.getAmount());
        config.set("name", item.getItemMeta().getDisplayName().replace("§", "&"));
        config.set("lore", item.getItemMeta().getLore());
        config.set("unbreakable", item.getItemMeta().spigot().isUnbreakable());
        item.getEnchantments().forEach((enchant, level) -> config.set("Enchantments." + enchant.getName(), level));
    }

    @Override
    public void setLocation(String path, Location location) {
        ConfigurationSection config = create(path);
        config.set("x", location.getX());
        config.set("y", location.getY());
        config.set("z", location.getZ());
        config.set("yaw", location.getYaw());
        config.set("pitch", location.getPitch());
        config.set("world", location.getWorld().getName());
    }

    @Override
    public ConfigurationSection create(String path) {
        return this.config.getConfigurationSection(path);
    }

    @Override
    public boolean contains(String path) {
        return this.config.contains(path);
    }

    @Override
    public void store(String path, StorageObject object) {
        //TODO: store an StorageObject in config
    }

    @Override
    public StorageObject restore(String path) {
        //TODO: restore an StorageObject from config
        return null;
    }

    @Override
    public ConfigurationSection getSection(String path) {
        return create(path);
    }

    @Override
    public Location getLocation(String path) {
        //TODO: get a location from config
        return null;
    }

    @Override
    public ItemStack getItem(String path) {
        //TODO: get an item from config
        return null;
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return this.config.getKeys(deep);
    }

    @Override
    public Object get(String path) {
        return this.config.get(path);
    }

    @Override
    public Object getOrDefault(String path, Object _default, boolean autoInsert) {
        if (!this.contains(path))
            set(path, _default);
        return this.get(path);
    }

    @Override
    public Object getOrDefault(String path, Object _default) {
        return this.getOrDefault(path, _default, true);
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
