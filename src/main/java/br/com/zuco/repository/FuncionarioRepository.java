package br.com.zuco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zuco.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {}
