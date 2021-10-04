package ru.platonova.medmod.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "docStandart")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocStandart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String docName;
}
