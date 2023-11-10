package com.viamatica.prueba.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoHasMaterias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumnoHasMaterias;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno")
    @NotNull
    private Alumno alumno;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia")
    @NotNull
    private Materia materia;

    @NotNull
    private Double puntaje;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoEntidad estado;
}
