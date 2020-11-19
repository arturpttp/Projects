package net.dev.art.core.lib.systems.repository.tables.creations;

public class Varchar implements TableColumnType<String, Varchar> {

    private String tableString = "VARCHAR($length)";

    private int length = 50;
    private String _default = null;
    private String columnName;
    public Varchar(String columnName) {
        this.columnName = columnName;
    }

    public Varchar(String columnName, int length) {
        this.columnName = columnName;
        this.length = length;
    }

    public String getTableString() {
        return tableString.replace("$length", this.length+"");
    }

    @Override
    public String getDefault() {
        return _default;
    }

    @Override
    public Varchar setDefault(String s) {
        this._default = s;
        return this;
    }

    public int getLength() {
        return length;
    }

    public Varchar setLength(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }
}
