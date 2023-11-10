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

import com.viamatica.prueba.entities.Materia;
import com.viamatica.prueba.services.MateriaService;

@Controller
@RequestMapping("/api/materias")
@CrossOrigin("*")
public class MateriaController {
    
    @Autowired
    MateriaService materiaService;


     @GetMapping()
    public ResponseEntity<Object> getMateriasActivos() {
        try {

            List<Materia> Materias = materiaService.getMateriasActivos();

            if (Materias == null)
                throw new Exception();

            return new ResponseEntity<>(Materias, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> crearMateria(@RequestBody Materia Materia) {
        try {
            if (materiaService.crearMateria(Materia) == false) {

            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping()
    public ResponseEntity<Object> editarMateria(@RequestBody Materia Materia) {
        try {
            if (materiaService.editarMateria(Materia) == false) {

            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarMateria(@PathVariable Integer id) {
        try {
            if (materiaService.borrarMateria(id) == false) {

            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


     @GetMapping("/cantidad-alumnos/{id}")
    public ResponseEntity<Object> getMateriasActivos(@PathVariable Integer id) {
        try {

            Integer cantidad = materiaService.cantidadAlumnosPorMateria(id);

            if (cantidad == null)
                throw new Exception();

            return new ResponseEntity<>(cantidad, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
