package com.viamatica.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.prueba.entities.AlumnoHasMaterias;
import com.viamatica.prueba.entities.EstadoEntidad;
import com.viamatica.prueba.entities.Materia;
import com.viamatica.prueba.repositories.AlumnoHasMateriaRepository;
import com.viamatica.prueba.repositories.MateriaRepository;

@Service
public class MateriaService {
    
    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    AlumnoHasMateriaRepository alumnoHasMateriaRepository;

    public boolean crearMateria(Materia Materia)
    {
        try {
            materiaRepository.save(Materia);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean editarMateria(Materia Materia)
    {
        try {
            Optional<Materia> MateriaBd = materiaRepository.findById(Materia.getIdMateria());

            if(MateriaBd.isEmpty())
                return false;
            
            materiaRepository.save(Materia);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean borrarMateria(Integer idMateria)
    {
        try {
            Optional<Materia> MateriaBd = materiaRepository.findById(idMateria);

            if(MateriaBd.isEmpty())
                return false;
            
            Materia materia = MateriaBd.get();
            materia.setEstado(EstadoEntidad.Inactivo);

            materiaRepository.save(materia);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Materia> getMateriasActivos()
    {
        try {
            List<Materia> Materias = materiaRepository.findAllActiveMaterias();

            return Materias;

        } catch (Exception e) {
            return null;
        }
    }

    public Integer cantidadAlumnosPorMateria(Integer materiaId)
    {
        try {

            Optional<Materia> materia = materiaRepository.findById(materiaId);

            if(materia.isEmpty())
                throw new Exception();

            List<AlumnoHasMaterias> alumnosMaterias = alumnoHasMateriaRepository.findByMateria(materia.get()) ;

            if(alumnosMaterias== null)
                throw new Exception();

            return alumnosMaterias.size();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
