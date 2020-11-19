package net.dev.art.core.lib.server.plugin;

public class PluginInformation {

    private String name, author, version;

    public PluginInformation(String name, String author, String version) {
        this.name = name;
        this.author = author;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public PluginInformation setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PluginInformation setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public PluginInformation setVersion(String version) {
        this.version = version;
        return this;
    }
}
