package net.dev.art.core.lib.systems.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

    private Connection connection = null;
    private String
            host,
            port,
            user,
            password ,
            database,
            url = "";
    private boolean autoReconnect = true;
    private ConnectionType type;

    public ConnectionHandler(ConnectionType type, String host, String port, String user, String password, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
        this.type = type;
        if (type == ConnectionType.MYSQL)
            this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoreconnect=" + this.autoReconnect;
        else if (type == ConnectionType.SQLITE)
            this.url = "jdbc:sqlite:C:/sqlite/" + database + ".db";
    }

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                Class.forName(this.type.driver);
                this.connection =
                        this.type == ConnectionType.MYSQL ?
                        DriverManager.getConnection(this.url, this.user, this.password) :
                        DriverManager.getConnection(this.url);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public ConnectionHandler setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ConnectionHandler setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ConnectionHandler setPort(String port) {
        this.port = port;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ConnectionHandler setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConnectionHandler setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public ConnectionHandler setDatabase(String database) {
        this.database = database;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ConnectionHandler setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public ConnectionHandler setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
        return this;
    }

    public enum ConnectionType {
        MYSQL("com.mysql.cj.jdbc.Driver"), SQLITE("org.sqlite.JDBC");

        public String driver;

        ConnectionType(String driver) {
            this.driver = driver;
        }
    }
}
