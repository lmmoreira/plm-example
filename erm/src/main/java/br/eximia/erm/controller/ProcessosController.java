package br.eximia.erm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("processosController")
public class ProcessosController extends AbstractCrudController<ProcessoEspecial, Long> {
	
	UsuarioService defaultUsuarioService;
	
	@Autowired
	public ProcessosController(UsuarioService defaultUsuarioService){
		this.defaultUsuarioService = defaultUsuarioService;
	}
	
	@Override
	public void save() {
		this.getEntity().setCriador(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
}
