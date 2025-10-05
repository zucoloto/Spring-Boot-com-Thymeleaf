package br.com.zuco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.zuco.entity.Cargo;
import br.com.zuco.entity.Departamento;
import br.com.zuco.service.CargoService;
import br.com.zuco.service.DepartamentoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cargos")
public class CargoController {

	@Autowired
	CargoService cargoService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id1, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id1));
		return "cargo/cadastro";
	}
	
	@PostMapping("/atualizar")
	public String atualizar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.atualizar(cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id1, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id1)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id1);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}
	
}
