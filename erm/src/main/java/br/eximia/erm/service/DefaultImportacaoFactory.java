package br.eximia.erm.service;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.TipoImportacao;

@Component("defaultImportacaoFactory")
public class DefaultImportacaoFactory {
	
	@Autowired
	DefaultTecnologiasImportacao defaultTecnologiasImportacao;
	@Autowired
	DefaultCategoriasImportacao defaultCategoriasImportacao;
	@Autowired
	DefaultProcessosImportacao defaultProcessosImportacao;
	@Autowired
	DefaultUnidadesFornecedorImportacao defaultUnidadesFornecedorImportacao;
	@Autowired
	DefaultUnidadesLoteFornecedorImportacao defaultUnidadesLoteFornecedorImportacao;
	@Autowired
	DefaultUnidadesLoteImportacao defaultUnidadesLoteImportacao;
	@Autowired
	DefaultUsuariosImportacao defaultUsuariosImportacao;
	@Autowired
	DefaultFornecedoresImportacao defaultFornecedoresImportacao;
	@Autowired
	DefaultMateriaisImportacao defaultMateriaisImportacao;
	
	private HashMap<TipoImportacao, AbstractImportacao<?, ?>> registeredImportacao = new HashMap<TipoImportacao, AbstractImportacao<?, ?>>();

	@PostConstruct
	public void postConstruct() {
		registeredImportacao.put(TipoImportacao.Tecnologias, defaultTecnologiasImportacao);
		registeredImportacao.put(TipoImportacao.Categorias, defaultCategoriasImportacao);
		registeredImportacao.put(TipoImportacao.Processos, defaultProcessosImportacao);
		
		registeredImportacao.put(TipoImportacao.UnidadesFornecedor, defaultUnidadesFornecedorImportacao);
		registeredImportacao.put(TipoImportacao.UnidadesLoteFornecedor, defaultUnidadesLoteFornecedorImportacao);
		registeredImportacao.put(TipoImportacao.UnidadesLote, defaultUnidadesLoteImportacao);
		
		registeredImportacao.put(TipoImportacao.Fornecedores, defaultFornecedoresImportacao);
		registeredImportacao.put(TipoImportacao.Materiais, defaultMateriaisImportacao);
		
		registeredImportacao.put(TipoImportacao.Usuarios, defaultUsuariosImportacao);
	}
	
	public AbstractImportacao<?, ?> getInstance(TipoImportacao tipo){
		return registeredImportacao.get(tipo);
	}
	
}
