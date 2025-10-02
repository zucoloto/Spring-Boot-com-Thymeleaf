package br.com.zuco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zuco.entity.Cargo;
import br.com.zuco.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	@Transactional
	public void salvar(Cargo cargo) {
		repository.save(cargo);
	}

	@Transactional
	public void atualizar(Cargo cargo) {
		repository.findById(cargo.getId()).orElseThrow();
		repository.save(cargo);
	}

	@Transactional
	public void excluir(Long id) {
		Cargo cargo = repository.findById(id).orElseThrow();
		repository.delete(cargo);
	}

	public List<Cargo> buscarTodos() {
		List<Cargo> listar = repository.findAll();
		return listar;
	}

	public Cargo buscarPorId(Long id) {
		Cargo cargo = repository.findById(id).orElseThrow();
		return cargo;
	}
	
}
