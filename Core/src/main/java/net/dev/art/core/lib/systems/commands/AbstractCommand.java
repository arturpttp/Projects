package net.dev.art.core.lib.systems.commands;

import net.dev.art.core.Core;
import net.dev.art.core.lib.server.enums.Result;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand extends Command implements ICommand {

    private JavaPlugin plugin;
    public static final Map<String, AbstractCommand> COMMAND_MAP = new HashMap<>();
    public static String NO_PERMISSION_MESSAGE = "§cYou don't have permission!";
    public String permission = "no-permission";
    public AbstractCommand(String name) {
        super(name);
    }

    public AbstractCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!permission.equalsIgnoreCase("no-permission") && !commandSender.hasPermission(permission)) {
            commandSender.sendMessage(NO_PERMISSION_MESSAGE);
            return false;
        }
        return command(commandSender, args) != Result.ERROR;
    }

    @Override
    public void aliases(String... aliases) {
        List<String> a = getAliases();
        a.addAll(Arrays.asList(aliases));
        setAliases(a);
    }

    @Override
    public void register(JavaPlugin plugin) {
        this.plugin = plugin;
        getCommandMap().register(getName(), this);
        //Default no permission message
        NO_PERMISSION_MESSAGE = "§1"+plugin.getDescription().getName() + " §8»  §7You don't have§c access§7 to that command.";
        COMMAND_MAP.put(getName(), this);
    }

    @Override
    public void register() {
        //this used to auto registry commands in core plugin
        register(JavaPlugin.getProvidingPlugin(Core.class));
    }

    @Override
    public CommandMap getCommandMap() {
        try {
            if (Bukkit.getServer() instanceof CraftServer) {
                final Field f = CraftServer.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                return (CommandMap) f.get(Bukkit.getServer());
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
