package net.dev.art.core.lib.config.storage;

import net.dev.art.core.lib.config.interfaces.TypesConfig;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StorageObject implements TypesConfig {

    private Map<String, Object> attributes = new LinkedHashMap<>();

    private Object get(String key) {
        return attributes.get(key);
    }

    private StorageObject set(String key, Object value) {
        attributes.put(key, value);
        return this;
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

    @Override
    public List<?> getList(String path) {
        //TODO: Serialization Class and list serialize
        return null;
    }

    @Deprecated
    @Override
    public Map<?, ?> getMap(String path) {
        return null;
    }
}
