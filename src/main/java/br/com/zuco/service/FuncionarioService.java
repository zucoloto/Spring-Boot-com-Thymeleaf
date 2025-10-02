package br.com.zuco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zuco.entity.Funcionario;
import br.com.zuco.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Transactional
	public void salvar(Funcionario funcionario) {
		repository.save(funcionario);
	}

	@Transactional
	public void atualizar(Funcionario funcionario) {
		repository.findById(funcionario.getId()).orElseThrow();
		repository.save(funcionario);
	}

	@Transactional
	public void excluir(Long id) {
		Funcionario funcionario = repository.findById(id).orElseThrow();
		repository.delete(funcionario);
	}

	public List<Funcionario> buscarTodos() {
		List<Funcionario> listar = repository.findAll();
		return listar;
	}

	public Funcionario buscarPorId(Long id) {
		Funcionario funcionario = repository.findById(id).orElseThrow();
		return funcionario;
	}
	
}
