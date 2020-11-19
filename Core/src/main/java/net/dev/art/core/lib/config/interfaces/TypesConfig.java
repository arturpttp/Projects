package net.dev.art.core.lib.config.interfaces;

import java.util.List;
import java.util.Map;

public interface TypesConfig {

    String getString(String path);
    int getInt(String path);
    double getDouble(String path);
    float getFloat(String path);
    long getLong(String path);

    List<?> getList(String path);
    Map<?, ?> getMap(String path);

}
