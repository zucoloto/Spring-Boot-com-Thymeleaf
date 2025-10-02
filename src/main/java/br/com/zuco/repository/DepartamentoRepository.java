package br.com.zuco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zuco.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {}
