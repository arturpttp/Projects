package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.lib.config.interfaces.IConfiguration;
import net.dev.art.core.lib.config.interfaces.TypesConfig;

public interface Provider extends IConfiguration, TypesConfig {

    default void init() {

    }

    void execute();

    void provideInformation(Information information);

}
