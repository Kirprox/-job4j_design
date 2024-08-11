package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        modCount++;
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        boolean isPut = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            isPut = true;
        }
        return isPut;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        V result = null;

        if (table[index] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[index].key)
                    && Objects.equals(key, table[index].key)) {
                result = table[index].value;
            }
        }

        return result;
    }

    @Override
    public boolean remove(K key) {
        modCount++;
        boolean deleted = false;
        int deleteIndex = indexFor(hash(Objects.hashCode(key)));
        if (table[deleteIndex] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[deleteIndex].key)
                    && Objects.equals(key, table[deleteIndex].key)) {
                table[deleteIndex] = null;
                deleted = true;
                count--;
            }
        }
        return deleted;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length && table[index] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity *= 2;
        int index, hashCode;
        K key;
        V value;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                index = indexFor(hash(Objects.hashCode(entry.key)));
                key = entry.key;
                value = entry.value;
                newTable[index] = new MapEntry<>(key, value);
                table = newTable;
            }
        }
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        map.put(null, 0);
        map.put(1, 1);
        System.out.println(map);
        System.out.println("hash" + map.hash(0));
        System.out.println("hash" + map.hash(65535));
        System.out.println("hash" + map.hash(65536));
        System.out.println("indexFor" + map.indexFor(0));
        System.out.println("indexFor" + map.indexFor(7));
        System.out.println("indexFor" + map.indexFor(8));
    }
}