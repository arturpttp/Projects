package net.dev.art.core.lib.systems.repository.providers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLProvider {

    Connection getConnection();

    ResultSet result(String query, Object... objects);

    void preparedExecute(String query, Object... objects);

    void execute(String query);

    void executeQueries(String... queries);

    default PreparedStatement setStatements(PreparedStatement preparedStatement, Object[] objects) throws SQLException {
        if (objects != null)
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject((i + 1), objects[i]);
            }
        return preparedStatement;
    }

    void close();
}
