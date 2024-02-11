package com.abyrne;

import java.util.AbstractMap;
import java.util.Set;
import java.util.function.Function;

public class HashMap<K, V> extends AbstractMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final Function<Object, Integer> preHashFunction;
    private Node<K, V>[] buckets;
    private int capacity;
    private final float loadFactor;
    private int threshold;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.setThreshold();
        this.size = 0;
        this.buckets = (Node<K, V>[]) new Node<?, ?>[capacity];
        this.preHashFunction = (k) -> defaultPreHash(k);
    }

    public HashMap(int initialCapacity, float loadFactor, Function<Object, Integer> preHashFunction) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.setThreshold();
        this.size = 0;
        this.buckets = (Node<K, V>[]) new Node<?, ?>[capacity];
        this.preHashFunction = preHashFunction;
    }

    public V get(Object key) {
        if (key == null || buckets == null) {
            return null;
        }

        int index = getIndex(buckets, key);
        Node<K, V> currentNode = buckets[index];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            return null;
        }

        if ((size + 1) >= threshold) {
            this.doubleCapacity();
        }

        return put(buckets, key, value);
    }

    public V remove(Object key) {
        if (key == null || buckets == null) {
            return null;
        }

        int index = getIndex(buckets, key);
        Node<K, V> headNode = buckets[index];

        if (headNode == null) {
            return null;
        }

        if (headNode.getKey().equals(key)) {
            buckets[index] = headNode.getNext();
            return headNode.getValue();
        }

        Node<K, V> previousNode = headNode;
        Node<K, V> nextNode = headNode.getNext();
        while (nextNode != null) {
            if (headNode.getKey().equals(key)) {
                previousNode.setNext(nextNode.getNext());
                return nextNode.getValue();
            }
            previousNode = nextNode;
            nextNode = nextNode.getNext();
            previousNode.setNext(nextNode);
        }

        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    private V put(Node<K, V>[] buckets, K key, V value) {
        if (key == null) {
            return null;
        }

        int index = getIndex(buckets, key);
        Node<K, V> headNode = buckets[index];

        if (headNode == null) {
            headNode = new Node<K, V>(key, value, null);
            buckets[index] = headNode;
            size++;
            return null;
        }

        if (headNode.getKey().equals(key)) {
            V oldValue = headNode.getValue();
            headNode.setValue(value);
            return oldValue;
        }

        // iterate remainder of bucket for key
        Node<K, V> previousNode = headNode;
        Node<K, V> nextNode = headNode.getNext();
        while (nextNode != null) {
            if (nextNode.getKey().equals(key)) {
                V oldValue = nextNode.getValue();
                nextNode.setValue(value);
                return oldValue;
            }

            previousNode = nextNode;
            nextNode = nextNode.getNext();
            previousNode.setNext(nextNode);
        }

        // key not found in bucket, append to end
        Node<K, V> newNode = new Node<K, V>(key, value, null);
        previousNode.setNext(newNode);
        size++;
        return null;
    }

    private int defaultPreHash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int getIndex(Node<K, V>[] buckets, Object key) {
        return Math.abs(preHashFunction.apply(key)) % buckets.length;
    }

    private void doubleCapacity() {
        capacity = capacity * 2;
        this.setThreshold();
        this.rehash();
    }

    private void setThreshold() {
        threshold = (int) (capacity * loadFactor);
    }

    private void rehash() {
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node<?, ?>[capacity];
        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> headNode = buckets[i];
            if (headNode != null) {
                Node<K, V> nextNode = headNode;
                while (nextNode != null) {
                    put(newBuckets, nextNode.getKey(), nextNode.getValue());
                    nextNode = nextNode.getNext();
                }
            }
        }
        buckets = newBuckets;
    }
}