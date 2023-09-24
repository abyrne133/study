package com.abyrne;


class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    Node(Node<K, V> node) {
        this(node.key, node.value, node.next);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node<?, ?>)) {
            return false;
        }
        Node<?, ?> other = (Node<?, ?>) o;
        return (other.getKey() == null ? this.getKey() == null : other.getKey().equals(this.getKey()))
                && (other.getValue() == null ? this.getValue() == null : other.getValue().equals(this.getValue()));
    }

    @Override
    public int hashCode() {
        return this.key.hashCode() ^ this.value.hashCode();
    }
}
