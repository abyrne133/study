package com.abyrne;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    @Test()
    void givenPutSuccess_whenGet_thenExpectedValue() {
        // given
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int key = 5;
        int value = 25;
        boolean isSuccessfulPut = hashMap.put(key, value);
        assertTrue(isSuccessfulPut);

        // when
        int result = hashMap.get(key);

        // then
        assertEquals(value, result);
    }


    @Test()
    void givenNullKey_whenPut_thenPutFailure() {
        // given
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int value = 25;

        //when
        boolean isSuccessfulPut = hashMap.put(null, value);

        //then
        assertFalse(isSuccessfulPut);
    }

    @Test()
    void givenNullValue_whenPutSuccess_thenExpectedNullValue() {
        // given
        HashMap<String, Integer> hashMap = new HashMap<>();
        String key = "my-key";

        // when
        boolean isSuccessfulPut = hashMap.put(key, null);
        assertTrue(isSuccessfulPut);

        // then
        assertNull(hashMap.get(key));
    }

    @Test()
    void givenTableWithMultipleValue_whenGetAllKeys_thenExpectedValue() {
        // given
        final int size = 100_000;
        HashMap<Integer, String> hashMap = new HashMap<>();
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
        boolean isSuccessfulPut = hashMap.put(key, expectedValue);
        assertTrue(isSuccessfulPut);

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
        boolean isSuccessfulPut = hashMap.put(key, null);
        assertTrue(isSuccessfulPut);

        // when
        Object value = hashMap.get(key);

        // then
        assertNull(value);
    }

    @Test()
    void givenTableWithMultipleValues_whenRemoveAllKeys_thenExpectedValuesAndRemoved() {
        // given
        final int size = 100_000;
        HashMap<Integer, String> hashMap = new HashMap<>();
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
}