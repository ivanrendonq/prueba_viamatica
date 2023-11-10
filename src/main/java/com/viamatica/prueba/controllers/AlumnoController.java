package com.viamatica.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viamatica.prueba.entities.Alumno;
import com.viamatica.prueba.entities.AlumnoHasMaterias;
import com.viamatica.prueba.entities.Materia;
import com.viamatica.prueba.services.AlumnoService;


@Controller
@RequestMapping("/api/alumnos")
@CrossOrigin("*")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping()
    public ResponseEntity<Object> getAlumnosActivos() {
        try {

            List<Alumno> alumnos = alumnoService.getAlumnosActivos();

            if (alumnos == null)
                throw new Exception();

            return new ResponseEntity<>(alumnos, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> crearAlumno(@RequestBody Alumno alumno) {
        try {
            if (alumnoService.crearAlumno(alumno) == false) {

            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping()
    public ResponseEntity<Object> editarAlumno(@RequestBody Alumno alumno) {
        try {
            if (alumnoService.editarAlumno(alumno) == false) {

            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarAlumno(@PathVariable Integer id) {
        try {
            if (alumnoService.borrarAlumno(id) == false) {

            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/buscar")
    public ResponseEntity<Object> buscarAlumnosPorNombre(@RequestParam String nombre) {
        try {

            List<Alumno> alumnos = alumnoService.buscarAlumnosPorNombre(nombre);

            if (alumnos == null)
                throw new Exception();

            return new ResponseEntity<>(alumnos, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/cantidad-materias/{id}")
    public ResponseEntity<Object> bucarCantidadMaterias(@PathVariable Integer id) {
        try {

            Integer cantidad = alumnoService.getCantidadMaterias(id);

            if(cantidad == null)
                throw new Exception();
                
            return new ResponseEntity<>(cantidad, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/agregar-materia")
    public ResponseEntity<Object> agregarMateria(@RequestBody AlumnoHasMaterias alumnoMateria) {
        try {
            if (alumnoService.agregarMateria(alumnoMateria) == false) {

            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/materia-mayor-puntaje/{id}")
    public ResponseEntity<Object> buscarMateriaConPuntajeMasAlto(@PathVariable Integer id) {
        try {

            Materia materia = alumnoService.buscarMateriaMayorPuntaje(id);

            if(materia == null)
                throw new Exception();
                
            return new ResponseEntity<>(materia, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
