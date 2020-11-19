package net.dev.art.core.lib.config.interfaces;

import net.dev.art.core.lib.config.storage.StorageObject;

public interface StorageFile {

    void store(String path, StorageObject object);

    StorageObject restore(String path);

}
