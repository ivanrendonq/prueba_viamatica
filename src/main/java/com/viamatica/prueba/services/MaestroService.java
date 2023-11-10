package com.viamatica.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.prueba.entities.Alumno;
import com.viamatica.prueba.entities.EstadoEntidad;
import com.viamatica.prueba.entities.Maestro;
import com.viamatica.prueba.repositories.AlumnoRepository;
import com.viamatica.prueba.repositories.MaestroRepository;

@Service
public class MaestroService {
    
    @Autowired
    MaestroRepository maestroRepository;

    @Autowired
    AlumnoRepository alumnoRepository;


    public boolean crearMaestro(Maestro Maestro)
    {
        try {
            maestroRepository.save(Maestro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Maestro> obtenerMaestro(Integer id)
    {
        try {
            Optional<Maestro> MaestroBd = maestroRepository.findById(id);
            
            return MaestroBd;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }

    public Integer cantidadAlumnos(Maestro maestro)
    {   
        try {
            List<Alumno> alumnos = alumnoRepository.findByMaestro(maestro);

            return alumnos.size();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean editarMaestro(Maestro Maestro)
    {
        try {
            Optional<Maestro> MaestroBd = maestroRepository.findById(Maestro.getIdMaestro());

            if(MaestroBd.isEmpty())
                return false;
            
            maestroRepository.save(Maestro);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean borrarMaestro(Integer idMaestro)
    {
        try {
            Optional<Maestro> MaestroBd = maestroRepository.findById(idMaestro);

            if(MaestroBd.isEmpty())
                return false;
            
            Maestro maestro = MaestroBd.get();
            maestro.setEstado(EstadoEntidad.Inactivo);

            maestroRepository.save(maestro);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Maestro> getMaestrosActivos()
    {
        try {
            List<Maestro> Maestros = maestroRepository.findAllActiveMaestros();

            return Maestros;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Alumno> getAlumnosMejoresPromedio(Integer id) {
        try {
            List<Maestro> Maestros = maestroRepository.findAllActiveMaestros();

            return Maestros;

        } catch (Exception e) {
            return null;
        }
    }








}
