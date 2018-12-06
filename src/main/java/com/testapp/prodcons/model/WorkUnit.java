package com.testapp.prodcons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_model")
public class WorkUnit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_finished")
    private Timestamp finishWork;

    public WorkUnit(Date date) {
        this.finishWork = new Timestamp(date.getTime());
    }
}
