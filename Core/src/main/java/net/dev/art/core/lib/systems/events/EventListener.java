package net.dev.art.core.lib.systems.events;

import net.dev.art.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventListener implements Listener {

    public static final List<EventListener> LISTENERS = new ArrayList<>();
    private JavaPlugin plugin;

    public void register(JavaPlugin plugin) {
        this.plugin = plugin;
        LISTENERS.add(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void register() {
        register(Core.instance.getJavaPlugin()); //Same -> JavaPlugin.getProvidingPlugin(Core.class);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
