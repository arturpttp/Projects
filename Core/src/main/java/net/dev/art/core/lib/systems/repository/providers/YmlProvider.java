package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.lib.config.Config;

import java.util.List;
import java.util.Map;

public class YmlProvider implements Provider {

    public Information information;
    public Config config;

    @Override
    public void provideInformation(Information information) {
        this.information = information;
        config = information.getYml();
    }

    @Override
    public void init() {
        config.save();
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
        return config.contains(path);
    }

    @Override
    public void set(String path, Object value) {
        config.set(path, value);
    }

    @Override
    public Object setIfNotContains(String path, Object _default) {
        return config.setIfNotContains(path, _default);
    }

    @Override
    public Object get(String path) {
        return config.get(path);
    }

    @Override
    public Object getOrDefault(String path, Object _default, boolean autoInsert) {
        return config.getOrDefault(path, _default, autoInsert);
    }

    @Override
    public Object getOrDefault(String path, Object _default) {
        return config.getOrDefault(path, _default);
    }

    @Override
    public String getString(String path) {
        return config.getString(path);
    }

    @Override
    public int getInt(String path) {
        return config.getInt(path);
    }

    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    @Override
    public float getFloat(String path) {
        return config.getFloat(path);
    }

    @Override
    public long getLong(String path) {
        return config.getLong(path);
    }

    @Override
    public List<?> getList(String path) {
        return config.getList(path);
    }

    @Override
    public Map<?, ?> getMap(String path) {
        return config.getMap(path);
    }
}
