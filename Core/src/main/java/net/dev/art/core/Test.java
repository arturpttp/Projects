package net.dev.art.core;

import net.dev.art.core.lib.Serialization;
import net.dev.art.core.lib.systems.file.Folder;
import net.dev.art.core.lib.systems.repository.tables.MysqlTable;
import net.dev.art.core.lib.systems.repository.tables.SqliteTable;
import net.dev.art.core.lib.systems.repository.tables.Table;
import net.dev.art.core.lib.systems.repository.tables.creations.Boolean;
import net.dev.art.core.lib.systems.repository.tables.creations.Date;
import net.dev.art.core.lib.systems.repository.tables.creations.Int;
import net.dev.art.core.lib.systems.repository.tables.creations.Varchar;
import net.dev.art.core.tests.TestAnnotation;
import net.dev.art.core.tests.TestClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {

    private static List<TestClass> tests = new ArrayList<>();

    public static void main(String[] args) {
//        Folder folder = new Folder("Core/src/test/tester")
//                .addFile("test.yml")
//                .addFile("tester.yml");
//        folder.execute();

        Table table = new MysqlTable("users",
            new Int("id").primaryKey().autoIncrement(),
            new Varchar("name", 255),
            new Varchar("group").setDefault("\"Member\""),
            new Boolean("admin").setDefault(Boolean.FALSE),
            new Date("created_on").current()
        ).ifNotExists();

        Table table2 = new SqliteTable();

        System.out.println(table.getQuery());

    }

    private static int getNextId() {
        int id = 1;
        for (TestClass test : tests) {
            while (test.getId() == id)
                id++;
        }
        return id;
    }

}
