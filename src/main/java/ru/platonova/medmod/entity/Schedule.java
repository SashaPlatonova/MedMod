package ru.platonova.medmod.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "schedule")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {

    @Id
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column
    private Time time;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
