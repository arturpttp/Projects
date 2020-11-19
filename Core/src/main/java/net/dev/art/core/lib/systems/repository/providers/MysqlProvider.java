package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.interfaces.Placeholdable;
import net.dev.art.core.lib.systems.repository.ConnectionHandler;
import net.dev.art.core.lib.systems.repository.tables.creations.TableColumnType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MysqlProvider implements Provider, SQLProvider, Placeholdable {

    public Information information;

    private ConnectionHandler connectionHandler;
    private Connection connection;

    @Override
    public void provideInformation(Information information) {
        this.information = information;
    }

    @Override
    public void init() {
        connectionHandler = new ConnectionHandler(
                ConnectionHandler.ConnectionType.MYSQL,
                information.getInformation().getHost(),
                information.getInformation().getPort(),
                information.getInformation().getUser(),
                information.getInformation().getPassword(),
                information.getInformation().getDatabase());
        this.connection = connectionHandler.getConnection();
        addReplacer("{$table}", this.information.getMysql().getTableName());
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public ResultSet result(String query, Object... objects) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return setStatements(preparedStatement, objects).executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void preparedExecute(String query, Object... objects) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setStatements(preparedStatement, objects).executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void execute(String query) {
        try {
            connection.createStatement().executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void executeQueries(String... queries) {
        for (String query : queries) {
            execute(query);
        }
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void execute() {

    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void saveDefault() {

    }

    @Override
    public boolean contains(String path) {
        String[] info = path.split(";");
        String query = "SELECT * FROM {$table} WHERE {$column} = ?";
        addReplacer("{$column}", info[0]);
        ResultSet result = result(getReplaced(query), info[1]);
        removeReplacer("{$column}");
        try {
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void set(String path, Object value) {
        String query = "INSERT INTO {$table} ($table_fields}) VALUES ({$insert_values});";
        String tableFields = "";
        List<TableColumnType<?, ?>> columns = Arrays.asList(information.getMysql().getColumns());
        for (TableColumnType<?, ?> column : columns) {
            if (!column.isAutoIncrement())
                tableFields += "`" + column.getColumnName() + ((columns.indexOf(column) == columns.size() - 1 ? "`" : "`, ");
        }
        addReplacer("{$table_fields}", tableFields);

        execute("");
    }

    @Override
    public Object setIfNotContains(String path, Object _default) {
        return null;
    }

    @Override
    public Object get(String path) {
        return null;
    }

    @Override
    public Object getOrDefault(String path, Object _default, boolean autoInsert) {
        return null;
    }

    @Override
    public Object getOrDefault(String path, Object _default) {
        return null;
    }
}
