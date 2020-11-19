package net.dev.art.core.interfaces;

import net.dev.art.core.lib.PluginCore;
import org.bukkit.plugin.java.JavaPlugin;

public interface IPluginCore {

    void register() throws Exception;

    JavaPlugin getJavaPlugin();

    String getPluginName();

    Class<? extends PluginCore> getProvidingClass();

}
