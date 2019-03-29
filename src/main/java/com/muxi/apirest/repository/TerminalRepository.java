package com.muxi.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muxi.apirest.model.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer>{
	Terminal findById(int logic);
}
