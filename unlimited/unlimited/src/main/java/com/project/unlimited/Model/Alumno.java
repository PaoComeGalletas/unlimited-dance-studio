package com.project.unlimited.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "dia_pago", nullable = false)
    private Integer diaPago;

    @Column(nullable = false)
    private BigDecimal mensualidad;

    @Column(name = "contacto_emergencia")
    private String contactoEmergencia;

    @Column(name = "telefono_emergencia")
    private String telefonoEmergencia;

    @ManyToMany
    @JoinTable(
            name = "alumno_clase",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "clase_id")
    )
    private Set<Clase> clases = new HashSet<>();
}
