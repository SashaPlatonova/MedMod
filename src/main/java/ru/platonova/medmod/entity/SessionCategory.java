package ru.platonova.medmod.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    @Column(nullable = false)
    private String name;

    @Column(name = "structure", nullable = false)
    private String structure;
}
