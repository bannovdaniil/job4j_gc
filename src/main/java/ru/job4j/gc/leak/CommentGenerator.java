package ru.job4j.gc.leak;

import ru.job4j.gc.leak.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class CommentGenerator implements Generate {

    private static final int COUNT = 50;
    private static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    private static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> comments;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        this.comments = new ArrayList<>();
        read();
    }

    private void read() {
        phrases = read(PATH_PHRASES);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            StringJoiner comment = new StringJoiner(SEPARATOR);
            comment.add(phrases.get(random.nextInt(phrases.size())))
                    .add(phrases.get(random.nextInt(phrases.size())))
                    .add(phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(comment.toString(), userGenerator.getRandomUser()));
        }
    }
}
