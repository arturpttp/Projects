package net.dev.art.core.lib.systems.repository;

import net.dev.art.core.lib.systems.repository.providers.*;

public enum RepositoryType {

    MYSQL(MysqlProvider.class), YML(YmlProvider.class), JSON(JsonProvider.class), SQL_LITE(SqlLiteProvider.class);

    private Class<? extends Provider> providerClass;

    RepositoryType(Class<? extends Provider> providerClass) {
        this.providerClass = providerClass;
    }

    public Class<? extends Provider> getProviderClass() {
        return providerClass;
    }

    public RepositoryType setProviderClass(Class<? extends Provider> providerClass) {
        this.providerClass = providerClass;
        return this;
    }
}
