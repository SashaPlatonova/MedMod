package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "session")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sessionName;

    @Column
    private Date date;

    @Column
    private String documentName;
}
