package br.com.zuco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zuco.entity.Departamento;
import br.com.zuco.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repository;

	@Transactional
	public void salvar(Departamento departamento) {
		repository.save(departamento);
	}

	@Transactional
	public void atualizar(Departamento departamento) {
		repository.findById(departamento.getId()).orElseThrow();
		repository.save(departamento);
	}

	@Transactional
	public void excluir(Long id) {
		Departamento departamento = repository.findById(id).orElseThrow();
		repository.delete(departamento);
	}

	public List<Departamento> buscarTodos() {
		List<Departamento> listar = repository.findAll();
		return listar;
	}

	public Departamento buscarPorId(Long id) {
		Departamento departamento = repository.findById(id).orElseThrow();
		return departamento;
	}
	
}
