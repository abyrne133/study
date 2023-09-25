package com.abyrne;

import java.util.*;

public class ArrayList<T> {

    private static final int DEFAULT_SIZE = 2;

    private Object[] array;

    public ArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public int size() {
        return array.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        return Arrays.stream(this.array).anyMatch(i -> i.equals(o));
    }

    public boolean add(Object o) {
        int addIndex = size();
        doubleArraySize();
        array[addIndex] = o;
        return true;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            Object item = array[i];
            if ((o == null && item == null) || (item != null && item.equals(o))) {
                removeAndShiftRemainingLeft(i);
                return true;
            }
        }
        return false;
    }

    public Object get(int index) {
        checkIndex(index, false);
        return array[index];
    }

    public Object set(int index, Object element) {
        checkIndex(index, false);
        Object previousElement = array[index];
        array[index] = element;
        return previousElement;
    }

    public void add(int index, Object element) {
        if (index >= size()) {
            doubleArraySize();
        }
        checkIndex(index, true);
        for (int i = 0; i < size(); i++) {

        }

    }

    public Object remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator listIterator() {
        return null;
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    private void doubleArraySize() {
        Object[] doubleArray = new Object[size() * 2];
        for (int i = 0; i < size(); i++) {
            doubleArray[i] = array[i];
        }
        array = doubleArray;
    }

    private void removeAndShiftRemainingLeft(int removeIndex) {
        for (int i = removeIndex; i < size(); i++) {
            array[i] = array[i + 1];
        }
    }

    private void checkIndex(int index, boolean permitAtEnd) {
        int upperBound = permitAtEnd ? size() : (size() - 1);
        if (index < 0 || index > upperBound) {
            throw new IndexOutOfBoundsException("At index " + index);
        }
    }
}
