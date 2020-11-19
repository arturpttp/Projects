package net.dev.art.economy;

import net.dev.art.core.lib.PluginCore;

public class Economy extends PluginCore {

    public static Economy  instance;

    public Economy() {
        super("Economy", Economy.class);
    }

    @Override
    public void onEnable() {
        instance = this;
    }
}
