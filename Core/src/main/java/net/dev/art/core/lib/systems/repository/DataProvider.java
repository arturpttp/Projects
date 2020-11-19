package net.dev.art.core.lib.systems.repository;

import net.dev.art.core.lib.config.interfaces.IConfiguration;

public class DataProvider implements IConfiguration {

    //YML
    //JSON
    //MYSQL
    //SQL LITE

    //Example, if mysql is enabled will use some class like MysqlDataProvider to provide data

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
