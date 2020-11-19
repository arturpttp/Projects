package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.lib.config.interfaces.IConfiguration;

public interface Provider extends IConfiguration {

    default void init() {

    }

    void execute();

    void provideInformation(Information information);

}
