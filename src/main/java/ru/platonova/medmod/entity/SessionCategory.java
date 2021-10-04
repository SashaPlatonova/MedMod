package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sessionCat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    // TODO: 04.10.2021 Json-строка для структуры приема/анализа/процедуры
//    @Column
//    private json structure;
}
