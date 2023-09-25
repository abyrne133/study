package com.abyrne.patterns;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumSingletonTest {

    @Test
    void multipleInstancesAreEquals() {
        int size = 100;
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE.getInstance();
        for (int i = 0; i < size; i++) {
            assertEquals(enumSingleton, EnumSingleton.INSTANCE.getInstance());
        }
    }

    @Test
    void multiThreadedInstancesAreEquals() throws ExecutionException, InterruptedException {
        int size = 100_000;
        CompletableFuture<EnumSingleton>[] future = new CompletableFuture[size];
        for (int i = 0; i < size; i++) {
            future[i] = CompletableFuture.supplyAsync(EnumSingleton.INSTANCE::getInstance);
        }

        for (int i = 0; i < size; i++) {
            assertEquals(EnumSingleton.INSTANCE.getInstance(), future[i].get());
        }

    }
}
