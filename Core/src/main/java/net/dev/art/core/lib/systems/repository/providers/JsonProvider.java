package net.dev.art.core.lib.systems.repository.providers;

import java.util.List;
import java.util.Map;

public class JsonProvider implements Provider {

    public Information information;

    @Override
    public void provideInformation(Information information) {
        this.information = information;
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

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public int getInt(String path) {
        return 0;
    }

    @Override
    public double getDouble(String path) {
        return 0;
    }

    @Override
    public float getFloat(String path) {
        return 0;
    }

    @Override
    public long getLong(String path) {
        return 0;
    }

    @Override
    public List<?> getList(String path) {
        return null;
    }

    @Override
    public Map<?, ?> getMap(String path) {
        return null;
    }
}
