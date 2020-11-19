package net.dev.art.core.interfaces;

import java.util.LinkedHashMap;
import java.util.Map;

public interface Placeholdable {

    Map<String, String> replaces = new LinkedHashMap<>();

    default Placeholdable addReplacer(String key, String value) {
        replaces.put(key, value);
        return this;
    }

    default Placeholdable removeReplacer(String key) {
        replaces.remove(key);
        return this;
    }

    default Placeholdable copy(Placeholdable toCopy) {
        replaces.putAll(toCopy.getReplaces());
        return this;
    }

    default String getReplacer(String key) {
        return replaces.get(key);
    }

    default String getReplaced(String message) {
        for (Map.Entry<String, String> entry : replaces.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            message = message.replace(k, v);
        }
        return message;
    }

    default Map<String, String> getReplaces() {
        return replaces;
    }

}
