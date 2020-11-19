package net.dev.art.core.lib.systems.repository.tables.creations;

public class Date implements TableColumnType<String, Date> {

    private String tableString = "DATE";
    private String _default = null;

    private String columnName;

    public Date(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getTableString() {
        return tableString;
    }

    @Override
    public String getDefault() {
        return _default;
    }

    @Override
    public Date setDefault(String s) {
        this._default = _default;
        return this;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public Date current() {
        setDefault("CURRENT_TIMESTAMP");
        return this;
    }

}
