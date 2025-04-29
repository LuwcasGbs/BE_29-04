package com.map.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.entity.Tarefas;
import com.map.repository.TarefasRepository;

@Service
public class TarefasService {
	private final TarefasRepository tarefasRepository;
	
	@Autowired
	public TarefasService(TarefasRepository tarefasRepository) {
		this.tarefasRepository = tarefasRepository;
	}
	
	public List<Tarefas> getAllTarefas() {
		return tarefasRepository.findAll();
	}
	
	public Tarefas getTarefasById(Long id) {
		Optional<Tarefas> tarefas = tarefasRepository.findById(id);
		return tarefas.orElse(null);
	}
	
	public Tarefas salvarTarefas(Tarefas tarefas) {
		return tarefasRepository.save(tarefas);
	}
	
	public Tarefas updateTarefas(Long id, Tarefas updatedTarefas) {
		Optional<Tarefas> existingTarefas = tarefasRepository.findById(id);
		if(existingTarefas.isPresent()) {
			updatedTarefas.setId(id);
			return tarefasRepository.save(updatedTarefas);
		}
		return null;
	}
	
	public boolean deleteTarefas(Long id) {
		Optional<Tarefas> existingTarefas = tarefasRepository.findById(id);
		if(existingTarefas.isPresent()) {
			tarefasRepository.deleteById(id);
		}
		return false;
	}
}
