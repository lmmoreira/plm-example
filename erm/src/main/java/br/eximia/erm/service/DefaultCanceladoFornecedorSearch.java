package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.service.interfaces.FornecedorService;

@Component("defaultCanceladoFornecedorSearch")
public class DefaultCanceladoFornecedorSearch extends AbstractFornecedorSearch {

	@Autowired
	FornecedorService fornecedorService;
	
	@Override
	public List<Fornecedor> getFornecedores() {
		return fornecedorService.findByFornecedoresCancelados();
	}
	
}
