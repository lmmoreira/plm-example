package br.eximia.nfp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.nfp.model.Fornecedor;
import br.eximia.nfp.model.Usuario;
import br.eximia.nfp.service.interfaces.FornecedorService;
import br.eximia.nfp.service.interfaces.UsuarioService;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("fornecedoresController")
public class FornecedoresController extends AbstractCrudController<Fornecedor, Long> {

	UsuarioService defaultUsuarioService;
	FornecedorService defaultFornecedorService;
	
	@Autowired
	public FornecedoresController(UsuarioService defaultUsuarioService, FornecedorService defaultFornecedorService){
		this.defaultUsuarioService = defaultUsuarioService;
		this.defaultFornecedorService = defaultFornecedorService;
	}
	
	public List<Usuario> getUsuarios() {
		return defaultUsuarioService.findAll();
	}
	
	@Override
	public void save() {
		this.getEntity().setCriador(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
}
