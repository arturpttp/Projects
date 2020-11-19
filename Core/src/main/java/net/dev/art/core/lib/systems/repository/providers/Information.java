package net.dev.art.core.lib.systems.repository.providers;

import net.dev.art.core.lib.config.Config;
import net.dev.art.core.lib.systems.repository.mysql.MysqlInformation;
import net.dev.art.core.lib.systems.repository.tables.MysqlTable;
import net.dev.art.core.lib.systems.repository.tables.SqliteTable;

public class Information {

    private MysqlInformation information;
    private Config yml;
    private MysqlTable mysql;
    private SqliteTable sqlite;

    public Information(MysqlInformation information, Config yml, MysqlTable mysql, SqliteTable sqlite) {
        this.information = information;
        this.yml = yml;
        this.mysql = mysql;
        this.sqlite = sqlite;
    }

    public Config getYml() {
        return yml;
    }

    public Information setYml(Config yml) {
        this.yml = yml;
        return this;
    }

    public MysqlTable getMysql() {
        return mysql;
    }

    public Information setMysql(MysqlTable mysql) {
        this.mysql = mysql;
        return this;
    }

    public SqliteTable getSqlite() {
        return sqlite;
    }

    public Information setSqlite(SqliteTable sqlite) {
        this.sqlite = sqlite;
        return this;
    }

    public MysqlInformation getInformation() {
        return information;
    }

    public Information setInformation(MysqlInformation information) {
        this.information = information;
        return this;
    }
}
