package br.com.zuco.error;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorView implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("status", status.value());
		switch (status.value()) {
			case 400:
				model.addObject("error", "Solicitação inválida.");
				model.addObject("message", "Aviso: O cliente não deve repetir essa requisição sem modificá-la.");
				break;
			case 404:
					model.addObject("error", "Página não encontrada.");
					model.addObject("message", "Aviso: A url para a página '" + map.get("path") + "' não existe.");
					break;
			case 500:
					model.addObject("error", "Ocorreu um erro interno no servidor.");
					model.addObject("message", "Aviso: Ocorreu um erro inexperado, tente mais tarde.");
					break;
			default:
					model.addObject("error", map.get("error"));
					model.addObject("message", map.get("message"));
					break;
		}		
		return model;
	}

}
