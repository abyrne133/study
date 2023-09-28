package com.abyrne;

import java.util.*;

public class ArrayList<T> {

    private static final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] array;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        return Arrays.stream(this.array)
                .anyMatch(i -> ((i == null) && (o == null)) || ((i != null) && i.equals(o)));
    }

    public boolean add(Object o) {
        int addIndex = size();
        manageCapacity(addIndex);
        array[addIndex] = o;
        size++;
        return true;
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

    public Object remove(int index) {
        checkIndex(index, false);
        Object element = array[index];
        removeAndShiftRemainingLeft(index);
        size--;
        return element;
    }

    private void manageCapacity(int addIndex) {
        if (addIndex >= array.length) {
            Object[] doubleArray = new Object[array.length * 2];
            for (int i = 0; i < size(); i++) {
                doubleArray[i] = array[i];
            }
            array = doubleArray;
        }
    }

    private void removeAndShiftRemainingLeft(int removeIndex) {
        for (int i = removeIndex; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size() - 1] = null;
    }

    private void checkIndex(int index, boolean permitAtEnd) {
        int upperBound = permitAtEnd ? size() : (size() - 1);
        if (index < 0 || index > upperBound) {
            throw new IndexOutOfBoundsException("At index " + index);
        }
    }
}
