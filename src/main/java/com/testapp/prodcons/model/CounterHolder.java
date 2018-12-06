package com.testapp.prodcons.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@PropertySource("classpath:application.properties")
@Configuration
public class CounterHolder {

    @Value("testapp.init.counter")
    private String initValue;


    private static volatile AtomicInteger counter = new AtomicInteger(50);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();




    public int getCounterValue() {
        try {
            lock.readLock().lock();
            return counter.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setValue(Integer counterValue) {

        try {
            lock.writeLock().lock();

            while (true) {
                int existingValue = counter.get();
                int newValue = counterValue;
                if (counter.compareAndSet(existingValue, newValue)) {
                    return;
                }
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    public int increaseValue() {


        try {
            lock.writeLock().lock();

            while (true) {
                int existingValue = counter.get();
                int newValue = existingValue + 1;
                if (counter.compareAndSet(existingValue, newValue)) {
                    return counter.get();
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int decreaseValue() {

        try {
            lock.writeLock().lock();

            while (true) {
                int existingValue = counter.get();
                int newValue = existingValue - 1;
                if (counter.compareAndSet(existingValue, newValue)) {
                    return counter.get();
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
