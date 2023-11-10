package com.viamatica.prueba.controllers;

import java.util.List;
import java.util.Optional;

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

import com.viamatica.prueba.entities.Alumno;
import com.viamatica.prueba.entities.Maestro;
import com.viamatica.prueba.services.MaestroService;

@Controller
@RequestMapping("/api/maestros")
@CrossOrigin("*")
public class MaestroController {
    
    @Autowired
    MaestroService maestroService;

    @GetMapping()
    public ResponseEntity<Object> getMaestrosActivos() {
        try {

            List<Maestro> Maestros = maestroService.getMaestrosActivos();

            if (Maestros == null)
                throw new Exception();

            return new ResponseEntity<>(Maestros, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> crearMaestro(@RequestBody Maestro Maestro) {
        try {
            if (maestroService.crearMaestro(Maestro) == false) {

            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping()
    public ResponseEntity<Object> editarMaestro(@RequestBody Maestro Maestro) {
        try {
            if (maestroService.editarMaestro(Maestro) == false) {

            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarMaestro(@PathVariable Integer id) {
        try {
            if (maestroService.borrarMaestro(id) == false) {

            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("cantidad-alumnos/{id}")
    public ResponseEntity<Object> getCantidadAlumnos(@PathVariable Integer id) {
        try {
            Optional<Maestro> maestro = maestroService.obtenerMaestro(id);
            
            if(maestro== null || maestro.isEmpty())
            {
                throw new Exception();
            }

            Integer cantidadAlumnos = maestroService.cantidadAlumnos(maestro.get());

            return new ResponseEntity<>(cantidadAlumnos, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    


    @GetMapping("alumnos-mejor-promedio/{id}")
    public ResponseEntity<Object> getAlumnosMejorPromedio(@PathVariable Integer id) {
        try {
            
            List<Alumno> alumnos = maestroService.getAlumnosMejoresPromedio(id);

            return new ResponseEntity<>(alumnos, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    

}
