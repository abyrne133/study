package com.abyrne;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test()
    void givenPutSuccess_whenGet_thenExpectedValue() {
        // given
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        int key = 5;
        int value = 25;
        boolean isSuccessfulPut = hashTable.put(key, value);
        assertTrue(isSuccessfulPut);

        // when
        int result = hashTable.get(key);

        // then
        assertEquals(value, result);
    }


    @Test()
    void givenNullKey_whenPut_thenPutFailure() {
        // given
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        int value = 25;

        //when
        boolean isSuccessfulPut = hashTable.put(null, value);

        //then
        assertFalse(isSuccessfulPut);
    }

    @Test()
    void givenNullValue_whenPutSuccess_thenExpectedNullValue() {
        // given
        HashTable<String, Integer> hashTable = new HashTable<>();
        String key = "my-key";

        // when
        boolean isSuccessfulPut = hashTable.put(key, null);
        assertTrue(isSuccessfulPut);

        // then
        assertNull(hashTable.get(key));
    }

    @Test()
    void givenTableWithMultipleValue_whenGetAllKeys_thenExpectedValue() {
        // given
        final int size = 100_000;
        HashTable<Integer, String> hashTable = new HashTable<>();
        String[] expectedValues = new String[size];
        for (int i = 0; i < size; i++) {
            String value = String.valueOf(i);
            hashTable.put(i, value);
            expectedValues[i] = value;
        }

        // when
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
               values[i] = hashTable.get(i);
        }

        // then
        assertArrayEquals(expectedValues, values);
    }
}