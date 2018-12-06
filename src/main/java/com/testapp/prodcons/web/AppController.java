package com.testapp.prodcons.web;


import com.testapp.prodcons.consumer.ConsumerGroup;
import com.testapp.prodcons.consumer.Receiver;
import com.testapp.prodcons.model.CounterHolder;
import com.testapp.prodcons.producer.Producer;
import com.testapp.prodcons.producer.ProducerGroup;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.testapp.prodcons.utils.WorkUtil.endReached;

@Log4j2
@RestController
public class AppController {

    private final CounterHolder holder;
    private final ProducerGroup producerGroupService;
    private final ConsumerGroup consumerGroupService;

    @Autowired
    public AppController(CounterHolder holder, ProducerGroup producer, ConsumerGroup consumerGroupService) {
        this.holder = holder;
        this.producerGroupService = producer;
        this.consumerGroupService = consumerGroupService;
    }

    @PostMapping("/api/increase")
    public ResponseEntity increase(@RequestParam(value = "incProd", required = false) Boolean incProd,
                                   @RequestParam(value = "incCons", required = false)  Boolean incCons) {

        log.info("work received");

        if (endReached(holder.getCounterValue()) &&
                (isValid(incProd) || isValid(incCons))) {
            holder.setValue(50);
        }

        if (isValid(incProd)) {

            if (endReached(holder.getCounterValue())) {
                producerGroupService.init();
            }
            producerGroupService.produce(new Producer());
        }

        if (isValid(incCons)) {
            if (endReached(holder.getCounterValue())) {
                consumerGroupService.init();
            }
            consumerGroupService.consume(new Receiver());
        }


        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    private boolean isValid(Boolean incProd) {
        return null != incProd && incProd;
    }


    @PostMapping("/api/counter")
    public ResponseEntity changeCounter(@RequestParam Integer counterValue) {

        if (isValid(counterValue)) {
            if (endReached(holder.getCounterValue())) {
                holder.setValue(counterValue);
                producerGroupService.init();
                consumerGroupService.init();
            }

            holder.setValue(counterValue);
            int currentCounterValue = holder.getCounterValue();
            return ResponseEntity.ok().body(currentCounterValue);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("invalid value sent");
    }

    private boolean isValid(Integer counterValue) {
        return null != counterValue;
    }
}
