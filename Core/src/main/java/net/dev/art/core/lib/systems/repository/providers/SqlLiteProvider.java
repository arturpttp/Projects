package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.lib.systems.repository.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SqlLiteProvider implements Provider, SQLProvider {

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
                ConnectionHandler.ConnectionType.SQLITE,
                information.getInformation().getHost(),
                information.getInformation().getPort(),
                information.getInformation().getUser(),
                information.getInformation().getPassword(),
                information.getInformation().getDatabase());
        this.connection = connectionHandler.getConnection();
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
        return false;
    }

    @Override
    public void set(String path, Object value) {

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

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public int getInt(String path) {
        return 0;
    }

    @Override
    public double getDouble(String path) {
        return 0;
    }

    @Override
    public float getFloat(String path) {
        return 0;
    }

    @Override
    public long getLong(String path) {
        return 0;
    }

    @Override
    public List<?> getList(String path) {
        return null;
    }

    @Override
    public Map<?, ?> getMap(String path) {
        return null;
    }
}
