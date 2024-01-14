package ru.job4j.gc.leak;

import ru.job4j.gc.leak.model.User;

import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class UserGenerator implements Generate {
    private static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
    private static final String SEPARATOR = " ";
    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private User user;
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        StringJoiner userFullName = new StringJoiner(SEPARATOR);
        userFullName.add(surnames.get(random.nextInt(surnames.size())))
                .add(names.get(random.nextInt(names.size())))
                .add(patrons.get(random.nextInt(patrons.size())));
        this.user = new User(userFullName.toString());
    }

    private void readAll() {
        names = read(PATH_NAMES);
        surnames = read(PATH_SURNAMES);
        patrons = read(PATH_PATRONS);
    }

    public User getRandomUser() {
        generate();
        return user;
    }

}
