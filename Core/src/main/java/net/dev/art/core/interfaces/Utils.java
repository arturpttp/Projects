package net.dev.art.core.interfaces;

import org.bukkit.Bukkit;

public interface Utils {

    public default String[] strs(String... strs) {
        return strs;
    }

    public default void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
    public default void console(String... messages) {
        Bukkit.getConsoleSender().sendMessage(messages);
    }

}
