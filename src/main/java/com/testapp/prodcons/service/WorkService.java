package com.testapp.prodcons.service;

import com.testapp.prodcons.dao.WorkDao;
import com.testapp.prodcons.model.WorkUnit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class WorkService {

    private final WorkDao workDao;

    @Autowired
    public WorkService(WorkDao workDao) {
        this.workDao = workDao;

    }

    @Transactional
    public WorkUnit save(WorkUnit unit) {
        log.info("save work unit with date {}", unit.getFinishWork());
        return workDao.save(unit);
    }
}
