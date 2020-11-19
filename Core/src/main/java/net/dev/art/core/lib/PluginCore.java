package net.dev.art.core.lib;

import net.dev.art.core.API;
import net.dev.art.core.interfaces.IPluginCore;
import net.dev.art.core.interfaces.Placeholdable;
import net.dev.art.core.lib.server.plugin.PluginInformation;
import net.dev.art.core.lib.systems.commands.AbstractCommand;
import net.dev.art.core.lib.systems.events.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin implements IPluginCore, Placeholdable {

    private String name;
    private Class<? extends PluginCore> clazz;
    protected boolean free = true;
    private double cost;
    private PluginInformation information;

    public PluginCore(String name, Class<? extends PluginCore> clazz) {
        this.name = name;
        this.clazz = clazz;
        if (this.free)
            this.cost = 0.0;
        this.information = new PluginInformation(
                name,
                getJavaPlugin().getDescription().getAuthors().size() > 0 ? getJavaPlugin().getDescription().getAuthors().get(0) : "Anonymous",
                getJavaPlugin().getDescription().getVersion()
        );
        addReplacer("{plugin-name}", this.name);
        addReplacer("{plugin-author}", this.information.getAuthor());
        addReplacer("{plugin-version}", this.information.getVersion());
        addReplacer("{plugin-free}", this.free+"");
        addReplacer("{plugin-cost}", this.cost+"");
    }

    @Override
    public void register() throws Exception {
        String pkg = clazz.getPackage().toString().replace("package ", "");
        JavaPlugin pl = JavaPlugin.getPlugin(clazz);
        for (Class<?> cls : API.getClassesForPackage(pl, pkg)) {
            if (EventListener.class.isAssignableFrom(cls) && cls != EventListener.class) {
                EventListener cz = (EventListener) cls.newInstance();
                cz.register(pl);
                API.console("§bEventAPI §8» §fLoading events in class: " + cls.getSimpleName());
            }
            if (AbstractCommand.class.isAssignableFrom(cls) && cls != AbstractCommand.class) {
                AbstractCommand cz = (AbstractCommand) cls.newInstance();
                cz.register(pl);
                API.console("§bCommandAPI §8» §fLoading command: " + cz.getName());
            }
        }
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return getProvidingPlugin(clazz);
    }

    @Override
    public String getPluginName() {
        return name;
    }

    @Override
    public Class<? extends PluginCore> getProvidingClass() {
        return clazz;
    }

    public double getCost() {
        return cost;
    }

    public PluginCore setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public PluginInformation getInformation() {
        return information;
    }

    public PluginCore setInformation(PluginInformation information) {
        this.information = information;
        return this;
    }
}
