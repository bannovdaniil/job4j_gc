package ru.job4j.gc.leak.model;

public class Comment {
    private final String text;
    private final User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("text='").append(text).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
