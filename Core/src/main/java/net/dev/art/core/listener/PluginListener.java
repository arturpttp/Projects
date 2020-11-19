package net.dev.art.core.listener;

import net.dev.art.core.Core;
import net.dev.art.core.lib.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginListener implements Listener {

    @EventHandler
    void enable(PluginEnableEvent event) {
        if (event.getPlugin() instanceof PluginCore) {
            PluginCore plugin = (PluginCore) event.getPlugin();
            //TODO: implement modules and messages
//            for (String s : plugin.ENABLE_MESSAGE.split("\n")) {
//                API.console(s.replace("$author", plugin.author).replace("$name", plugin.getName()).replace("$version", plugin.version));
//            }
//            plugin.modules.forEach(Module::enable);
//            if (!plugin.free)
//                plugin.prefixed(plugin.THANKS_FOR_BUY_MESSAGE.replace("$author", plugin.author).replace("$name", plugin.getName()).replace("$version", plugin.version));
            try {
                plugin.register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    void disable(PluginDisableEvent event) {
//        if (event.getPlugin() instanceof PluginCore) {
//            PluginCore plugin = (PluginCore) event.getPlugin();
//            plugin.setup();
//            for (String s : plugin.DISABLE_MESSAGE.split("\n")) {
//                plugin.prefixed(s.replace("$author", plugin.author).replace("$name", plugin.getName()).replace("$version", plugin.version));
//            }
//            plugin.modules.forEach(Module::disable);
//            if (!plugin.free)
//                console(plugin.THANKS_FOR_BUY_MESSAGE.replace("$author", plugin.author).replace("$name", plugin.getName()).replace("$version", plugin.version));
//        }
    }

}
