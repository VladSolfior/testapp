package com.testapp.prodcons.consumer;


import com.testapp.prodcons.model.CounterHolder;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;

import static com.testapp.prodcons.utils.WorkUtil.endReached;
import static java.lang.Thread.currentThread;

@Log4j2
public class Receiver implements Callable<String> {

    private CounterHolder holder;

    public String call() {
        String item = null;
        currentThread().setName("Consumer-" + holder.getCounterValue());

        while (!endReached(holder.getCounterValue())) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.info("current value: {}",holder.getCounterValue());
            holder.decreaseValue();
            item = String.format("value decreased by thread: %s, current value: %s",
                    Thread.currentThread().getName(), holder.getCounterValue());
            log.info(item);
        }
        return item;
    }


    void setHolder(CounterHolder holder) {
        this.holder = holder;
    }
}