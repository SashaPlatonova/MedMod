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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "standart")
    private DocStandart standart;
}
