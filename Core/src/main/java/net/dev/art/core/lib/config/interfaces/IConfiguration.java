package net.dev.art.core.lib.config.interfaces;

public interface IConfiguration {

    /*File Management*/

    void save();
    void delete();
    void reload();
    void saveDefault();

    /*Items Management*/

    boolean contains(String path);

    void set(String path, Object value);
    Object setIfNotContains(String path, Object _default);

    Object get(String path);
    Object getOrDefault(String path, Object _default, boolean autoInsert);
    Object getOrDefault(String path, Object _default);

}
