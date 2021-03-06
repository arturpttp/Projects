package net.dev.art.core.lib.systems.repository.tables;

import net.dev.art.core.interfaces.Placeholdable;
import net.dev.art.core.lib.systems.repository.tables.creations.TableColumnType;

public class MysqlTable implements Table, Placeholdable {

    //CREATE TABLE IF NOT EXISTS $name(`id` INTEGER($length) PRIMARY KEY

    private TableColumnType<?, ?>[] columns;
    private boolean ifNotExists = false;
    private String tableName;

    public MysqlTable(String tableName, TableColumnType<?, ?>... columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public MysqlTable ifNotExists() {
        this.ifNotExists = true;
        return this;
    }

    public TableColumnType<?, ?>[] getColumns() {
        return columns;
    }

    public MysqlTable setColumns(TableColumnType<?, ?>[] columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public String getQuery() {
        String query = ifNotExists ? "CREATE TABLE IF NOT EXISTS `$name` (" : "CREATE TABLE `$name` (";
        int i = 1;
        for (TableColumnType<?, ?> column : columns) {
            query += "`" + column.getColumnName() + "` " + column.getTableString();
            if (column.isPrimaryKey())
                query += " PRIMARY KEY";
            if (column.isAutoIncrement())
                query += " AUTO_INCREMENT";
            if (column.notNull())
                query += " NOT NULL";
            if (column.getDefault() != null)
                query += " DEFAULT " + column.getDefault();
            if (i < columns.length) {
                i++;
                query += ", ";
            }
        }
        addReplacer("$name", tableName);
        query += ");";
        return getReplaced(query);
    }
}
