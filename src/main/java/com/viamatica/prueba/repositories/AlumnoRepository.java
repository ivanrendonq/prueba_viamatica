package com.viamatica.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.prueba.entities.Alumno;
import com.viamatica.prueba.entities.Maestro;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    
    Optional<Alumno> findByIdAlumno(Integer idAlumno);

    List<Alumno> findByMaestro(Maestro maestro);

    @Query( value = "Select * from alumno a where a.estado = 'Activo' ", nativeQuery = true)
    List<Alumno> findAllActiveAlumnos();

    List<Alumno> findByNombreContainingIgnoreCase(String nombre);

    
}
