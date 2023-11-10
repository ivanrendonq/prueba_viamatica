package com.viamatica.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viamatica.prueba.entities.Maestro;
import java.util.List;
import java.util.Optional;


@Repository
public interface MaestroRepository extends JpaRepository<Maestro, Integer>{
    
    Optional<Maestro> findByIdMaestro(Integer idMaestro);

    List<Maestro> findByTitulo(String titulo);

    @Query( value = "Select * from maestro m where m.estado = 'Activo' ", nativeQuery = true)
    List<Maestro> findAllActiveMaestros();
}
