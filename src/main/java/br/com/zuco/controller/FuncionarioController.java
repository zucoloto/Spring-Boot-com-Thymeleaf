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
import br.com.zuco.entity.Funcionario;
import br.com.zuco.entity.UF;
import br.com.zuco.service.CargoService;
import br.com.zuco.service.FuncionarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	CargoService cargoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id1, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id1));
		return "funcionario/cadastro";
	}
	
	@PostMapping("/atualizar")
	public String atualizar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.atualizar(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id1, RedirectAttributes attr) {
		funcionarioService.excluir(id1);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return cargoService.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
}
