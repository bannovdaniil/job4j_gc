package ru.job4j.gc;

/**
 * This Object = 16 byte
 * int +4 byte
 * boolean +1 byte
 * Links +4 ?
 */
public class User {
    private int age;
    private String name;
    private boolean married;

    public User(int age, String name, boolean married) {
        this.age = age;
        this.name = name;
        this.married = married;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("I'm never will back. Rip: %s, age %d was %b %n", name, age, married);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
