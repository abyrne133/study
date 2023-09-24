package com.abyrne;

public class HashTable<K, V> {
    Node<K, V>[] table;

    public V get(K key) {
        if (key == null || table == null) {
            return null;
        }

        int index = getIndex(key);
        Node<K, V> currentNode = table[index];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }

        if (table == null) {
            table = (Node<K, V>[]) new Node<?, ?>[31];
        }

        int index = getIndex(key);
        Node<K, V> headNode = table[index];

        if (headNode == null) {
            headNode = new Node<K, V>(key, value, null);
            table[index] = headNode;
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
        return true;
    }

    public V remove(K key) {
        if (key == null || table == null) {
            return null;
        }

        int index = getIndex(key);
        Node<K, V> headNode = table[index];

        if (headNode == null) {
            return null;
        }

        if (headNode.getKey().equals(key)) {
            table[index] = headNode.getNext();
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

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % table.length);
    }
}