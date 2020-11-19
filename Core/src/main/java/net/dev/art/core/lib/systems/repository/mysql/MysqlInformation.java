package net.dev.art.core.lib.systems.repository.mysql;

public class MysqlInformation {

    private String host, port, user, password, database;

    public MysqlInformation(String host, String port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public static MysqlInformation getDefault() {
        return new MysqlInformation(
                "localhost",
                "3306",
                "root",
                ""
        );
    }

    public MysqlInformation database(String database) {
        this.database = database;
        return this;
    }

    public String getHost() {
        return host;
    }

    public MysqlInformation setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public MysqlInformation setPort(String port) {
        this.port = port;
        return this;
    }

    public String getUser() {
        return user;
    }

    public MysqlInformation setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MysqlInformation setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public MysqlInformation setDatabase(String database) {
        this.database = database;
        return this;
    }
}
