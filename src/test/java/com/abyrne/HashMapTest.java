package com.abyrne;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test()
    void givenPutSuccess_whenGet_thenExpectedValue() {
        // given
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int key = 5;
        int value = 25;
        assertNull(hashMap.put(key, value));

        // when
        int result = hashMap.get(key);

        // then
        assertEquals(value, result);
    }

    @Test()
    void givenNullValue_whenPutSuccess_thenExpectedNullValue() {
        // given
        HashMap<String, Integer> hashMap = new HashMap<>();
        String key = "my-key";

        // when
        assertNull(hashMap.put(key, null));

        // then
        assertNull(hashMap.get(key));
    }

    @Test()
    void givenTableWithMultipleValue_whenGetAllKeys_thenExpectedValue() {
        // given
        final int size = 1_000_000;
        final float loadFactor = 0.75f;
        final int initialCapacity = (int) (size / loadFactor);
        HashMap<Integer, String> hashMap = new HashMap<>(initialCapacity, loadFactor);
        String[] expectedValues = new String[size];
        for (int i = 0; i < size; i++) {
            String value = String.valueOf(i);
            hashMap.put(i, value);
            expectedValues[i] = value;
        }

        // when
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = hashMap.get(i);
        }

        // then
        assertArrayEquals(expectedValues, values);
    }

    @Test()
    void givenPutSuccess_whenRemoveKey_thenExpectedValueAndRemoved() {
        // given
        String key = "the-key";
        double expectedValue = 56.4;
        HashMap<String, Double> hashMap = new HashMap<>();
        assertNull(hashMap.put(key, expectedValue));

        // when
        double value = hashMap.remove(key);

        // then
        assertEquals(expectedValue, value);
        assertNull(hashMap.get(key));
    }

    @Test()
    void givenNullValue_whenRemoveKey_thenExpectedNullValue() {
        // given
        List<Integer> key = new ArrayList<>();
        HashMap<List<Integer>, Object> hashMap = new HashMap<>();
        assertNull(hashMap.put(key, null));

        // when
        Object value = hashMap.get(key);

        // then
        assertNull(value);
    }

    @Test()
    void givenTableWithMultipleValues_whenRemoveAllKeys_thenExpectedValuesAndRemoved() {
        // given
        final int size = 1_000_000;
        final float loadFactor = 0.75f;
        final int initialCapacity = (int) (size / loadFactor);
        HashMap<Integer, String> hashMap = new HashMap<>(initialCapacity, loadFactor);
        String[] expectedValues = new String[size];
        for (int i = 0; i < size; i++) {
            String value = String.valueOf(i);
            hashMap.put(i, value);
            expectedValues[i] = value;
        }

        // when
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = hashMap.remove(i);
            assertEquals(expectedValues[i], values[i]);
        }

        // then
        for (int i = 0; i < size; i++) {
            assertNull(hashMap.get(i));
        }
    }

    @Test()
    void givenExceededThreshold_whenGetKey_thenExpectedValue() {
        // given
        int capacity = 200;
        float loadFactor = 0.2f;
        HashMap<Integer, Integer> hashMap = new HashMap<>(capacity, loadFactor);

        // when
        for (int i = 0; i < capacity; i++) {
            assertNull(hashMap.put(i, i));
        }

        // then
        for (int i = 0; i < capacity; i++) {
            assertEquals(i, hashMap.get(i));
        }

    }

    @Test()
    void givenHashMapWithCustomPreHashFunction_whenPopulated_thenExpectedValuesRetrievable() {
        // given
        int capacity = 500_000;
        float loadFactor = 0.9f;
        HashMap<Integer, Integer> hashMap = new HashMap(capacity, loadFactor, (k) -> k.hashCode());


        // when
        for (int i = 0; i < capacity; i++) {
            assertNull(hashMap.put(i, i));
        }

        // then
        for (int i = 0; i < capacity; i++) {
            assertEquals(i, hashMap.remove(i));
        }
    }

    @Test()
    void givenHashMapWithPoorPreHashFunction_whenPopulated_thenExpectedValuesRetrievable() {
        // given
        int capacity = 100; // low number or the pre-hash function will have us here all day!
        float loadFactor = 0.9f;
        HashMap<Integer, Integer> hashMap = new HashMap(capacity, loadFactor, (k) -> 7);


        // when
        for (int i = 0; i < capacity; i++) {
            assertNull(hashMap.put(i, i));
        }

        // then
        for (int i = 0; i < capacity; i++) {
            assertEquals(i, hashMap.remove(i));
        }
    }
}