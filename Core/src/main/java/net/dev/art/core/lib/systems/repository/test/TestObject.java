package net.dev.art.core.lib.systems.repository.test;

import net.dev.art.core.lib.systems.repository.Storable;

public class TestObject implements Storable {

    private int id;
    private String name;
    private double money;

    public TestObject(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public TestObject setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestObject setName(String name) {
        this.name = name;
        return this;
    }

    public double getMoney() {
        return money;
    }

    public TestObject setMoney(double money) {
        this.money = money;
        return this;
    }
}
