package net.dev.art.core.lib.systems.commands;

import net.dev.art.core.lib.server.enums.Result;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public interface ICommand {

    Result command(CommandSender sender, String[] args);
    void aliases(String... aliases);
    void register(JavaPlugin plugin);
    void register();
    CommandMap getCommandMap();
}
