package ru.job4j.gc.leak;

import ru.job4j.gc.leak.model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private final Map<Integer, Post> posts = new HashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        if (post.getId() == null) {
            Integer id = atomicInteger.getAndIncrement();
            post.setId(id);
        }
        posts.put(post.getId(), post);

        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}