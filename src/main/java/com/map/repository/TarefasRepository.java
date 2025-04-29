package com.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.map.entity.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

}
