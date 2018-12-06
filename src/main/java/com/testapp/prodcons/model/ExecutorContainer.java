package com.testapp.prodcons.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorContainer {

    private final AtomicInteger counter = new AtomicInteger(0);
    private ConcurrentMap<Integer, ExecutorService> storage = new ConcurrentHashMap<>();


    public void addTaskToStorage(ExecutorService task) {
        storage.put(counter.incrementAndGet(), task);
    }

    public ConcurrentMap<Integer, ExecutorService> getStorage() {
        return storage;
    }
}
