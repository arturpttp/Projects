package net.dev.art.core.lib.systems.repository.tables.creations;

public class Boolean implements TableColumnType<java.lang.Boolean, Boolean> {

    private String tableString = "BOOLEAN";
    private String columnName;

    private boolean _default = TRUE, unique = false;

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    public Boolean(String columnName) {
        this.columnName = columnName;
    }

    public String getTableString() {
        return tableString;
    }

    @Override
    public java.lang.Boolean getDefault() {
        return this._default;
    }

    @Override
    public Boolean setDefault(java.lang.Boolean aBoolean) {
        this._default = aBoolean;
        return this;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean unique() {
        this.unique = true;
        return unique;
    }
}
