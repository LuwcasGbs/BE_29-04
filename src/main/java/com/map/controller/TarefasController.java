package com.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.map.entity.Tarefas;
import com.map.service.TarefasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

	private final TarefasService tarefasService;
	
	@Autowired
	public TarefasController(TarefasService tarefasService) {
		this.tarefasService = tarefasService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefas> getTarefasById(@PathVariable Long id) {
		Tarefas tarefas = tarefasService.getTarefasById(id);
		if(tarefas != null) {
			return ResponseEntity.ok(tarefas);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Tarefas>> getAllTarefas() {
		List<Tarefas> tarefas = tarefasService.getAllTarefas();
		return ResponseEntity.ok(tarefas);
	}
	
	@PostMapping("/")
	public ResponseEntity<Tarefas> criarTarefas(@RequestBody @Valid Tarefas tarefas) {
		Tarefas criarTarefas = tarefasService.salvarTarefas(tarefas);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefas> updateTarefas(@PathVariable Long id, @RequestBody @Valid Tarefas tarefas) {
		Tarefas updatedTarefas = tarefasService.updateTarefas(id, tarefas);
		if(updatedTarefas != null) {
			return ResponseEntity.ok(updatedTarefas);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tarefas> deleteTarefas(@PathVariable Long id) {
		boolean deleted = tarefasService.deleteTarefas(id);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
