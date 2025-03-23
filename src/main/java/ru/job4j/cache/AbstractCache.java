package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> soft = new SoftReference<V>(value);
        cache.put(key, soft);
    }

    public final V get(K key) {
        SoftReference<V> result = cache.get(key);
        if (result == null) {
            System.out.println("генерал кеноби");
            put(key, load(key));
            result = cache.get(key);
        }
        return result.get();
    }

    protected abstract V load(K key);
}