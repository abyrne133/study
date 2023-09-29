package com.abyrne;

public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
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
    }

    public V get(K key) {
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

    public boolean put(K key, V value) {
        if ((size + 1) >= threshold) {
            this.doubleCapacity();
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
        return put(buckets, key, value);
    }

    public V remove(K key) {
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

    private boolean put(Node<K, V>[] buckets, K key, V value) {
        if (key == null) {
            return false;
        }

        int index = getIndex(buckets, key);
        Node<K, V> headNode = buckets[index];

        if (headNode == null) {
            headNode = new Node<K, V>(key, value, null);
            buckets[index] = headNode;
            size++;
            return true;
        }

        if (headNode.getKey().equals(key)) {
            headNode.setValue(value);
            return true;
        }

        // iterate remainder of bucket for key
        Node<K, V> previousNode = headNode;
        Node<K, V> nextNode = headNode.getNext();
        while (nextNode != null) {
            if (nextNode.getKey().equals(key)) {
                headNode.setValue(value);
                return true;
            }

            previousNode = nextNode;
            nextNode = nextNode.getNext();
            previousNode.setNext(nextNode);
        }

        // key not found in bucket, append to end
        Node<K, V> newNode = new Node<K, V>(key, value, null);
        previousNode.setNext(newNode);
        size++;
        return true;
    }

    private int getIndex(Node<K, V>[] buckets, K key) {
        int hashCode = key.hashCode();
        return Math.abs((hashCode ^ (hashCode >>> 16))) % buckets.length;
    }

    private void doubleCapacity() {
        capacity = capacity * 2;
        this.setThreshold();

    }

    private void setThreshold() {
        this.threshold = (int) (capacity * loadFactor);
    }
}