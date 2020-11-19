package net.dev.art.core.lib.systems.repository.tables.creations;

public class Int implements TableColumnType<java.lang.Integer, Int> {

    private String tableString = "INTEGER($length)";

    private int length = 11, _default = -1;

    private String columnName;
    private boolean primaryKey = false, autoIncrement = false;

    public Int(String columnName) {
        this.columnName = columnName;
    }

    public Int(String columnName, int length) {
        this.columnName = columnName;
        this.length = length;
    }

    public String getTableString() {
        return tableString.replace("$length", this.length + "");
    }

    @Override
    public java.lang.Integer getDefault() {
        return _default == -1 ? null : _default;
    }

    @Override
    public Int setDefault(java.lang.Integer integer) {
        this._default = integer;
        return this;
    }

    public Int primaryKey() {
        this.primaryKey = true;
        return this;
    }

    public Int autoIncrement() {
        this.autoIncrement = true;
        return this;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public int getLength() {
        return length;
    }

    public Int setLength(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }
}
