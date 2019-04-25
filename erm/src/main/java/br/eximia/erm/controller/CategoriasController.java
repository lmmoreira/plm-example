package br.eximia.erm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Categoria;
import br.eximia.erm.model.Tecnologia;
import br.eximia.erm.service.interfaces.TecnologiaService;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("categoriasController")
public class CategoriasController extends AbstractCrudController<Categoria, Long> {

	TecnologiaService defaultTecnologiaService;
	
	@Autowired
	public CategoriasController(TecnologiaService defaultTecnologiaService) {
		super();
		this.defaultTecnologiaService = defaultTecnologiaService;
	}
	
	public List<Tecnologia> getTecnologias(){
		return defaultTecnologiaService.findAll();
	}
	
}
