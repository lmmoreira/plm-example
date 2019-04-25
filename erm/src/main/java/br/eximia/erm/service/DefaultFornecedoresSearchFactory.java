package br.eximia.erm.service;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.StatusFornecedor;

@Component("defaultFornecedoresSearchFactory")
public class DefaultFornecedoresSearchFactory {
	
	@Autowired
	DefaultPendenteFornecedorSearch defaultPendenteFornecedorSearch;
	@Autowired
	DefaultAprovadoFornecedorSearch defaultAprovadoFornecedorSearch;
	@Autowired
	DefaultCanceladoFornecedorSearch defaultCanceladoFornecedorSearch;
	
	private HashMap<StatusFornecedor, AbstractFornecedorSearch> registeredSearch = new HashMap<StatusFornecedor, AbstractFornecedorSearch>();

	@PostConstruct
	public void postConstruct() {
		registeredSearch.put(StatusFornecedor.Pendente, defaultPendenteFornecedorSearch);
		registeredSearch.put(StatusFornecedor.Aprovado, defaultAprovadoFornecedorSearch);
		registeredSearch.put(StatusFornecedor.Cancelado, defaultCanceladoFornecedorSearch);
	}
	
	public AbstractFornecedorSearch getInstance(StatusFornecedor status){
		return registeredSearch.get(status);
	}
	
}
