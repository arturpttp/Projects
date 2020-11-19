package net.dev.art.core.lib.systems.repository;

import net.dev.art.core.lib.config.interfaces.TypesConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryItem implements TypesConfig {

    private final Map<String, Object> map;

    public RepositoryItem() {
        this.map = new HashMap<>();
    }

    public RepositoryItem(Map<String, Object> map) {
        this.map = map;
    }

    public boolean contains(String path) {
        return map.containsKey(path);
    }

    public void set(String path, Object value) {
        map.put(path, value);
    }

    public Object setIfNotContains(String path, Object _default) {
        if (!contains(path))
            set(path, _default);
        return get(path);
    }

    public Object get(String path) {
        return map.get(path);
    }

    public Object getOrDefault(String path, Object _default, boolean autoInsert) {
        if (autoInsert)
            setIfNotContains(path, _default);
        return map.getOrDefault(path, _default);
    }

    public Object getOrDefault(String path, Object _default) {
        return getOrDefault(path, _default, true);
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    @Override
    public String getString(String path) {
        return (String) get(path);

    }

    @Override
    public int getInt(String path) {
        return (int) get(path);

    }

    @Override
    public double getDouble(String path) {
        return (double) get(path);
    }

    @Override
    public float getFloat(String path) {
        return (float) get(path);
    }

    @Override
    public long getLong(String path) {
        return (long) get(path);
    }

    @Deprecated
    @Override
    public List<?> getList(String path) {
        return null;
    }

    @Deprecated
    @Override
    public Map<?, ?> getMap(String path) {
        return null;
    }

    public int getId() {
        return contains("id") ? getInt("id") : 0;
    }

}
