package com.abyrne;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void givenArrayList_whenAdd_SizeIncrements() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        assertEquals(0, arrayList.size());

        for (int i = 0; i < 200; i++) {
            arrayList.add(i);
            assertEquals(i + 1, arrayList.size());
        }
    }

    @Test
    void givenMultipleAdded_whenSubsetRemoved_thenArrayContainsExpectedRemainingElements() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            arrayList.add(i);
        }

        assertEquals(7, arrayList.remove(7));
        assertEquals(5, arrayList.remove(5));

        // index 197 returns 199 value because of shifting (two elements before this index were already removed)
        assertEquals(199, arrayList.remove(197));

        assertFalse(arrayList.contains(7));
        assertFalse(arrayList.contains(5));
        assertFalse(arrayList.contains(199));

        for (int i = 0; i < 200; i++) {
            if (i != 5 && i != 7 && i != 199) {
                assertTrue(arrayList.contains(i));
            }
        }
    }

    @Test
    void givenPopulatedArrayList_whenAllElementsRemoved_thenListIsEmpty() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int size = 450;

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < size; i++) {
            arrayList.remove(size - 1 - i);
        }

        assertTrue(arrayList.isEmpty());
    }

    @Test
    void givenArrayListWithItem_whenGet_thenExpectedItemReturned() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(-1);
        arrayList.set(0, 0);
        assertEquals(0, arrayList.get(0));

        for (int i = 1; i < 300; i++) {
            arrayList.add(i);
        }
        assertEquals(299, arrayList.get(299));
    }
}
