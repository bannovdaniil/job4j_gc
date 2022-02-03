package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * показываем содержимое кеша
     */
    public void showMeCache() {
        if (cache.size() != 0) {
            for (var key : cache.keySet()) {
                System.out.println(key + (cache.get(key).get() != null
                        ? " - OK" : " - NOT IN CACHE"));
            }
        } else {
            System.out.println("Not any file put in cache.");
        }
    }

    /**
     * подгружает содержимое файла в кеш
     *
     * @param key - имя файла
     * @return - содержимое
     */
    @Override
    protected String load(String key) {
        String value = null;
        try {
            if (!cache.containsKey(key) || cache.get(key).get() == null) {
                value = Files.readString(Paths.get(cachingDir, key));
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
        return value;
    }

}