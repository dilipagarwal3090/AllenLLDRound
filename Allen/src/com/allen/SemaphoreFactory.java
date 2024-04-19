package com.allen;

public class SemaphoreFactory {
    public static ConcurrencyManager createSemaphore() {
        return new ConcurrencyManager();
    }
}
