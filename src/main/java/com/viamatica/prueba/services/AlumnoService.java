package com.viamatica.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.prueba.entities.Alumno;
import com.viamatica.prueba.entities.AlumnoHasMaterias;
import com.viamatica.prueba.entities.EstadoEntidad;
import com.viamatica.prueba.entities.Materia;
import com.viamatica.prueba.repositories.AlumnoHasMateriaRepository;
import com.viamatica.prueba.repositories.AlumnoRepository;
import com.viamatica.prueba.repositories.MateriaRepository;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    AlumnoHasMateriaRepository alumnoMateriaRepository;

    public boolean crearAlumno(Alumno alumno) {
        try {
            alumnoRepository.save(alumno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarAlumno(Alumno alumno) {
        try {
            Optional<Alumno> alumnoBd = alumnoRepository.findById(alumno.getIdAlumno());

            if (alumnoBd.isEmpty())
                return false;

            alumnoRepository.save(alumno);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrarAlumno(Integer idAlumno) {
        try {
            Optional<Alumno> alumnoBd = alumnoRepository.findById(idAlumno);

            if (alumnoBd.isEmpty())
                return false;

            Alumno alumno = alumnoBd.get();
            alumno.setEstado(EstadoEntidad.Inactivo);

            alumnoRepository.save(alumno);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Alumno> getAlumnosActivos() {
        try {
            List<Alumno> alumnos = alumnoRepository.findAllActiveAlumnos();

            return alumnos;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Alumno> buscarAlumnosPorNombre(String nombre) {
        try {
            List<Alumno> alumnos = alumnoRepository.findByNombreContainingIgnoreCase(nombre);

            return alumnos;

        } catch (Exception e) {
            return null;
        }
    }

    public Integer getCantidadMaterias(Integer idAlumno) {
        try {
            Optional<Alumno> alumno = alumnoRepository.findById(idAlumno);

            if (alumno.isEmpty())
                throw new Exception();

            List<AlumnoHasMaterias> materias = alumnoMateriaRepository.findMateriasDeAlumno(alumno.get().getIdAlumno());

            if (materias == null)
                throw new Exception();

            return materias.size();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AlumnoHasMaterias> getMaterias(Integer idAlumno) {
        try {
            Optional<Alumno> alumno = alumnoRepository.findById(idAlumno);

            if (alumno.isEmpty())
                throw new Exception();

            List<AlumnoHasMaterias> materias = alumnoMateriaRepository.findMateriasDeAlumno(alumno.get().getIdAlumno());

            if (materias == null)
                throw new Exception();

            return materias;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean agregarMateria(AlumnoHasMaterias alumnoMateria) {
        try {
            // Comprobar si existe alumno
            Optional<Alumno> alumno = alumnoRepository.findById(alumnoMateria.getAlumno().getIdAlumno());

            if (alumno.isEmpty())
                throw new Exception();

            // Comprobar si existe la materia
            Optional<Materia> materia = materiaRepository.findById(alumnoMateria.getMateria().getIdMateria());

            if (materia.isEmpty())
                throw new Exception();

            // Guardar
            alumnoMateriaRepository.save(alumnoMateria);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Materia buscarMateriaMayorPuntaje(Integer id) {
        try {
            // Comprobar si existe alumno
            Optional<Alumno> alumno = alumnoRepository.findById(id);

            if (alumno.isEmpty())
                throw new Exception();

            List<AlumnoHasMaterias> alumnoMaterias = alumnoMateriaRepository.findMateriasDeAlumno(id);

            Double puntajeMaximo = 0d;

            Materia materiaMayorPuntaje = null;
            for (AlumnoHasMaterias alumnoHasMaterias : alumnoMaterias) {
                if(alumnoHasMaterias.getPuntaje() > puntajeMaximo)
                {
                    puntajeMaximo = alumnoHasMaterias.getPuntaje();
                    materiaMayorPuntaje = alumnoHasMaterias.getMateria();
                }
            }
            return materiaMayorPuntaje;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
