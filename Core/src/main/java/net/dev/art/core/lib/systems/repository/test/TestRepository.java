package net.dev.art.core.lib.systems.repository.test;

import net.dev.art.core.lib.config.Config;
import net.dev.art.core.lib.systems.repository.Repository;
import net.dev.art.core.lib.systems.repository.RepositoryItem;
import net.dev.art.core.lib.systems.repository.RepositoryType;
import net.dev.art.core.lib.systems.repository.mysql.MysqlInformation;
import net.dev.art.core.lib.systems.repository.providers.Information;
import net.dev.art.core.lib.systems.repository.tables.MysqlTable;
import net.dev.art.core.lib.systems.repository.tables.SqliteTable;
import net.dev.art.core.lib.systems.repository.tables.creations.Boolean;
import net.dev.art.core.lib.systems.repository.tables.creations.Date;
import net.dev.art.core.lib.systems.repository.tables.creations.Int;
import net.dev.art.core.lib.systems.repository.tables.creations.Varchar;
import org.bukkit.plugin.java.JavaPlugin;

public class TestRepository extends Repository<TestObject> {

    public TestRepository(JavaPlugin plugin) {
        super(RepositoryType.YML, new Information(
                MysqlInformation.getDefault().database("test"),
                new Config("test.yml", plugin),
                new MysqlTable("test",
                        new Int("id",11).primaryKey().autoIncrement(),
                        new Varchar("nick", 256),
                        new Boolean("logged").setDefault(Boolean.FALSE),
                        new Date("last_login").current()
                ).ifNotExists(),
                new SqliteTable("test",
                        new Int("id", 11).primaryKey().autoIncrement(),
                        new Varchar("nick", 255),
                        new Boolean("logged").setDefault(Boolean.FALSE),
                        new Date("last_login").current()
                ).ifNotExists()
        ));
    }

    @Override
    public RepositoryItem serialize(TestObject testObject) {
        RepositoryItem item = new RepositoryItem();
        item.set("id", testObject.getId());
        item.set("name", testObject.getName());
        item.set("money", testObject.getMoney());
        return item;
    }

    @Override
    public TestObject deserialize(RepositoryItem item) {
        return new TestObject(item.getInt("id"), item.getString("name"), item.getDouble("money"));
    }
}
