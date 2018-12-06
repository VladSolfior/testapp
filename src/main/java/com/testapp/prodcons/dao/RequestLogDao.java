package com.testapp.prodcons.dao;


import com.testapp.prodcons.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogDao extends JpaRepository<RequestLog, Long> {
}
