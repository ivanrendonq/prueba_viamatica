package com.viamatica.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viamatica.prueba.entities.Materia;
import java.util.List;
import java.util.Optional;


@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    
    Optional<Materia> findByIdMateria(Integer idMateria);

    List<Materia> findByDescripcion(String descripcion);

    @Query( value = "Select * from materia m where m.estado = 'Activo' ", nativeQuery = true)
    List<Materia> findAllActiveMaterias();

}
