package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        V value = null;
        if (cache.containsKey(key)) {
            value = cache.get(key).get();
            if (value == null) {
                value = load(key);
                put(key, value);
            }
        }
        return value;
    }

    protected abstract V load(K key);

}