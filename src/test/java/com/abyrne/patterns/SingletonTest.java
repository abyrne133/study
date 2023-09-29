package com.abyrne.patterns;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingletonTest {

    @Test
    void multipleInstancesAreEquals() {
        int size = 100;
        Singleton singleton = Singleton.getInstance();
        for (int i = 0; i < size; i++) {
            assertEquals(singleton, Singleton.getInstance());
        }
    }

    @Test
    void multiThreadedInstancesAreEquals() throws ExecutionException, InterruptedException {
        int size = 100_000;
        CompletableFuture<Singleton>[] future = new CompletableFuture[size];
        for (int i = 0; i < size; i++) {
            future[i] = CompletableFuture.supplyAsync(Singleton::getInstance);
        }

        for (int i = 0; i < size; i++) {
            assertEquals(Singleton.getInstance(), future[i].get());
        }

    }
}
