package com.recetario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetario.repository.entities.Receta;
import com.recetario.service.RecetaService;

@RestController
@RequestMapping("receta")
public class RecetaController {

	@Autowired
	RecetaService servicio;
	
	@GetMapping
	public ResponseEntity<?> listarRecetas(){
		ResponseEntity<?> responseEntity = null;
		Iterable<Receta> listadoCursos = null;

		listadoCursos = servicio.listarRecetas();
		responseEntity = ResponseEntity.ok(listadoCursos);

		return responseEntity;
	}
	
	@GetMapping("/{nombre}")
	public ResponseEntity<?> obtieneRecetaPorNombre(@PathVariable String nombre) {
		ResponseEntity<?> responseEntity = null;
		Optional<Receta> optional;

		optional = servicio.obtieneRecetaPorNombre(nombre);
		if (optional.isPresent()) {
			Receta recetaLeida = optional.get();
			responseEntity = ResponseEntity.ok(recetaLeida);
		} else {
			responseEntity = ResponseEntity.noContent().build();// 204
		}

		return responseEntity;
	}

}
