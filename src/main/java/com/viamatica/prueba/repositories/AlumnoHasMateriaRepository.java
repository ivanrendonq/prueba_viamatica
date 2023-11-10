package com.viamatica.prueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.prueba.entities.AlumnoHasMaterias;
import com.viamatica.prueba.entities.Materia;

@Repository
public interface AlumnoHasMateriaRepository extends JpaRepository<AlumnoHasMaterias, Integer>{
    
    @Query( value = "Select * from alumno_has_materias am where am.id_alumno = :alumnoId", nativeQuery = true)
    List<AlumnoHasMaterias> findMateriasDeAlumno(@Param("alumnoId") Integer alumnoId);

    List<AlumnoHasMaterias> findByMateria(Materia materia);

    
}
