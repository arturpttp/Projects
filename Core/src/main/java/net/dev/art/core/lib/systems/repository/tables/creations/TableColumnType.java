package net.dev.art.core.lib.systems.repository.tables.creations;

public interface TableColumnType<T, RETURN> {

    String getTableString();

    T getDefault();

    RETURN setDefault(T t);

    String getColumnName();

    default boolean isPrimaryKey() {
        return false;
    }

    default boolean isAutoIncrement() {
        return false;
    }

    default boolean notNull() {
        return true;
    }

    default boolean unique() {
        return false;
    }

}
