package ru.platonova.medmod.entity;


import com.google.gson.JsonObject;
import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String sessionName;

    @Column(nullable = false)
    private int office;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SessionCategory category;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "conclusion")
    private String conclusion;
}
