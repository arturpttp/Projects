package net.dev.art.core.lib.systems.repository.providers;

public class YmlProvider implements Provider {

    public Information information;

    @Override
    public void provideInformation(Information information) {
        this.information = information;
        information.getYml().save();
    }

    @Override
    public void execute() {
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void saveDefault() {

    }

    @Override
    public boolean contains(String path) {
        return false;
    }

    @Override
    public void set(String path, Object value) {

    }

    @Override
    public Object setIfNotContains(String path, Object _default) {
        return null;
    }

    @Override
    public Object get(String path) {
        return null;
    }

    @Override
    public Object getOrDefault(String path, Object _default, boolean autoInsert) {
        return null;
    }

    @Override
    public Object getOrDefault(String path, Object _default) {
        return null;
    }
}
