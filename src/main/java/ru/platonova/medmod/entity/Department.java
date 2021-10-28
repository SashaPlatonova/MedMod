package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Department {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
}
