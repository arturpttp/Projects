package net.dev.art.core;

import net.dev.art.core.lib.PluginCore;
import net.dev.art.core.listener.PluginListener;
import org.bukkit.Bukkit;

public class Core extends PluginCore {

    protected boolean free = false;

    public static Core instance;

    public Core() {
        super("Core", Core.class);
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new PluginListener(), this);}
}
