package com.muxi.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muxi.apirest.model.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer>{
	Terminal findByLogic(Integer logic);
}
