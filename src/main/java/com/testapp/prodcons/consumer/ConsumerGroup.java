package com.testapp.prodcons.consumer;

import com.testapp.prodcons.model.CounterHolder;
import com.testapp.prodcons.model.ExecutorContainer;
import com.testapp.prodcons.model.WorkUnit;
import com.testapp.prodcons.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.testapp.prodcons.utils.WorkUtil.endReached;

@Service
public class ConsumerGroup {

    private final WorkService workService;
    private final CounterHolder counterHolder;
    private ExecutorContainer container = new ExecutorContainer();


    @Autowired
    public ConsumerGroup(WorkService workService, CounterHolder counterHolder) {
        this.workService = workService;
        this.counterHolder = counterHolder;
    }


    public void consume(Receiver task) {

        if (endReached(counterHolder.getCounterValue())) {
            stopWork();
            WorkUnit unit = new WorkUnit(new Date());
            workService.save(unit);
        }
        else {
            task.setHolder(counterHolder);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(task);
            container.addTaskToStorage(executor);
        }
    }


    private void stopWork() {
        if (null != container) {
            container.getStorage().values()
                    .forEach( ExecutorService::shutdownNow);
        }
        container = null;
    }

    public void init() {
        stopWork();
        this.container = new ExecutorContainer();
    }
}