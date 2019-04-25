package br.eximia.erm.controller;

import java.util.List;

import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.StatusFornecedor;
import br.eximia.erm.model.StatusMaterial;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.MaterialMensagemService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.UsuarioService;

@Component
@Scope("session")
@Qualifier("mainController")
public class MainController {

	MaterialService defaultMaterialService;
	FornecedorService defaultFornecedorService;
	UsuarioService defaultUsuarioService;

	@Autowired
	public MainController(MaterialService defaultMaterialService,
			UsuarioService defaultUsuarioService,
			FornecedorService defaultFornecedorService,
			MaterialMensagemService defaultMaterialMensagemService) {
		this.defaultMaterialService = defaultMaterialService;
		this.defaultUsuarioService = defaultUsuarioService;
		this.defaultFornecedorService = defaultFornecedorService;
	}

	public List<Material> getUserMessagePendencies() {
		return defaultMaterialService
				.findByDestinoNaoLido(defaultUsuarioService.getLoggedUser());
	}

	public List<Fornecedor> getUserMessagePendenciesFornecedores() {
		return defaultFornecedorService
				.findByDestinoNaoLido(defaultUsuarioService.getLoggedUser());
	}

	public List<Material> getUserMaterialPendencies() {
		return defaultMaterialService.findByAprovadoresStatusAndUser(
				StatusMaterial.Pendente, defaultUsuarioService.getLoggedUser());
	}
	
	public List<Material> getUserMaterialPendenciesCampos() {
		return defaultMaterialService.findByAprovadoresStatusAndUserCampos(
				StatusMaterial.Pendente, defaultUsuarioService.getLoggedUser());
	}

	public List<Fornecedor> getUserFornecedorPendencies() {
		return defaultFornecedorService.findByAprovadoresStatusAndUser(
				StatusFornecedor.Pendente,
				defaultUsuarioService.getLoggedUser());
	}

	public void checkFornecedorRevalidacao(ComponentSystemEvent event) {
		defaultFornecedorService.checkFornecedorRevalidacao();
	}
	
}
