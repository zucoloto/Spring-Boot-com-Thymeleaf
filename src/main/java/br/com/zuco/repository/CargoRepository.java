package br.com.zuco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zuco.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {}
