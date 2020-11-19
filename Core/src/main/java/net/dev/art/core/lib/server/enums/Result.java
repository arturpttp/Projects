package net.dev.art.core.lib.server.enums;

import net.dev.art.core.API;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Result {

    SUCCESS("§aSuccess §7» "),
    INFO("§bInfo §7» "),
    WARNING("§eWarning §7» "),
    ERROR("§cError §7» "),
    CONSOLE("§dDebug §7» ", true),
    UNDEFINED("§fUndefined §7» ");

    private String name, prefix, message;
    private boolean onlyConsole;

    Result(String prefix, String message, boolean onlyConsole) {
        this.name = this.toString().toUpperCase();
        this.prefix = prefix;
        this.message = message;
        this.onlyConsole = onlyConsole;
    }

    Result(String prefix, boolean onlyConsole) {
        this(prefix, "", onlyConsole);
    }

    Result(String prefix) {
        this(prefix, "", false);
    }

    public Result send(CommandSender sender, boolean reset) {
        if (onlyConsole && (sender instanceof Player)) {
            System.out.println("Some error in your code, message that is only for console is sending for players!");
            return this;
        }
        if (this == UNDEFINED) {
            if (!message.equalsIgnoreCase(""))
                if (message.contains("\n"))
                    for (String s : message.split("\n")) {
                        System.out.println(API.replaceColorsCodes(s));
                    }
                else System.out.println(API.replaceColorsCodes(message));
            if (reset)
                this.message = "";
            return this;
        }
        if (!message.equalsIgnoreCase(""))
            if (message.contains("\n"))
                for (String s : message.split("\n")) {
                    API.console(s);
                }
            else API.console(message);
        if (reset)
            this.message = "";
        return this;
    }

    public Result send(CommandSender sender) {
        return this.send(sender, true);
    }

    public String getName() {
        return name;
    }

    public Result setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Result setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setOnlyConsole(boolean onlyConsole) {
        this.onlyConsole = onlyConsole;
        return this;
    }

    public boolean isOnlyConsole() {
        return onlyConsole;
    }
}
