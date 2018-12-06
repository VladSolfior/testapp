package com.testapp.prodcons.dao;

import com.testapp.prodcons.model.WorkUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDao extends JpaRepository<WorkUnit, Long> {
}
