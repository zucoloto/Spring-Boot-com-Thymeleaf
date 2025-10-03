package br.com.zuco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zuco.entity.Departamento;
import br.com.zuco.service.DepartamentoService;

@Controller
@RequestMapping("departamentos")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		service.salvar(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id1, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id1));
		return "/departamento/cadastro";
	}
	
	@PostMapping("/atualizar")
	public  String atualizar(Departamento departamento) {
		service.atualizar(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id1, ModelMap model) {
		if (!service.departamentoTemCargos(id1)) {
			service.excluir(id1);
		}
		return listar(model);
	}
	
}
