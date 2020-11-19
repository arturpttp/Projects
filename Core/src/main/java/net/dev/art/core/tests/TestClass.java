package net.dev.art.core.tests;

public class TestClass {

    private String test;
    private int id;

    public TestClass(String test, int id) {
        this.test = test;
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public TestClass setTest(String test) {
        this.test = test;
        return this;
    }

    public int getId() {
        return id;
    }

    public TestClass setId(int id) {
        this.id = id;
        return this;
    }
}
