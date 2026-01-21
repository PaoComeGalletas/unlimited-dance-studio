package com.project.unlimited.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String maestro;

    @ManyToMany(mappedBy = "clases")
    private Set<Alumno> alumnos = new HashSet<>();
}
