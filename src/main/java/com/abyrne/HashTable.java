package com.abyrne;


import java.util.LinkedList;
import java.util.ListIterator;

public class HashTable<K, V> {
    LinkedList<Entry<K, V>> table[];

    HashTable() {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[31];
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = getIndex(key);
        LinkedList<Entry<K, V>> values = table[index];
        if (values == null) {
            return null;
        }

        for (Entry<K, V> entry : values) {
            if (entry != null && entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }

        int index = getIndex(key);
        LinkedList<Entry<K, V>> values = table[index];
        if (values == null) {
            values = new LinkedList<>();
            values.add(new Entry<K, V>(key, value));
            table[index] = values;
            return true;
        }

        for (Entry<K, V> entry : values) {
            if (entry != null && entry.getKey().equals(key)) {
                entry.setValue(value);
                return true;
            }
        }

        values.add(new Entry<K, V>(key, value));
        return true;
    }

    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int index = getIndex(key);
        LinkedList<Entry<K, V>> values = table[index];
        if (values == null) {
            return null;
        }

        ListIterator<Entry<K, V>> linkedListIterator = values.listIterator();
        while (linkedListIterator.hasNext()) {
            int i = linkedListIterator.nextIndex();
            Entry<K, V> entry = linkedListIterator.next();
            if (entry != null && entry.getKey().equals(key)) {
                values.remove(i);
                return entry.getValue();
            }
        }

        return null;
    }

    private int getIndex(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % table.length;
    }
}

class Entry<K, V> {
    private final K key;
    private V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    void setValue(V value) {
        this.value = value;
    }
}
