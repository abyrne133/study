package com.abyrne.patterns;

public final class Singleton {

    private static Singleton INSTANCE;

    private Singleton() {
        System.out.println("created");
    }

    public static synchronized Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}
