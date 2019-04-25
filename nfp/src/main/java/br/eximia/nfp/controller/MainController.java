package br.eximia.nfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.nfp.service.interfaces.FornecedorService;
import br.eximia.nfp.service.interfaces.UsuarioService;

@Component
@Scope("session")
@Qualifier("mainController")
public class MainController {

	FornecedorService defaultFornecedorService;
	UsuarioService defaultUsuarioService;

	@Autowired
	public MainController(UsuarioService defaultUsuarioService,
			FornecedorService defaultFornecedorService) {
		this.defaultUsuarioService = defaultUsuarioService;
		this.defaultFornecedorService = defaultFornecedorService;
	}
	
}
