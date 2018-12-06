package com.testapp.prodcons.producer;

import com.testapp.prodcons.model.CounterHolder;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;

import static com.testapp.prodcons.utils.WorkUtil.endReached;
import static java.lang.Thread.currentThread;

@Log4j2
public class Producer implements Callable<String> {

    private CounterHolder holder;

    @Override
    public String call() {
        String item = null;
        currentThread().setName("Producer-" + holder.getCounterValue());


        while (!endReached(holder.getCounterValue())) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.info("current value: {}",holder.getCounterValue());
            holder.increaseValue();
            item = String.format("value increased: by thread: %s, current: %s",Thread.currentThread().getName(), holder.getCounterValue());
            log.info(item);

        }
        return item;
    }

    void setHolder(CounterHolder counterHolder) {
        this.holder = counterHolder;
    }
}