package com.project.unlimited.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clases")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String maestro;

    @ManyToMany(mappedBy = "clases")
    @ToString.Exclude
    private Set<Alumno> alumnos = new HashSet<>();
}
