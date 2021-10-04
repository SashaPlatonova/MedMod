package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "diagnosis")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "standart")
    private DocStandart standart;
}
