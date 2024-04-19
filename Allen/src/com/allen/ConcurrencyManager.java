package com.allen;

import javax.inject.Singleton;
import java.util.concurrent.Semaphore;

@Singleton
public class ConcurrencyManager {
    private static final int MAX_CONCURRENT_OPERATION = 10;
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_OPERATION);

    public void acquire() {
        try {
            semaphore.acquire();
        } catch (InterruptedException exception) {
            throw new ConcurrencyException("Failed to acquire semaphore");
        }
    }

    public void release() {
        semaphore.release();
    }
}
